package hu.cubix.airport.controller;

import hu.cubix.airport.dto.AirportDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    private final Map<Long, AirportDto> airports = new HashMap<>();

    {
        airports.put(1L, new AirportDto(1, "Budapest Liszt Ferenc International", "BUD"));
    }

    @GetMapping
    public ResponseEntity<List<AirportDto>> findAll() {
        return ResponseEntity.ok(new ArrayList<>(airports.values()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportDto> findById(@PathVariable long id) {
        AirportDto airport = airports.get(id);
        if (airport == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(airport);
    }

    @PostMapping
    public ResponseEntity<AirportDto> create(@RequestBody AirportDto airport) {
        if (airports.containsKey(airport.getId())) {
            return ResponseEntity.badRequest().build();
        }
        airports.put(airport.getId(), airport);
        return ResponseEntity.ok(airport);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportDto> update(@PathVariable long id, @RequestBody AirportDto airport) {
        airport.setId(id);
        if (!airports.containsKey(airport.getId())) {
            return ResponseEntity.notFound().build();
        }
        airports.put(id, airport);
        return ResponseEntity.ok(airport);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        airports.remove(id);
    }
}
