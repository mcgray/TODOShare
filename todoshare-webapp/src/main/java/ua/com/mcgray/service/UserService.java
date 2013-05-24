package ua.com.mcgray.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.mcgray.domain.User;
import ua.com.mcgray.repository.jpa.UserRepository;

/**
 * @author orezchykov
 * @since 15.04.13
 */
@Service
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmailAddress(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with such credentials was not found!");
        }
        return user;
    }
}
