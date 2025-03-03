package pet.project.sensor.Air.Sensor.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pet.project.sensor.Air.Sensor.entity.Cities;

@Repository
@Transactional
public interface CitiesRepository extends JpaRepository<Cities, Long> {
}
