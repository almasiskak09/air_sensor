package pet.project.sensor.Air.Sensor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorDto {

    private Long id;
    private String sensorName;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long cityId;
    private List<AirQualityDto> airQualityDtoList;
}

