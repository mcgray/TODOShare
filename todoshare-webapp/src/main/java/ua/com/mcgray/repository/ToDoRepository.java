package ua.com.mcgray.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.mcgray.domain.ToDo;

/**
 * @author orezchykov
 * @since 15.04.13
 */
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

}
