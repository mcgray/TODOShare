package ua.com.mcgray.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.mcgray.domain.User;

/**
 * @author orezchykov
 * @since 13.04.13
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAddress(String email);
}
