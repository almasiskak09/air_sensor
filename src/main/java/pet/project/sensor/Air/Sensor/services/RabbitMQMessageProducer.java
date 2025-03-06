package pet.project.sensor.Air.Sensor.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import pet.project.sensor.Air.Sensor.dto.CitiesDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQMessageProducer {

    public final RabbitTemplate rabbitTemplate;

    public void sendAirQualityDto(CitiesDto citiesDto) {
        rabbitTemplate.convertAndSend("sensor-exchange","sensor-key",citiesDto);
        log.info("Sent citiesDto to Rabbit: ");
    }
}
