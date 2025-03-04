package pet.project.sensor.Air.Sensor.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pet.project.sensor.Air.Sensor.dto.AirQualityDto;
import pet.project.sensor.Air.Sensor.dto.SensorDto;
import pet.project.sensor.Air.Sensor.entity.AirQuality;
import pet.project.sensor.Air.Sensor.entity.Sensor;

import java.util.List;

@Mapper(componentModel = "spring", uses = SensorMapper.class)
public interface AirQualityMapper {

    @Mapping(source = "sensor.id", target = "sensorId")
    AirQualityDto toAirQualityDto(AirQuality airQuality);

    @Mapping(source = "sensorId", target = "sensor", qualifiedByName = "mapDtoToSensor")
    AirQuality toAirQualityEntity(AirQualityDto airQualityDto);
    List<AirQualityDto> toDtoList(List<AirQuality>airQualityList);

    @Named("mapDtoToSensor")
    default Sensor mapSensor(Long sensorId) {
        if(sensorId == null){
            return null;
        }
        Sensor sensor = new Sensor();
        sensor.setId(sensorId);
        return sensor;
    }

}
