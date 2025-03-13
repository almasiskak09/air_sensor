package pet.project.sensor.Air.Sensor;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pet.project.sensor.Air.Sensor.api.SensorsApi;
import pet.project.sensor.Air.Sensor.dto.CitiesDto;
import pet.project.sensor.Air.Sensor.dto.SensorDto;
import pet.project.sensor.Air.Sensor.repository.SensorRepository;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class AirSensorApplicationTests extends AbstractTestIntegration {

	@Autowired
	private SensorsApi sensorsApi;

	@Autowired
	private SensorRepository sensorRepository;

	@AfterEach
	void cleanup() {
		sensorRepository.deleteAll();
	}


	@Test
	void getAllCities(){
		List<CitiesDto> citiesList = sensorsApi.getAllCities();
		Assertions.assertNotNull(citiesList);
		Assertions.assertTrue(citiesList.size() > 0);

		citiesList.forEach(city -> {
			Assertions.assertNotNull(city.getId());
			Assertions.assertNotNull(city.getCityName());
		});
	}

	@Test
	@Transactional
	void getAllSensors() {
		List<SensorDto> sensorDtoList = sensorsApi.getAllSensors();
		Assertions.assertNotNull(sensorDtoList);
		Assertions.assertFalse(sensorDtoList.isEmpty());
		Assertions.assertTrue(sensorDtoList.size() > 0);
	}

	@Test
	@Transactional
	void getSensorById() {
		Long sensorId = 1L;
		ResponseEntity<SensorDto> sensorDto = sensorsApi.getSensorById(sensorId);

		Assertions.assertEquals(HttpStatus.OK, sensorDto.getStatusCode());
		Assertions.assertNotNull(sensorDto.getBody());
		Assertions.assertTrue(sensorDto.getBody().getSensorName().equals("ALMATY_A01K"));

	}

	@Test
	void addSensor() {
		SensorDto sensor = new SensorDto(null,"Test Sensor", BigDecimal.valueOf(34.434323),BigDecimal.valueOf(34.434323),1L,null);
		ResponseEntity<SensorDto> response = sensorsApi.addSensor(sensor);
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assertions.assertNotNull(response.getBody());
	}

	@Test
	@Transactional
	void updateSensor() {
		Long sensorId = 1L;
		SensorDto updatedSensor = new SensorDto(sensorId,"UpdatedSensor", BigDecimal.valueOf(34.434323),BigDecimal.valueOf(34.434323),1L,null);
		ResponseEntity<SensorDto> response = sensorsApi.updateSensor(updatedSensor);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertNotNull(response.getBody());
	}




}
