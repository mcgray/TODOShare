package ua.com.mcgray.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.mcgray.domain.User;
import ua.com.mcgray.dto.ToDoForm;
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("todoList", toDoService.getToDos(user.getToDoShareAccount()));
        model.addAttribute("toDoForm", new ToDoForm());
        return "todo-list";
    }

    @RequestMapping(value = "/todo/{toDoId}", method = RequestMethod.GET, produces = "text/html")
    public String edit(@PathVariable("toDoId") @ModelAttribute("toDoForm") ToDoForm toDoForm, Model model) {
        model.addAttribute("toDoForm", toDoForm);
        return "todo-edit";
    }

    @RequestMapping(value = "/todo/{toDoId}", method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid ToDoForm toDoForm, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (bindingResult.hasErrors()) {
            model.addAttribute("statusMessage", "Error!!");
            return "todo-edit";
        } else {
            toDoForm.setOwnerId(user.getToDoShareAccount().getId());
            Long toDoId = toDoService.saveOrUpdateToDo(toDoForm);
            redirectAttributes.addFlashAttribute("statusMessage", "Success!!");
            return "redirect:/todo/" + toDoId;
        }
    }

    @RequestMapping(value = "/todo/", method = RequestMethod.POST, produces = "text/html")
    public String save(@Valid ToDoForm toDoForm, BindingResult bindingResult, ModelMap model, RedirectAttributes redirectAttributes) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (bindingResult.hasErrors()) {
            model.addAttribute("statusMessage", "Error!!");
            return "todo-edit";
        } else {
            toDoForm.setOwnerId(user.getToDoShareAccount().getId());
            Long toDoId = toDoService.saveOrUpdateToDo(toDoForm);
            redirectAttributes.addFlashAttribute("statusMessage", "Success!!");
            return "redirect:/todo/" + toDoId;
        }
    }


}
