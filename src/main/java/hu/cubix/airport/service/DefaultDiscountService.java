package hu.cubix.airport.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultDiscountService implements IDiscountService {

    @Override
    public int getDiscountPercent(int totalPrice) {
        return 10;
    }
}
