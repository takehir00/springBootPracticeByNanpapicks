package admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @RequestMapping(value = "/admin/user/registerForm", method = RequestMethod.GET)
    public ModelAndView registerForm(ModelAndView mav) {
        mav.setViewName("users/registerForm");
        return mav;
    }

        return mav;
    }
}
