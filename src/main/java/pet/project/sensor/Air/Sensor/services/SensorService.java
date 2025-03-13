package pet.project.sensor.Air.Sensor.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.project.sensor.Air.Sensor.dto.SensorDto;
import pet.project.sensor.Air.Sensor.entity.Sensor;
import pet.project.sensor.Air.Sensor.mapper.SensorMapper;
import pet.project.sensor.Air.Sensor.repository.SensorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

    public List<SensorDto> getAllSensors() {
        List<Sensor> sensorList = sensorRepository.findAll();
        return sensorMapper.toDtoList(sensorList);
    }

//    public Optional<SensorDto> getSensorById(Long id) {
//        return sensorRepository.findById(id)
//                .map(sensorMapper::toSensorDto);
//
//    }

    public SensorDto getSensorById(Long id) {
       Sensor sensor = sensorRepository.findById(id)
               .orElseThrow(() -> new EntityNotFoundException("Сенсор с id="+ id + " не найден"));
       return sensorMapper.toSensorDto(sensor);
    }



    public SensorDto addSensor(SensorDto sensorDto) {
        Sensor addedSensor = sensorMapper.toSensor(sensorDto);
        sensorRepository.save(addedSensor);
        return sensorMapper.toSensorDto(addedSensor);
    }

    public SensorDto updateSensor(SensorDto sensorDto) {
        Sensor sensor = sensorMapper.toSensor(sensorDto);
        sensorRepository.save(sensor);
        return sensorMapper.toSensorDto(sensor);
    }

    public void deleteSensor(Long id) {
        sensorRepository.deleteById(id);
    }
}
