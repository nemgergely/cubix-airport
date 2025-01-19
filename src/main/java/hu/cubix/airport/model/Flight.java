package hu.cubix.airport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Flight {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Airport takeoff;
    @ManyToOne
    private Airport landing;
    private String flightNumber;
    private LocalDateTime takeoffTime;

    public Flight(Airport takeoff, Airport landing, String flightNumber, LocalDateTime takeoffTime) {
        this.takeoff = takeoff;
        this.landing = landing;
        this.flightNumber = flightNumber;
        this.takeoffTime = takeoffTime;
    }
}
