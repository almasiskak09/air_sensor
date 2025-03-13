package pet.project.sensor.Air.Sensor.api;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/getAllSensors")
    public List<SensorDto> getAllSensors(){
        return sensorService.getAllSensors();
    }

    @GetMapping(value = "/getSensor/{id}")
    public ResponseEntity<SensorDto> getSensorById(@PathVariable("id") Long id){
        return ResponseEntity.ok(sensorService.getSensorById(id));
    }

    @PostMapping(value = "/addSensor")
    public ResponseEntity<SensorDto> addSensor(@RequestBody SensorDto sensorDto){
        return new ResponseEntity<>(sensorService.addSensor(sensorDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/updateSensor")
    public ResponseEntity<SensorDto> updateSensor(@RequestBody SensorDto sensorDto){
        try {
            sensorService.updateSensor(sensorDto);
            return new ResponseEntity<>(sensorDto, HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteSensor/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Long id ){
        try{
            sensorService.deleteSensor(id);
            return ResponseEntity.noContent().build();
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
