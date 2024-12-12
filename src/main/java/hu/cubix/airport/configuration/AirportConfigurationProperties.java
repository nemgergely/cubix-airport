package hu.cubix.airport.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ConfigurationProperties(prefix = "airport")
@Component
public class AirportConfigurationProperties {
    private Discount discount;

    @Setter
    @Getter
    public static class Discount {
        private Special special;

        @Setter
        @Getter
        public static class Special {
            private int limit;
            private int lowerPercent;
            private int higherPercent;

        }
    }
}
