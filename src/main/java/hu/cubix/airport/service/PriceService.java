package hu.cubix.airport.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PriceService {

    private final IDiscountService discountService;

    public int getFinalPrice(int price) {
        return (int)(price / 100.0 * (100 - discountService.getDiscountPercent(price)));
    }
}
