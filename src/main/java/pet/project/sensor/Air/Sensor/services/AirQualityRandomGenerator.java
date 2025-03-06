package pet.project.sensor.Air.Sensor.services;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pet.project.sensor.Air.Sensor.dto.AirQualityDto;
import pet.project.sensor.Air.Sensor.dto.CitiesDto;
import pet.project.sensor.Air.Sensor.dto.SensorDto;
import pet.project.sensor.Air.Sensor.entity.AirQuality;
import pet.project.sensor.Air.Sensor.entity.Cities;
import pet.project.sensor.Air.Sensor.entity.Sensor;
import pet.project.sensor.Air.Sensor.mapper.AirQualityMapper;
import pet.project.sensor.Air.Sensor.mapper.CitiesMapper;
import pet.project.sensor.Air.Sensor.repository.AirQualityRepository;
import pet.project.sensor.Air.Sensor.repository.CitiesRepository;
import pet.project.sensor.Air.Sensor.repository.SensorRepository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AirQualityRandomGenerator implements Serializable {

    private final Random random = new Random();
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final CitiesRepository citiesRepository;
    private final CitiesMapper citiesMapper;

    @Transactional
    @Scheduled(fixedRate = 10000) //запуск каждые 10сек/
    public void generateRandomAirQuality() {
        List<Cities> cities = citiesRepository.findAll();
        if (cities.isEmpty()) {
            log.warn("Cities is empty");
            return;
        }

        for (Cities c : cities) {
            CitiesDto citiesDto = citiesMapper.toCityDto(c);

            for (SensorDto sensorDto : citiesDto.getSensorDtoList()) {
                if(sensorDto==null){
                    log.warn("Sensor is null");
                }

                List<AirQualityDto> generatedAirQualityList = new ArrayList<>();

                    AirQualityDto airQualityDto = new AirQualityDto();
                    airQualityDto.setTemperature(random.nextInt(20) + 12);
                    airQualityDto.setHumidity(random.nextInt(15) + 31);
                    airQualityDto.setAirPollution(random.nextInt(51) + 52);
                    BigDecimal airPressure = BigDecimal.valueOf(700 + random.nextDouble() * 300)
                            .setScale(2, RoundingMode.HALF_UP);
                    airQualityDto.setAirPressure(airPressure);
                    airQualityDto.setNoise(random.nextInt(25) + 20);
                    airQualityDto.setCreated(LocalDateTime.now());
                    airQualityDto.setSensorId(sensorDto.getId());

                    generatedAirQualityList.add(airQualityDto);


                sensorDto.setAirQualityDtoList(generatedAirQualityList);
            }

            rabbitMQMessageProducer.sendAirQualityDto(citiesDto);

            }
        }
    }

