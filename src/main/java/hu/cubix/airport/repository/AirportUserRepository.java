package hu.cubix.airport.repository;

import hu.cubix.airport.model.AirportUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportUserRepository extends JpaRepository<AirportUser, String> {
}
