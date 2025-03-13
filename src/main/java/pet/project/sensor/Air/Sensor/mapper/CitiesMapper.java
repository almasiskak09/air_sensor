package pet.project.sensor.Air.Sensor.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pet.project.sensor.Air.Sensor.dto.CitiesDto;
import pet.project.sensor.Air.Sensor.entity.Cities;

import java.util.List;

@Mapper(componentModel = "spring", uses = SensorMapper.class)
public interface CitiesMapper {


    @Mapping(target = "sensorDtoList", source = "sensors")
    CitiesDto toCityDto(Cities cities);

    @Mapping(source = "sensorDtoList", target = "sensors")
    Cities toCity(CitiesDto citiesDto);

    List<CitiesDto> toDtoList (List<Cities> citiesList);
    List<Cities> toEntityList (List<CitiesDto> citiesDtoList);


}
