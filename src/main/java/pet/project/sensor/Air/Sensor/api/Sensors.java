package pet.project.sensor.Air.Sensor.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.project.sensor.Air.Sensor.dto.CitiesDto;
import pet.project.sensor.Air.Sensor.services.CityService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sensors")
@RequiredArgsConstructor
public class Sensors {

    private final CityService cityService;

    @GetMapping(value = "/all")
    public List<CitiesDto> getAllCities(){
          return cityService.getAllCities();
    }
}
