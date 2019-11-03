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

    /**
     * 詳細画面
     *
     * @param mav
     * @param userId
     * @return
     */
    @GetMapping(value = "user/{userId}")
    public ModelAndView show(ModelAndView mav,
                             @PathVariable Long userId) {
        Optional<User> userOpt = userService.getById(userId);
        if (userOpt.isPresent()) {
            mav.setViewName("/users/show");
            mav.addObject("user",userOpt.get());
            return mav;
        } else {
            mav.setViewName("/articles/index");
            String flash = "該当のユーザーは見つかりません";
            mav.addObject("flash", flash);
            return mav;
        }
    }

    /**
     * 編集画面
     *
     * @param mav
     * @param userId
     * @return
     */
    @GetMapping(value = "user/edit/{userId}")
    public ModelAndView edit(ModelAndView mav,
                             @PathVariable Long userId) {
        Optional<User> userOpt = userService.getById(userId);
        if (userOpt.isPresent()) {
            mav.setViewName("/users/editForm");
            mav.addObject("user",userOpt.get());
            return mav;
        } else {
            mav.setViewName("/articles/index");
            String flash = "該当のユーザーは見つかりません";
            mav.addObject("flash", flash);
            return mav;
        }
    }

    /**
     * 更新
     *
     * @param userForm
     * @return
     */
    @PostMapping(value = "user/update")
    public String update(@ModelAttribute("userForm")UserForm userForm) {
        userService.update(userForm);
        return "redirect:/";
    }

    /**
     * ログイン画面
     *
     * @param mav
     * @return
     */
    @GetMapping(value = "login")
    public ModelAndView loginForm(ModelAndView mav) {
        mav.setViewName("/users/loginForm");
        return mav;
    }

    /**
     * ログイン
     *
     * @param
     * @return
     */
    @PostMapping("login")
    public String login() {
        return "forward:/authenticate";
    }

    /**
     * ログイン成功時の画面遷移
     *
     * @param
     * @return
     */
    @PostMapping("success")
    public String loginSuccess() {
        return "redirect:/";
    }

    /**
     * ログイン失敗時の画面遷移
     *
     * @return
     */
    @PostMapping("failure")
    public String loginFailure() {
        return "redirect:/login";
    }

    /**
     * ログアウト画面
     *
     * @param mav
     * @return
     */
    @GetMapping("logout")
    public ModelAndView logoutForm(ModelAndView mav) {
        mav.setViewName("users/logoutForm");
        return mav;
    }
}
