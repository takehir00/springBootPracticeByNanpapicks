package client.controllers;

import client.forms.UserForm;
import client.services.UserService;
import db.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

/**
 * ユーザーコントローラ
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 登録画面
     *
     * @param mav
     * @return
     */
    @GetMapping(value = "user/registerForm")
    public ModelAndView registerForm(ModelAndView mav) {
        mav.setViewName("users/registerForm");
        return mav;
    }

    /**
     * 登録
     *
     * @param userForm
     * @return
     */
    @PostMapping(value = "user")
    public String create(@ModelAttribute("userForm")UserForm userForm) {
        userService.create(userForm);
        return "redirect:/";
    }

    @GetMapping(value = "user/{userId}")
    public ModelAndView show(ModelAndView mav,
                             @PathVariable Long userId) {
        Optional<User> userOpt = userService.getById(userId);
        if (userOpt.isPresent()) {
            mav.setViewName("/users/show");
            mav.addObject("user",userOpt.get());
            return mav;
        } else {
            return mav;
        }
    }
}
