package pet.project.sensor.Air.Sensor.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import pet.project.sensor.Air.Sensor.dto.CitiesDto;
import pet.project.sensor.Air.Sensor.entity.Cities;
import pet.project.sensor.Air.Sensor.mapper.CitiesMapper;
import pet.project.sensor.Air.Sensor.repository.AirQualityRepository;
import pet.project.sensor.Air.Sensor.repository.CitiesRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityService {

    private final CitiesRepository citiesRepository;
    private final CitiesMapper citiesMapper;

    @Transactional
    public List<CitiesDto> getAllCities(){
        List<Cities> cities =  citiesRepository.findAll();
        List<CitiesDto> citiesDtos = citiesMapper.toDtoList(cities);
        return citiesDtos;
    }



}



