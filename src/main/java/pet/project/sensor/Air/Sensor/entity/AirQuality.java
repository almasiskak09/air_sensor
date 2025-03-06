package pet.project.sensor.Air.Sensor.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
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

    @Column(name = "air_pollution")
    private int airPollution;

    @Column(name = "air_pressure")
    private BigDecimal airPressure;
    private int noise;

    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

}
