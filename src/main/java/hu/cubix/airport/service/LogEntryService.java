package hu.cubix.airport.service;

import hu.cubix.airport.model.Airport;
import hu.cubix.airport.model.LogEntry;
import hu.cubix.airport.repository.LogEntryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class LogEntryService {

    private final LogEntryRepository logEntryRepository;

    public void logAirportChange(Airport airport) {
        int dataFromOtherBackend = callOtherBackendSystem();
        logEntryRepository.save(new LogEntry(String.format(
            "Airport with id %d has been modified. New name is: %s. Int from other backend is %d",
            airport.getId(),
            airport.getName(),
            dataFromOtherBackend)));
    }

    public int callOtherBackendSystem() {
        int random = new Random().nextInt(4);
        if (random == 0) {
            throw new RuntimeException();
        }
        return random;
    }
}
