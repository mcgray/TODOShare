package ua.com.mcgray.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ua.com.mcgray.domain.ToDo;
import ua.com.mcgray.dto.ToDoForm;
import ua.com.mcgray.repository.ToDoRepository;
import ua.com.mcgray.repository.ToDoShareAccountRepository;

/**
 * @author orezchykov
 * @since 10.05.13
 */
@Service
public class ToDoService {

    @Resource
    private ToDoRepository toDoRepository;

    @Resource
    private ToDoShareAccountRepository toDoShareAccountRepository;

    public List<ToDoForm> getToDos() {
        List<ToDoForm> toDoForms = new ArrayList<ToDoForm>();
        for (ToDo toDo : toDoRepository.findAll()) {
			toDoForms.add(new ToDoForm(toDo));
		}
        return toDoForms;
    }


    public ToDoForm getToDoById(Long toDoId) {
        return new ToDoForm(toDoRepository.findOne(toDoId));
    }

    @PreAuthorize("#toDoForm.ownerId == authentication.principal.toDoShareAccount.id")
    public Long saveOrUpdateToDo(final ToDoForm toDoForm) {
        ToDo toDo = null;
        if (toDoForm.getId() == null) {
            toDo = new ToDo();
            toDo.setCreatedBy(toDoShareAccountRepository.findOne(toDoForm.getOwnerId()));
        } else {
            toDo = toDoRepository.findOne(toDoForm.getId());
        }
        toDo.setTitle(toDoForm.getTitle());
        toDo.setNote(toDoForm.getNote());
        toDo.setDueDate(toDoForm.getDueDate());
        toDoRepository.save(toDo);
        return toDo.getId();
    }
}
