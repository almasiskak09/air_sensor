package pet.project.sensor.Air.Sensor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "air_quality")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirQuality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int temperature;
    private int humidity;

    @Column(name = "ari_pollution")
    private int ariPollution;

    @Column(name = "air_pressure")
    private double airPressure;
    private int noise;

    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

}
