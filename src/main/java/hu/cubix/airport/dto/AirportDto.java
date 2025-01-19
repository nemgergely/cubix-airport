package hu.cubix.airport.dto;

import jakarta.validation.constraints.NotEmpty;

public record AirportDto(Long id, @NotEmpty String name, @NotEmpty String iata) {

    public AirportDto(String name, String iata) {
        this(null, name, iata);
    }
}
