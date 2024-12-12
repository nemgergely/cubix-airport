package hu.cubix.airport.exception;

public class NonUniqueIataException extends RuntimeException {

    public NonUniqueIataException() {
        super( "IATA must be unique!" );
    }
}
