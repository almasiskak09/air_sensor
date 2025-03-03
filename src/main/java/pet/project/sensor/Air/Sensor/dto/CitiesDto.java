package pet.project.sensor.Air.Sensor.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CitiesDto {

    private Long id;
    private String cityName;
    private String cityCode;
    private List<SensorDto> sensors;

}
