package hu.cubix.airport.service;

import org.springframework.stereotype.Service;

@Service
public class PriceService {

    private IDiscountService discountService;

    public PriceService(IDiscountService discountService) {
        this.discountService = discountService;
    }

    public int getFinalPrice(int price) {
        return (int)(price / 100.0 * (100 - discountService.getDiscountPercent(price)));
    }
}
