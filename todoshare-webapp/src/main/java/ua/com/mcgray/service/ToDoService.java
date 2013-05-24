package ua.com.mcgray.service;

import java.util.List;

import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import ua.com.mcgray.domain.ToDo;
import ua.com.mcgray.domain.ToDoShareAccount;
import ua.com.mcgray.dto.ToDoForm;

/**
 * @author orezchykov
 * @since 18.05.13
 */
public interface ToDoService {
    List<ToDoForm> getToDos(ToDoShareAccount toDoShareAccount);

    ToDoForm getToDoById(Long toDoId);

    @PreAuthorize("#toDoForm.ownerId == authentication.principal.toDoShareAccount.id")
    Long saveOrUpdateToDo(ToDoForm toDoForm);

    void delete(ToDoForm toDoForm);

    Page<ToDo> findAll(Predicate predicate, Pageable pageable);
}
