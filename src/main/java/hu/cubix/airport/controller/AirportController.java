package hu.cubix.airport.controller;

import hu.cubix.airport.dto.AirportDto;
import hu.cubix.airport.mapper.AirportMapper;
import hu.cubix.airport.model.Airport;
import hu.cubix.airport.service.AirportService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/airports")
public class AirportController {

    private final AirportService airportService;
    private final AirportMapper airportMapper;

    @GetMapping
    public List<AirportDto> findAll() {
        List<Airport> allAirports = airportService.findAll();
        return airportMapper.airportsToDtos(allAirports);
    }

    @GetMapping("/{id}")
    public AirportDto findById(@PathVariable long id) {
        Airport airport = airportService.findById(id);
        if (airport == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return airportMapper.airportToDto(airport);
    }

    @PostMapping
    public AirportDto create(@RequestBody @Valid AirportDto airportDto) {
        Airport airport = airportMapper.dtoToAirport(airportDto);
        Airport savedAirport = airportService.create(airport);
        if (savedAirport == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return airportMapper.airportToDto(savedAirport);
    }

    @PutMapping
    public AirportDto update(@RequestBody @Valid AirportDto airportDto) {
        airportDto = new AirportDto(airportDto.id(), airportDto.name(), airportDto.iata());
        Airport airport = airportMapper.dtoToAirport(airportDto);
        Airport updatedAirport = airportService.update(airport);

        return airportMapper.airportToDto(updatedAirport);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
       airportService.delete(id);
    }
}
