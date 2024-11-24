package hu.cubix.airport.configuration;

import hu.cubix.airport.service.DefaultDiscountService;
import hu.cubix.airport.service.IDiscountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!prod")
public class DefaultConfiguration {

    @Bean
    public IDiscountService discountService() {
        return new DefaultDiscountService();
    }
}
