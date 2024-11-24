package hu.cubix.airport.configuration;

import hu.cubix.airport.service.IDiscountService;
import hu.cubix.airport.service.SpecialDiscountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfiguration {

    @Bean
    public IDiscountService discountService() {
        return new SpecialDiscountService();
    }
}
