package ua.com.mcgray.service;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.com.mcgray.domain.ToDo;
import ua.com.mcgray.dto.ToDoDocument;
import ua.com.mcgray.repository.solr.ToDoDocumentRepository;

/**
 * @author orezchykov
 * @since 23.05.13
 */
@Service
public class ToDoDocumentService {

    @Resource
    private ToDoDocumentRepository toDoDocumentRepository;

    public Page<ToDoDocument> search(final String searchString, final int pageNumber, final int pageSize) {

        return toDoDocumentRepository.findByContent(searchString.replaceAll("\\s+",","), new PageRequest(pageNumber, pageSize));
    }

    public void save(ToDo toDo) {
        try {
            toDoDocumentRepository.save(convert(toDo));
        } catch (Exception e) {
            //System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    private  ToDoDocument convert(final ToDo toDo) {
        StringBuilder content = new StringBuilder();
        content.append(toDo.getId()).append(" ")
                .append(toDo.getTitle())
                .append(" ")
                .append(toDo.getNote())
                .append(" ")
                .append(toDo.getDueDate())
                .append(" ");
        return new ToDoDocument("todo_" + toDo.getId(), toDo.getId(), toDo.getTitle(), content.toString());
    }




    
}
