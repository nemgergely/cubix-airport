package hu.cubix.airport.service;

import hu.cubix.airport.exception.NonUniqueIataException;
import hu.cubix.airport.model.Airport;
import hu.cubix.airport.model.Flight;
import hu.cubix.airport.repository.AirportRepository;
import hu.cubix.airport.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class AirportService {

//    @PersistenceContext
//    EntityManager em;

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    @Transactional
    public Airport create(Airport airport) {
        if (findById(airport.getId()) != null) {
            return null;
        }
        return save(airport);
    }

    @Transactional
    public Airport update(Airport airport) {
        if (findById(airport.getId()) == null) {
            return null;
        }
        return save(airport);
    }

    public List<Airport> findAll() {
//        return em.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
        return airportRepository.findAll();
    }

    public Airport findById(long id) {
//        return em.find(Airport.class, id);
        return airportRepository.findById(id).orElse(null);
    }

    @Transactional
    public void delete(long id) {
//        em.remove(findById(id));
        airportRepository.deleteById(id);
    }

    @Transactional
    public Flight createFlight(long takeoffId, long landingId, String flightNumber, LocalDateTime takeoffTime) {
        Airport takeoff = airportRepository.findById(takeoffId).get();
        Airport landing = airportRepository.findById(landingId).get();
        Flight flight = new Flight(takeoff, landing, flightNumber, takeoffTime);
        return flightRepository.save(flight);
    }

    private Airport save(Airport airport) {
        throwIfNonUniqueIata(airport);
//        if (airport.getId() == 0) {
//            em.persist(airport);
//        } else {
//            airport = em.merge(airport);
//        }
        return airportRepository.save(airport);
    }

    private void throwIfNonUniqueIata(Airport airport) {
        long count = 0;
        if (airport.getId() == 0) {
//            count = (long) em.createNamedQuery("Airport.countByIata")
//                .setParameter("iata", airport.getIata())
//                .getSingleResult();
            count = airportRepository.countByIata(airport.getIata());
        } else {
//            count = (long) em.createNamedQuery("Airport.countByIataAndIdNot")
//                .setParameter("iata", airport.getIata())
//                .setParameter("id", airport.getId())
//                .getSingleResult();
            count = airportRepository.countByIataAndIdNot(airport.getIata(), airport.getId());
        }
        if (count > 0) {
            throw new NonUniqueIataException();
        }
    }
}
