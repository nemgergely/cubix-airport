package hu.cubix.airport;

import hu.cubix.airport.service.AirportService;
import hu.cubix.airport.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
@AllArgsConstructor
public class AirportApplication implements CommandLineRunner {

	private PriceService priceService;
	private AirportService airportService;

	public static void main(String[] args) {
		SpringApplication.run(AirportApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(priceService.getFinalPrice(100));
		System.out.println(priceService.getFinalPrice(2000));
		airportService.createFlight(1, 3, "FN111", LocalDateTime.now());
	}
}
