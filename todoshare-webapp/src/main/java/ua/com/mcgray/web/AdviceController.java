package ua.com.mcgray.web;

import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author orezchykov
 * @since 16.05.13
 */

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(TypeMismatchException.class)
    public ModelAndView handleTypeMismatchException(TypeMismatchException ex) {
        ModelAndView modelAndView = new ModelAndView("exception");
        modelAndView.addObject("exception", ex);
        return modelAndView;
    }

}
