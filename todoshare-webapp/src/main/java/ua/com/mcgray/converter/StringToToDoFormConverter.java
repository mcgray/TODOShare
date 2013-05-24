package ua.com.mcgray.converter;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;
import ua.com.mcgray.dto.ToDoForm;
import ua.com.mcgray.repository.jpa.ToDoRepository;

/**
 * @author orezchykov
 * @since 10.05.13
 */
public class StringToToDoFormConverter implements Converter<String, ToDoForm> {

    @Resource
    private ToDoRepository toDoRepository;


    @Override
    public ToDoForm convert(final String toDoId) {
        return new ToDoForm(toDoRepository.findOne(Long.valueOf(toDoId)));
    }
}
