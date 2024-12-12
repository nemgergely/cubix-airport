package hu.cubix.airport.service;

import hu.cubix.airport.exception.NonUniqueIataException;
import hu.cubix.airport.model.Airport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AirportService {

    private final Map<Long, Airport> airports = new HashMap<>();

    public Airport create(Airport airport) {
        if (findById(airport.getId()) != null) {
            return null;
        }
        return save(airport);
    }

    public Airport update(Airport airport) {
        if (findById(airport.getId()) == null) {
            return null;
        }
        return save(airport);
    }

    public List<Airport> findAll() {
        return new ArrayList<>(airports.values());
    }

    public Airport findById(long id) {
        return airports.get(id);
    }

    public void delete(long id) {
        airports.remove(id);
    }

    private Airport save(Airport airport) {
        throwIfNonUniqueIata(airport);
        airports.put(airport.getId(), airport);
        return airport;
    }

    private void throwIfNonUniqueIata(Airport airport) {
        if (airports.values().stream().anyMatch(a -> a.getIata().equals(airport.getIata()))) {
            throw new NonUniqueIataException();
        }
    }
}
