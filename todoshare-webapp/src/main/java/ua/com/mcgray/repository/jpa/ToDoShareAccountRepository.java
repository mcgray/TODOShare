package ua.com.mcgray.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.mcgray.domain.ToDoShareAccount;

/**
 * @author orezchykov
 * @since 13.04.13
 */
public interface ToDoShareAccountRepository extends JpaRepository<ToDoShareAccount, Long> {

}
