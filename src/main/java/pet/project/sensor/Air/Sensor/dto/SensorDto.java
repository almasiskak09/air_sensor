package pet.project.sensor.Air.Sensor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorDto {

    private Long id;
    private String sensorName;
    private int latitude;
    private int longitude;
    private Long cityId;
    private List<AirQualityDto> airQualityDtos;
}

