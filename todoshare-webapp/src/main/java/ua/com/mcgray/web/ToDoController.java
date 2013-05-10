package ua.com.mcgray.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.mcgray.service.ToDoService;

/**
 * @author orezchykov
 * @since 09.05.13
 */
@Controller
public class ToDoController {

    @Resource
    private ToDoService toDoService;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "text/html")
    public String dashboard() {
        return "dashboard";
    }

    @RequestMapping(value = "/todo/list", method = RequestMethod.GET, produces = "text/html")
    public String list(Model model) {
        model.addAttribute("todoList", toDoService.getToDos());
        return "todo-list";
    }


}
