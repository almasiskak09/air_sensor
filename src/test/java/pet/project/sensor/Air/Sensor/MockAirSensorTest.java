package pet.project.sensor.Air.Sensor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pet.project.sensor.Air.Sensor.dto.SensorDto;
import pet.project.sensor.Air.Sensor.entity.Sensor;
import pet.project.sensor.Air.Sensor.mapper.SensorMapper;
import pet.project.sensor.Air.Sensor.repository.SensorRepository;
import pet.project.sensor.Air.Sensor.services.SensorService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class MockAirSensorTest {

    @InjectMocks
    private SensorService sensorService;

    @Mock
    private SensorRepository sensorRepository;

    @Mock
    private SensorMapper sensorMapper;

    @Test
    void getAllSensors(){
        Sensor s1 = new Sensor(1L, "Sensor1", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);
        Sensor s2 = new Sensor(2L, "Sensor2", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);
        Sensor s3 = new Sensor(3L, "Sensor3", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);

        SensorDto sDto1 = new SensorDto(1L, "Sensor1", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);
        SensorDto sDto2 = new SensorDto(2L, "Sensor2", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);
        SensorDto sDto3 = new SensorDto(3L, "Sensor3", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);


        List<Sensor> sensorList = new ArrayList<>();
        sensorList.addAll(List.of(s1,s2,s3));

        List<SensorDto> sensorDtoList = new ArrayList<>();
        sensorDtoList.addAll(List.of(sDto1,sDto2,sDto3));

        when(sensorRepository.findAll()).thenReturn(sensorList);
        when(sensorMapper.toDtoList(sensorList)).thenReturn(sensorDtoList);

        List<SensorDto> result = sensorService.getAllSensors();

        assertNotNull(result);
        assertEquals(sensorList.size(), result.size());
        assertEquals(sensorDtoList,result);

        verify(sensorRepository).findAll();
        verify(sensorMapper).toDtoList(sensorList);
    }


    @Test
    void addSensor(){
        SensorDto sensorDto = new SensorDto(1L, "Added Dto Sensor", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);
        Sensor sensor = new Sensor(1L, "Added Sensor", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);

        when(sensorMapper.toSensor(sensorDto)).thenReturn(sensor);
        when(sensorRepository.save(sensor)).thenReturn(sensor);
        when(sensorMapper.toSensorDto(sensor)).thenReturn(sensorDto);


        SensorDto result = sensorService.addSensor(sensorDto);

        assertNotNull(result);
        assertEquals(sensorDto.getId(), result.getId());
        assertEquals(sensorDto.getLatitude(), result.getLatitude());
    }


    @Test
    void getSensorById(){
        Long findId = 1L;
        Sensor sensor = new Sensor(1L, "Test Sensor", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);
        SensorDto sensorDto = new SensorDto(1L, "Test Sensor", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);

        when(sensorRepository.findById(findId)).thenReturn(Optional.of(sensor));
        when(sensorMapper.toSensorDto(sensor)).thenReturn(sensorDto);

        SensorDto result = sensorService.getSensorById(findId);

        assertNotNull(result);
        assertEquals(sensorDto.getId(), result.getId());

        verify(sensorRepository).findById(findId);
        verify(sensorMapper).toSensorDto(sensor);
    }

    @Test
    void updateSensor(){
        Long findId = 1L;

        Sensor existingSensor = new Sensor(1L, "Sensor", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);
        Sensor updatedSensor = new Sensor(1L, "Updated SensorDto", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);

        SensorDto sentDto = new SensorDto(1L, "Updated SensorDto", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);
        SensorDto updatedSensorDto = new SensorDto(1L, "Updated SensorDto", BigDecimal.valueOf(77), BigDecimal.valueOf(99), null, null);

        when(sensorRepository.findById(findId)).thenReturn(Optional.of(existingSensor));
        when(sensorMapper.toSensor(sentDto)).thenReturn(updatedSensor);
        when(sensorRepository.save(updatedSensor)).thenReturn(updatedSensor);
        when(sensorMapper.toSensorDto(updatedSensor)).thenReturn(updatedSensorDto);

        SensorDto result = sensorService.updateSensor(sentDto);

        assertNotNull(result);
        assertEquals(updatedSensorDto.getId(), result.getId());
        assertEquals(result.getSensorName(), sentDto.getSensorName());


        verify(sensorRepository).findById(findId);
        verify(sensorRepository).save(updatedSensor);
        verify(sensorMapper).toSensor(sentDto);
        verify(sensorMapper).toSensorDto(updatedSensor);
    }

    @Test
    void deleteSensor(){
        Long findId = 1L;

        sensorService.deleteSensor(findId);
        verify(sensorRepository).deleteById(findId);
    }

    @Test
    void getSensorById_NotFound(){
        Long fakeId = 999L;
        when(sensorRepository.findById(fakeId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> sensorService.getSensorById(fakeId));

        verify(sensorRepository ).findById(fakeId);
    }

    @Test
    void deleteSensorById_NotFound(){
        Long fakeId = 999L;
        doThrow(new RuntimeException("Sensor Not Found")).when(sensorRepository).deleteById(fakeId);

        assertThrows(RuntimeException.class, () -> sensorService.deleteSensor(fakeId));
        verify(sensorRepository).deleteById(fakeId);
    }
}
