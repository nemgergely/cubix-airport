package hu.cubix.airport.service;

import hu.cubix.airport.exception.NonUniqueIataException;
import hu.cubix.airport.model.Airport;
import hu.cubix.airport.model.Flight;
import hu.cubix.airport.repository.AirportRepository;
import hu.cubix.airport.repository.FlightRepository;
import hu.cubix.airport.specification.FlightSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static hu.cubix.airport.specification.FlightSpecification.*;

@Service
@AllArgsConstructor
public class AirportService {

//    @PersistenceContext
//    EntityManager em;

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;
    private final LogEntryService logEntryService;

    @Transactional
    public Airport create(Airport airport) {
        if (airport.getId() != null && findById(airport.getId()) != null) {
            return null;
        }
        return save(airport);
    }

    @Transactional
    public Airport update(Airport airport) {
        if (findById(airport.getId()) == null) {
            return null;
        }
        logEntryService.logAirportChange(airport);
        return save(airport);
    }

    public List<Airport> findAll() {
//        return em.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
        return airportRepository.findAll();
    }

    public Airport findById(Long id) {
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

    public List<Flight> findFlightsByExample(Flight flight) {
        Long id = flight.getId();
        String flightNumber = flight.getFlightNumber();
        Long takeoffId = null;
        Airport takeoff = flight.getTakeoff();
        if (takeoff != null) {
            takeoffId = takeoff.getId();
        }
        Specification<Flight> specs = Specification.where(null);
        if (id != null) {
            specs = specs.and(hasId(id));
        }
        if (StringUtils.hasLength(flightNumber)) {
            specs = specs.and(flightNumberStartsWith(flightNumber));
        }
        if (takeoffId != null) {
            specs = specs.and(flightHasTakeoffId(takeoffId));
        }

        return flightRepository.findAll(specs);
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
        if (airport.getId() == null) {
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
