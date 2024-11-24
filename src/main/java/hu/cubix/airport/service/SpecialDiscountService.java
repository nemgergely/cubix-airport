package hu.cubix.airport.service;

import hu.cubix.airport.configuration.AirportConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static hu.cubix.airport.configuration.AirportConfigurationProperties.Discount.Special;

@Service
public class SpecialDiscountService  implements IDiscountService {

    @Autowired
    private AirportConfigurationProperties airportConfigurationProperties;

    @Override
    public int getDiscountPercent(int totalPrice) {
        Special special = airportConfigurationProperties.getDiscount().getSpecial();
        return totalPrice > special.getLimit() ? special.getHigherPercent() : special.getLowerPercent();
    }
}
