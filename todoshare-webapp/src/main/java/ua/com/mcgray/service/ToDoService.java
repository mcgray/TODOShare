package ua.com.mcgray.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import ua.com.mcgray.domain.ToDo;
import ua.com.mcgray.repository.ToDoRepository;

/**
 * @author orezchykov
 * @since 10.05.13
 */
@Service
public class ToDoService {

    @Resource
    private ToDoRepository toDoRepository;

    public List<ToDo> getToDos() {
        return toDoRepository.findAll();
    }


}
