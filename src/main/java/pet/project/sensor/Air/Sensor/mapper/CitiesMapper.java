package pet.project.sensor.Air.Sensor.mapper;

import org.mapstruct.Mapper;
import pet.project.sensor.Air.Sensor.dto.CitiesDto;
import pet.project.sensor.Air.Sensor.entity.Cities;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitiesMapper {

    CitiesDto toCityDto(Cities cities);
    Cities toCity(CitiesDto citiesDto);
    List<CitiesDto> toDtoList (List<Cities> citiesList);
}
