package pet.project.sensor.Air.Sensor.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pet.project.sensor.Air.Sensor.dto.AirQualityDto;
import pet.project.sensor.Air.Sensor.entity.AirQuality;
import pet.project.sensor.Air.Sensor.entity.Sensor;
import pet.project.sensor.Air.Sensor.mapper.AirQualityMapper;
import pet.project.sensor.Air.Sensor.repository.AirQualityRepository;
import pet.project.sensor.Air.Sensor.repository.SensorRepository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AirQualityRandomGenerator implements Serializable {

    private final AirQualityRepository airQualityRepository;
    private final SensorRepository sensorRepository;
    private final Random random = new Random();
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final AirQualityMapper airQualityMapper;


    @Scheduled(initialDelay = 5000, fixedRate = 5000) //запуск каждые 60сек/1 min
    public void generateRandomAirQuality(){
        List<Sensor> sensorList = sensorRepository.findAll();
        if(sensorList.isEmpty()){
            System.out.println("Sensor list is empty!");
            return;
        }

        for(Sensor sensor : sensorList){
            AirQuality airQuality = new AirQuality();
            airQuality.setTemperature(random.nextInt(20)+12);
            airQuality.setHumidity(random.nextInt(15)+31);
            airQuality.setAirPollution(random.nextInt(51)+52);
            BigDecimal airPressure = BigDecimal.valueOf(700 + random.nextDouble() * 300)
                    .setScale(2, RoundingMode.HALF_UP);
            airQuality.setAirPressure(airPressure);
            airQuality.setNoise(random.nextInt(25)+20);
            airQuality.setCreated(LocalDateTime.now());
            airQuality.setSensor(sensor);
            AirQualityDto airQualityDto = airQualityMapper.toAirQualityDto(airQuality);

            rabbitMQMessageProducer.sendAirQualityDto(airQualityDto);
            log.info("Air quality generated!");

        }


    }



}
