package pet.project.sensor.Air.Sensor.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pet.project.sensor.Air.Sensor.dto.CitiesDto;
import pet.project.sensor.Air.Sensor.dto.SensorDto;
import pet.project.sensor.Air.Sensor.services.CityService;
import pet.project.sensor.Air.Sensor.services.SensorService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sensors")
@RequiredArgsConstructor
public class SensorsApi {

    private final CityService cityService;
    private final SensorService sensorService;

    @GetMapping(value = "/all")
    public List<CitiesDto> getAllCities(){
          return cityService.getAllCities();
    }

    @PostMapping(value = "/addSensor")
    public SensorDto addSensor(@RequestBody SensorDto sensorDto){
        return sensorService.addSensor(sensorDto);
    }

    @PutMapping(value = "/updateSensor")
    public SensorDto updateSensor(@RequestBody SensorDto sensorDto){
        return sensorService.updateSensor(sensorDto);
    }

    @DeleteMapping(value = "/deleteSensor/{id}")
    public void deleteSensor(@PathVariable Long id ){
        sensorService.deleteSensor(id);
    }




}
