package pet.project.sensor.Air.Sensor.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pet.project.sensor.Air.Sensor.entity.Cities;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface CitiesRepository extends JpaRepository<Cities, Long> {

}


