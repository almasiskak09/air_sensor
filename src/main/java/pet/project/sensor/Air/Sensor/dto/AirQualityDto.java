package pet.project.sensor.Air.Sensor.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirQualityDto {

    private Long id;
    private int temperature;
    private int humidity;
    private int ariPollution;
    private double airPressure;
    private int noise;
    private LocalDateTime created;
    private Long sensorId;
}
