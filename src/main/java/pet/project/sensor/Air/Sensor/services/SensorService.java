package pet.project.sensor.Air.Sensor.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pet.project.sensor.Air.Sensor.dto.SensorDto;
import pet.project.sensor.Air.Sensor.entity.Sensor;
import pet.project.sensor.Air.Sensor.mapper.SensorMapper;
import pet.project.sensor.Air.Sensor.repository.SensorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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
        Sensor sensor = sensorMapper.toSensor(sensorDto);
        sensorRepository.save(sensor);
        return sensorMapper.toSensorDto(sensor);
    }

    public SensorDto updateSensor(SensorDto sensorDto) {
        Optional<Sensor> foundSensor = sensorRepository.findById(sensorDto.getId());
        if (foundSensor.isPresent()) {
            Sensor updatedSensor = sensorRepository.save(sensorMapper.toSensor(sensorDto));
            return sensorMapper.toSensorDto(updatedSensor);
        }
        else {
            log.error("Данного сенсора по id: "+sensorDto.getId()+" не существует");
            return null;
        }
    }


    public void deleteSensor(Long id) {
        sensorRepository.deleteById(id);
    }
}
