package hu.cubix.airport.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Setter
public class MyError {

    private String message;
    private List<FieldError> fieldErrors;

    public MyError(String message) {
        this.message = message;
    }
}
