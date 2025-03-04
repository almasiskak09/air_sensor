package pet.project.sensor.Air.Sensor.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pet.project.sensor.Air.Sensor.dto.AirQualityDto;
import pet.project.sensor.Air.Sensor.dto.CitiesDto;
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

    private final SensorRepository sensorRepository;
    private final CitiesRepository citiesRepository;
    private final CitiesMapper citiesMapper;
    private final AirQualityWebSocketHandler airQualityWebSocketHandler;
    private final AirQualityRepository airQualityRepository;
    private final AirQualityMapper airQualityMapper;


    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "sensor-exchange",type = ExchangeTypes.DIRECT),
            value = @Queue(value = "sensor-queue"),
            key = "sensor-key"
    ))

    public void AirQualityReceiver(AirQualityDto airQualityDto) {
        log.info("RabbitMQMessageListener AirQualityReceiver " + airQualityDto.getSensorId());

        Sensor sensor = sensorRepository.findById(airQualityDto.getSensorId())
                .orElseThrow(() -> new RuntimeException("Sensor not found"));

        AirQuality savedAirQuality = airQualityRepository.save(airQualityMapper.toAirQualityEntity(airQualityDto));

        airQualityDto.setId(savedAirQuality.getId());
        Cities cities = citiesRepository.findById(sensor.getCity().getId())
                .orElseThrow(() -> new RuntimeException("City not found"));
        CitiesDto citiesDto = citiesMapper.toCityDto(cities);

        // Оставляем только последнее измерение для текущего сенсора
        citiesDto.getSensorDtoList().forEach(sensorDto -> {
            if(sensorDto.getId().equals(airQualityDto.getSensorId())){
                sensorDto.setAirQualityDtoList(List.of(airQualityDto));
            }
        });

        airQualityWebSocketHandler.sendToWebSocket(citiesDto);

    }


}
