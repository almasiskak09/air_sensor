package pet.project.sensor.Air.Sensor.mapper;


import org.mapstruct.Mapper;
import pet.project.sensor.Air.Sensor.dto.AirQualityDto;
import pet.project.sensor.Air.Sensor.entity.AirQuality;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirQualityMapper {

    AirQualityDto toAirQualityDto(AirQuality airQuality);
    AirQuality toAirQualityEntity(AirQualityDto airQualityDto);
    List<AirQualityDto> toDtoList(List<AirQuality>airQualityList);
}
