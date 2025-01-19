package hu.cubix.airport.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class LogEntry {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime ts;
    private String description;

    public LogEntry(String description) {
        this.ts = LocalDateTime.now();
        this.description = description;
    }
}
