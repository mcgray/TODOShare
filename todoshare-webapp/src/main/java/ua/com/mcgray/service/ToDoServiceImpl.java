package ua.com.mcgray.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ua.com.mcgray.domain.ToDo;
import ua.com.mcgray.domain.ToDoShareAccount;
import ua.com.mcgray.dto.ToDoForm;
import ua.com.mcgray.repository.jpa.ToDoRepository;
import ua.com.mcgray.repository.jpa.ToDoShareAccountRepository;

/**
 * @author orezchykov
 * @since 10.05.13
 */
@Service
public class ToDoServiceImpl implements ToDoService {

    @Resource
    private ToDoRepository toDoRepository;

    @Resource
    private ToDoShareAccountRepository toDoShareAccountRepository;

    @Override
    public List<ToDoForm> getToDos(ToDoShareAccount toDoShareAccount) {
        List<ToDoForm> toDoForms = new ArrayList<ToDoForm>();
        for (ToDo toDo : toDoRepository.findByCreatedBy(toDoShareAccount)) {
			toDoForms.add(new ToDoForm(toDo));
		}
        return toDoForms;
    }


    @Override
    public ToDoForm getToDoById(Long toDoId) {
        return new ToDoForm(toDoRepository.findOne(toDoId));
    }

    @Override
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

    @Override
    @PreAuthorize("#toDoForm.ownerId == authentication.principal.toDoShareAccount.id")
    public void delete(final ToDoForm toDoForm) {
        toDoRepository.delete(toDoForm.getId());
    }

    @Override
    public Page<ToDo> findAll(final Predicate predicate, final Pageable pageable) {
        return toDoRepository.findAll(predicate, pageable);

    }
}
