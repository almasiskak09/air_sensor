package pet.project.sensor.Air.Sensor.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import pet.project.sensor.Air.Sensor.dto.AirQualityDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQMessageProducer {

    public final RabbitTemplate rabbitTemplate;

    public void sendAirQualityDto(AirQualityDto airQualityDto) {
        rabbitTemplate.convertAndSend("sensor-exchange","sensor-key",airQualityDto);
        log.info("Sent air-Quality to Rabbit ");
    }
}
