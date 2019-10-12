package admin.controllers;

import admin.forms.UserForm;
import admin.models.User;
import admin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "admin/user", method = RequestMethod.GET)
    public ModelAndView top(ModelAndView mav) {
        mav.setViewName("users/top");
        List<User> users = userService.getAll();
        mav.addObject("users", users);
        return mav;
    }


    @RequestMapping(value = "/admin/user/registerForm", method = RequestMethod.GET)
    public ModelAndView registerForm(ModelAndView mav) {
        mav.setViewName("users/registerForm");
        return mav;
    }

    @RequestMapping(value = "admin/user", method = RequestMethod.POST)
    public String register(@ModelAttribute("userForm")UserForm userForm) {
        userService.create(userForm);
        return "redirect:/admin/user";
    }
}
