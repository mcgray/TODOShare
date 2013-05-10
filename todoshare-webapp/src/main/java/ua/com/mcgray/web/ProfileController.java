package ua.com.mcgray.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author orezchykov
 * @since 09.05.13
 */
@Controller
public class ProfileController {

    @RequestMapping(value = "/profile", method = RequestMethod.GET, produces = "text/html")
    public String showProfile() {
        return "profile";
    }

}
