package pet.project.sensor.Air.Sensor;

import jakarta.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pet.project.sensor.Air.Sensor.api.SensorsApi;
import pet.project.sensor.Air.Sensor.dto.CitiesDto;
import pet.project.sensor.Air.Sensor.dto.SensorDto;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@Transactional
public class AirSensorTest  extends AbstractTestIntegration{

    @Autowired
    public SensorsApi sensorsApi;

    @Test
    public void getAllCities(){
        List<CitiesDto> citiesDtoList = sensorsApi.getAllCities();

        assertNotNull(citiesDtoList);
        assertTrue(citiesDtoList.size() > 0);
       assertEquals("Almaty",citiesDtoList.getFirst().getCityName());
    }

    @Test
    @Transactional
    public void getAllSensors() {
        List<SensorDto> sensors = sensorsApi.getAllSensors();
        assertNotNull(sensors);
        assertTrue(sensors.size() > 0);
    }


    @Test
    public void getSensorById() {
        Long id = 1L;
        SensorDto sensorDto = sensorsApi.getSensorById(id).getBody();

        assertNotNull(sensorDto);
        assertTrue(sensorDto.getSensorName().equals("ALMATY_A01K"));

    }

    @Test
    public void addSensor() {
        SensorDto addedSensor = new SensorDto(null,"Added Sensor", BigDecimal.valueOf(77.2323),BigDecimal.valueOf(99),1L,null);
        sensorsApi.addSensor(addedSensor);

        assertTrue(addedSensor.getSensorName().equals("Added Sensor"));
        assertNotNull(addedSensor.getSensorName());
        assertTrue(addedSensor.getCityId().equals(1L));

    }

    @Test
    public void updateSensor() {
        Long id = 1L;
        Long cityId = 1L;
        SensorDto updatedSensor = new SensorDto(id,"Test Sensor", BigDecimal.valueOf(23.2323),BigDecimal.valueOf(32.3232),cityId,null);

        sensorsApi.updateSensor(updatedSensor);

        assertNotNull(updatedSensor);
        SensorDto newSensor = sensorsApi.getSensorById(id).getBody();
        assertTrue(newSensor.getSensorName().equals("Test Sensor"));
    }


}
