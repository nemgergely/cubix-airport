package hu.cubix.airport.mapper;

import hu.cubix.airport.dto.AirportDto;
import hu.cubix.airport.model.Airport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirportMapper {

    AirportDto airportToDto(Airport airport);
    List<AirportDto> airportsToDtos(List<Airport> airports);
    Airport dtoToAirport(AirportDto airportDto);
}
