package hu.cubix.airport.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PriceServiceNoProdIntTest {

    @Autowired
    PriceService priceService;

    @Test
    void testGetFinalPrice() {
        int result = priceService.getFinalPrice(100);

        assertThat(result).isEqualTo(90);
    }
}
