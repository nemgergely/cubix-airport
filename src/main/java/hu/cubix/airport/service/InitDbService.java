package hu.cubix.airport.service;

import hu.cubix.airport.model.AirportUser;
import hu.cubix.airport.repository.AirportUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@AllArgsConstructor
public class InitDbService {

    private final AirportUserRepository airportUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUsersIfNeeded() {
        if (!airportUserRepository.existsById("admin")) {
            airportUserRepository.save(
                new AirportUser("admin", passwordEncoder.encode("pass"), Set.of("admin", "user")));
        }
        if (!airportUserRepository.existsById("user")) {
            airportUserRepository.save(
                new AirportUser("user", passwordEncoder.encode("pass"), Set.of("user")));
        }
    }
}
