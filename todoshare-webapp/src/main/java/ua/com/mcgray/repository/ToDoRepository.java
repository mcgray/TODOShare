package ua.com.mcgray.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.mcgray.domain.ToDo;
import ua.com.mcgray.domain.ToDoShareAccount;

/**
 * @author orezchykov
 * @since 15.04.13
 */
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findByCreatedBy(ToDoShareAccount createdBy);
}
