package pet.project.sensor.Air.Sensor.services;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;
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
import pet.project.sensor.Air.Sensor.webSocket.AirQualityWebSocketHandler;

import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class RabbitMQMessageListener {

    private final CitiesRepository citiesRepository;
    private final CitiesMapper citiesMapper;
    private final SensorRepository sensorRepository;
    private final AirQualityWebSocketHandler airQualityWebSocketHandler;
    private final AirQualityMapper airQualityMapper;
    private final AirQualityRepository airQualityRepository;

    @Transactional
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "sensor-exchange",type = ExchangeTypes.DIRECT),
            value = @Queue(value = "sensor-queue",
            arguments = {
            @Argument(name = "x-dead-letter-exchange", value = "dlx"),
            @Argument(name = "x-dead-letter-routing-key", value = "dlx.sensor_key")}),
            key = "sensor-key"
    ))

    public void AirQualityReceiver(CitiesDto citiesDto) {

            Cities city = citiesRepository.findById(citiesDto.getId())
                    .orElseThrow(() -> new RuntimeException("City not found"));

            for (SensorDto sensorDto : citiesDto.getSensorDtoList()) {
                Sensor sensor = sensorRepository.findById(sensorDto.getId())
                        .orElseThrow(() -> new RuntimeException("Sensor not found"));

                for (AirQualityDto airQualityDto : sensorDto.getAirQualityDtoList()) {
                    AirQuality airQuality = airQualityMapper.toAirQualityEntity(airQualityDto);
                    airQuality.setSensor(sensor);
                    airQualityRepository.save(airQuality);
                }
            }

            //  DTO для WebSocket (свежие данные)
            CitiesDto freshCitiesDto = citiesMapper.toCityDto(city);
            for (SensorDto sensorDto : freshCitiesDto.getSensorDtoList()) {
                AirQuality latestMeasurement = airQualityRepository
                        .findTopBySensorIdOrderByCreatedDesc(sensorDto.getId());

                if (latestMeasurement != null) {
                    sensorDto.setAirQualityDtoList(List.of(airQualityMapper.toAirQualityDto(latestMeasurement)));
                } else {
                    sensorDto.setAirQualityDtoList(List.of());
                }
            }

            // WebSocket
            airQualityWebSocketHandler.sendToWebSocket(freshCitiesDto);

        }
    }
