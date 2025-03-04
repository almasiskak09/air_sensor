package pet.project.sensor.Air.Sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AirSensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirSensorApplication.class, args);
	}

}
