package ua.com.mcgray.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.mcgray.domain.ToDo;
import ua.com.mcgray.service.ToDoDocumentService;

/**
 * @author orezchykov
 * @since 23.05.13
 */
@Component
@Aspect
public class SolrSearchAspect {

    @Autowired
    private ToDoDocumentService toDoDocumentService;

    @After(value = "(execution(* ua.com.mcgray.repository.jpa" +
            ".ToDoRepository.save(..)))" +
            "&& args(entity, ..)")
    public void updateIndex(ToDo entity) {
        toDoDocumentService.save(entity);
    }

}
