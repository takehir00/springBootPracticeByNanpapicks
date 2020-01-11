package admin.controllers;

import admin.forms.user.UserForm;
import admin.forms.user.UserUpdateForm;
import admin.util.PageUtil;
import db.entities.User;
import admin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * ユーザーコントローラ
 */
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
    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public ModelAndView top(ModelAndView mav, @RequestParam int page) {
        int limit = 15;
        int offset = PageUtil.calculatePageOffset(page, limit);

        mav.setViewName("users/top");
        mav.addObject("users",
                userService.getByOffsetAndLimit(offset, limit));
        mav.addObject("pageCount",
                userService.getPageCount(limit));
        return mav;
    }

    /**
     * 登録フォーム
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/admin/user/registerForm", method = RequestMethod.GET)
    public ModelAndView registerForm(
            ModelAndView mav,
            @ModelAttribute("userForm")UserForm userForm) {
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
    public String register(
            @Validated @ModelAttribute("userForm")UserForm userForm,
            BindingResult bindingResult,
            RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            FieldError nameError =bindingResult.getFieldError("name");
            FieldError mailError =bindingResult.getFieldError("mail");
            FieldError introductionError =bindingResult.getFieldError("introduction");
            FieldError passwordError =bindingResult.getFieldError("password");
            attributes.addFlashAttribute("userForm", userForm);
            attributes.addFlashAttribute("nameError", nameError);
            attributes.addFlashAttribute("mailError", mailError);
            attributes.addFlashAttribute("introductionError", introductionError);
            attributes.addFlashAttribute("passwordError", passwordError);
            return "redirect:/admin/user/registerForm";
        }
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
        UserUpdateForm form = userService.updateForm(userId);
        if (form != null) {
            mav.setViewName("/users/editForm");
            mav.addObject("userUpdateForm", form);
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
     * @param userUpdateForm
     * @return
     */
    @RequestMapping(value = "/admin/user/update", method = RequestMethod.POST)
    public String update(
            @Validated @ModelAttribute("userUpdateForm")UserUpdateForm userUpdateForm,
            BindingResult bindingResult,
            RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            FieldError nameError =bindingResult.getFieldError("name");
            FieldError mailError =bindingResult.getFieldError("mail");
            FieldError introductionError =bindingResult.getFieldError("introduction");
            FieldError imageUrlError =bindingResult.getFieldError("imageUrl");
            attributes.addFlashAttribute("userUpdateForm", userUpdateForm);
            attributes.addFlashAttribute("nameError", nameError);
            attributes.addFlashAttribute("mailError", mailError);
            attributes.addFlashAttribute("introductionError", introductionError);
            attributes.addFlashAttribute("imageUrlError", imageUrlError);
            return "redirect:/admin/user/edit/" + userUpdateForm.id;
        }
        userService.update(userUpdateForm);
        return "redirect:/admin/user";
    }

    /**
     * 削除画面
     *
     * @param mav
     * @param userId
     * @return
     */
    @RequestMapping(value = "/admin/user/destroy/{userId}")
    public ModelAndView destroy(ModelAndView mav,
                                @PathVariable Long userId) {
        Optional<User> userOpt = userService.getById(userId);
        if (userOpt.isPresent()) {
            mav.setViewName("/users/deleteForm");
            mav.addObject("user", userOpt.get());
            return mav;
        } else {
            String flash = "該当のユーザーはありません";
            mav.setViewName("users/top");
            List<User> users = userService.getAll();
            mav.addObject("users", users);
            mav.addObject("flash", flash);
            return mav;
        }
    }

    /**
     * 削除
     *
     * @param userForm
     * @return
     */
    @RequestMapping(value = "/admin/user", method = RequestMethod.DELETE)
    public String delete(@ModelAttribute("userForm")UserForm userForm) {
        userService.delete(userForm);
        return "redirect:/admin/user";
    }
}
