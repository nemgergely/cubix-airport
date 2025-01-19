package hu.cubix.airport.controller;

import hu.cubix.airport.dto.AirportDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class AirportControllerIntTest {

    @Autowired
    WebTestClient webTestClient;

    public static final String API_AIRPORTS = "/api/airports";

    @Test
    void testThatCreatedAirportIsListed() {
        List<AirportDto> airportsBefore = getAllAirports();
        AirportDto newAirport = new AirportDto("test name", "iata5");

        createAirport(newAirport);
        List<AirportDto> airportsAfter = getAllAirports();

        assertThat(airportsAfter.subList(0, airportsBefore.size()))
            .usingRecursiveFieldByFieldElementComparator()
            .containsExactlyElementsOf(airportsBefore);
        assertThat(airportsAfter.get(airportsAfter.size() - 1))
            .usingRecursiveComparison()
            .ignoringFields("id")
            .isEqualTo(newAirport);
    }

    private void createAirport(AirportDto newAirport) {
        webTestClient
            .post()
            .uri(API_AIRPORTS)
            .bodyValue(newAirport)
            .exchange()
            .expectStatus().isOk();
    }

    private List<AirportDto> getAllAirports() {
        List<AirportDto> allAirports = webTestClient
            .get()
            .uri(API_AIRPORTS)
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(AirportDto.class)
            .returnResult()
            .getResponseBody();

        allAirports.sort(Comparator.comparing(AirportDto::id));
        return allAirports;
    }
}
