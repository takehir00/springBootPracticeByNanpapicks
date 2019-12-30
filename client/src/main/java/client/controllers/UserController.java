package client.controllers;

import client.forms.UserForm;
import client.forms.UserUpdateForm;
import client.responses.users.UserShowResponse;
import client.services.ArticleService;
import client.services.UserService;
import db.entities.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * ユーザーコントローラ
 */
@Controller
public class UserController extends HomeController {
    @Autowired
    UserService userService;

    @Autowired
    ArticleService articleService;

    @PersistenceContext
    EntityManager entityManager;

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
    @PostMapping(value = "signUp")
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
        Optional<User> userOpt = userService.getByMail(getMail());
        if (!userOpt.isPresent()) {
            mav.setViewName("articles/index");
            String flash = "あなたのアカウントは削除されています";
            mav.addObject("flash", flash);
            mav.addObject("articles", articleService.getAll());
            return mav;
        }

        if (!userOpt.get().id.equals(userId)) {
            mav.addObject("user", userOpt.get());
            mav.setViewName("articles/index");
            String flash = "指定したユーザーにアクセスする権限はありません";
            mav.addObject("flash", flash);
            mav.addObject("articles", articleService.getAll());
            return mav;
        }
        mav.setViewName("users/show");
        mav.addObject("response",userService.show(userOpt.get()));
        return mav;
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
        Optional<User> userOpt = userService.getByMail(getMail());
        if (!userOpt.isPresent()) {
            mav.setViewName("articles/index");
            String flash = "あなたのアカウントは削除されています";
            mav.addObject("flash", flash);
            mav.addObject("articles", articleService.getAll());
            return mav;
        }

        if (!userOpt.get().id.equals(userId)) {
            mav.addObject("user", userOpt.get());
            mav.setViewName("articles/index");
            String flash = "指定したユーザーにアクセスする権限はありません";
            mav.addObject("flash", flash);
            mav.addObject("articles", articleService.getAll());
            return mav;
        }

        UserUpdateForm form = userService.updateForm(userOpt.get());

        mav.addObject("userUpdateForm", form);
        mav.setViewName("users/editForm");
        return mav;
    }

    /**
     * 更新
     *
     * @param userUpdateForm
     * @return
     */
    @PostMapping(value = "user/update")
    public String update(RedirectAttributes redirectAttributes,
                         @Validated @ModelAttribute("userUpdateForm")UserUpdateForm userUpdateForm,
                         BindingResult bindingResult,
                         RedirectAttributes attributes) throws NotFoundException {
        Optional<User> userOpt = userService.getByMail(getMail());
        if (!userOpt.isPresent()) {
            String flash = "あなたのアカウントは削除されています";
            redirectAttributes.addAttribute("flash", flash);
            redirectAttributes.addAttribute("articles", articleService.getAll());
            return "redirect:/";
        }

        if (!userOpt.get().id.equals(userUpdateForm.id)) {
            redirectAttributes.addAttribute("user", userOpt.get());
            String flash = "指定したユーザーにアクセスする権限はありません";
            redirectAttributes.addAttribute("flash", flash);
            redirectAttributes.addAttribute("articles", articleService.getAll());
            return "redirect:/";
        }

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
            return "redirect:/user/edit/" + userUpdateForm.id;
        }
        userService.update(userUpdateForm);
        return "redirect:/";
    }
}
