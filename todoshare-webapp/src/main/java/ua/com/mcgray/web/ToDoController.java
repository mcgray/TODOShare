package ua.com.mcgray.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.mcgray.domain.QToDo;
import ua.com.mcgray.domain.ToDo;
import ua.com.mcgray.domain.ToDoShareAccount;
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

    @RequestMapping(value = "/todo/listdata", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Page<ToDo> listData(Pageable pageable, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        final Predicate predicate = constructPredicate(user.getToDoShareAccount());
        return toDoService.findAll(predicate, pageable);
    }

    public static Predicate constructPredicate(ToDoShareAccount toDoShareAccount) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QToDo.toDo.createdBy.eq(toDoShareAccount));
        return booleanBuilder;
    }

    @RequestMapping(value = "/todo/list", method = RequestMethod.GET, produces = "text/html")
    public String list(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
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
    public String update(@Valid ToDoForm toDoForm, BindingResult bindingResult, Authentication authentication, ModelMap model, RedirectAttributes redirectAttributes) {
        return saveOrUpdate(toDoForm, bindingResult, authentication, model, redirectAttributes);
    }

    @RequestMapping(value = "/todo/", method = RequestMethod.POST, produces = "text/html")
    public String save(@Valid ToDoForm toDoForm, BindingResult bindingResult, Authentication authentication, ModelMap model, RedirectAttributes redirectAttributes) {
        return saveOrUpdate(toDoForm, bindingResult, authentication, model, redirectAttributes);
    }

    private String saveOrUpdate(final ToDoForm toDoForm, final BindingResult bindingResult, final Authentication authentication, final ModelMap model, final RedirectAttributes redirectAttributes) {
        User user = (User) authentication.getPrincipal();
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Validation error occurred!");
            return "todo-edit";
        } else {
            toDoForm.setOwnerId(user.getToDoShareAccount().getId());
            Long toDoId = toDoService.saveOrUpdateToDo(toDoForm);
            redirectAttributes.addFlashAttribute("successMessage", "Data saved successfully!");
            return "redirect:/todo/" + toDoId;
        }
    }

    @RequestMapping(value = "/todo/{toDoId}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("toDoId") @ModelAttribute("toDoForm") ToDoForm toDoForm, RedirectAttributes redirectAttributes) {
        toDoService.delete(toDoForm);
        redirectAttributes.addFlashAttribute("statusMessage", "Record deleted successfully!");
        return "redirect:/todo/list";
    }


}
