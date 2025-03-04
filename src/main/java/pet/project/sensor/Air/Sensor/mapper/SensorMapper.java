package pet.project.sensor.Air.Sensor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pet.project.sensor.Air.Sensor.dto.SensorDto;
import pet.project.sensor.Air.Sensor.entity.Cities;
import pet.project.sensor.Air.Sensor.entity.Sensor;

import java.util.List;

@Mapper(componentModel = "spring", uses = AirQualityMapper.class)
public interface SensorMapper {

    @Mapping(source = "city.id", target = "cityId")
    @Mapping(source = "airQualityList", target = "airQualityDtoList")
    SensorDto toSensorDto(Sensor sensor);

    @Mapping(source = "cityId", target = "city", qualifiedByName = "mapCity")// Маппинг cityId -> Cities
    @Mapping(source = "airQualityDtoList", target = "airQualityList") //
    Sensor toSensor(SensorDto sensorDto);

    List<SensorDto> toDtoList(List<Sensor> sensors);

    List<Sensor> toEntityList(List<SensorDto> sensorDtoList);

    @Named("mapCity")
    default Cities mapCity(Long cityId) {
        if (cityId == null) {
            return null;
        }
        Cities cities = new Cities();
        cities.setId(cityId);
        return cities;
    }

}
