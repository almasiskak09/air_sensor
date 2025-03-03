package pet.project.sensor.Air.Sensor.mapper;

import org.mapstruct.Mapper;
import pet.project.sensor.Air.Sensor.dto.SensorDto;
import pet.project.sensor.Air.Sensor.entity.Sensor;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SensorMapper {

    SensorDto toSensorDto(Sensor sensor);
    Sensor toSensor(SensorDto sensorDto);
    List<SensorDto> toSensorDtoList(List<Sensor> sensors);
}
