package ua.com.mcgray.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import ua.com.mcgray.domain.ToDo;
import ua.com.mcgray.dto.ToDoForm;
import ua.com.mcgray.repository.ToDoRepository;

/**
 * @author orezchykov
 * @since 10.05.13
 */
@Service
public class ToDoService {

    @Resource
    private ToDoRepository toDoRepository;

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
}
