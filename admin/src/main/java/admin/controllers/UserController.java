package admin.controllers;

import admin.forms.UserForm;
import admin.models.User;
import admin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 一覧画面
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "admin/user", method = RequestMethod.GET)
    public ModelAndView top(ModelAndView mav) {
        mav.setViewName("users/top");
        List<User> users = userService.getAll();
        mav.addObject("users", users);
        return mav;
    }

    /**
     * 登録フォーム
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/admin/user/registerForm", method = RequestMethod.GET)
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
    @RequestMapping(value = "admin/user", method = RequestMethod.POST)
    public String register(@ModelAttribute("userForm")UserForm userForm) {
        userService.create(userForm);
        return "redirect:/admin/user";
    }

    /**
     * 編集フォーム
     *
     * @param mav
     * @param userId
     * @return
     */
    @RequestMapping(value = "/admin/user/edit/{userId}", method = RequestMethod.GET)
    public ModelAndView edit(ModelAndView mav,
                                 @PathVariable Long userId) {
        Optional<User> userOpt = userService.getById(userId);
        if (userOpt.isPresent()) {
            mav.setViewName("/users/editForm");
            mav.addObject("user", userOpt.get());
            return mav;
        } else {
            String flash = "該当の記事はありません";
            mav.setViewName("users/top");
            List<User> users = userService.getAll();
            mav.addObject("users", users);
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
    @RequestMapping(value = "/admin/user/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("userForm")UserForm userForm) {
        userService.update(userForm);
        return "redirect:/admin/user";
    }


}
