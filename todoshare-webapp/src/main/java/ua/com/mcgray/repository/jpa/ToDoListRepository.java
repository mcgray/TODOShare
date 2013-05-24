package ua.com.mcgray.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.mcgray.domain.ToDoList;

/**
 * @author orezchykov
 * @since 15.04.13
 */
public interface ToDoListRepository extends JpaRepository<ToDoList, Long> {

}
