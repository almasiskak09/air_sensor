package pet.project.sensor.Air.Sensor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SensorController {

    @GetMapping(value = "/")
    public String home() {
        return "sensors";
    }
}
