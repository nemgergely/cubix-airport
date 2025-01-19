package hu.cubix.airport.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(of = "id")
//@NamedQuery(
//    name = "Airport.countByIata",
//    query = "SELECT COUNT(a) FROM Airport a WHERE a.iata = :iata"
//)
//@NamedQuery(
//    name = "Airport.countByIataAndIdNot",
//    query = "SELECT COUNT(a) FROM Airport a WHERE a.iata = :iata AND a.id != :id"
//)
public class Airport {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Size(min = 2, max = 5)
    private String iata;

    public Airport(String name, String iata) {
        this.name = name;
        this.iata = iata;
    }
}
