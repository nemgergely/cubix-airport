package hu.cubix.airport.controller;

import hu.cubix.airport.model.Airport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AirportTLController {

    private final List<Airport> airports = new ArrayList<>();

    {
        airports.add(new Airport(1L, "Budapest Liszt Ferenc International", "BUD"));
    }

    @GetMapping("/")
    public String home(Map<String, Object> model) {
        model.put("airports", airports);
        model.put("newAirport", new Airport());
        return "index";
    }

    @PostMapping("/airport")
    public String createAirport(Airport airport) {
        airports.add(airport);
        return "redirect:/";
    }
}
