package client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * ユーザーコントローラ
 */
@Controller
public class UserController {

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
}
