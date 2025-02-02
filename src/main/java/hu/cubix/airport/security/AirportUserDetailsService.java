package hu.cubix.airport.security;

import hu.cubix.airport.model.AirportUser;
import hu.cubix.airport.repository.AirportUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AirportUserDetailsService implements UserDetailsService {

    private final AirportUserRepository airportUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AirportUser airportUser = airportUserRepository.findById(username)
            .orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(username, airportUser.getPassword(),
            airportUser.getRoles().stream().map(SimpleGrantedAuthority::new).toList());
    }
}
