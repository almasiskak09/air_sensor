package pet.project.sensor.Air.Sensor.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pet.project.sensor.Air.Sensor.dto.SensorDto;
import pet.project.sensor.Air.Sensor.entity.Sensor;
import pet.project.sensor.Air.Sensor.mapper.SensorMapper;
import pet.project.sensor.Air.Sensor.repository.SensorRepository;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final SensorMapper sensorMapper;

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
