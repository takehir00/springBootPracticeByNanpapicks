package client.controllers;

import client.forms.UserForm;
import client.forms.UserUpdateForm;
import client.responses.users.UserShowResponse;
import client.services.ArticleService;
import client.services.UserService;
import client.util.PageUtil;
import client.validator.UserFormValidator;
import db.entities.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
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

    @Autowired
    UserFormValidator userFormValidator;

    @PersistenceContext
    EntityManager entityManager;

    @InitBinder("userForm")
    public void validationBinder(WebDataBinder binder) {
        binder.addValidators(userFormValidator);
    }

    /** 記事一覧画面で取得するデータの上限数 */
    private int limit = 15;

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
    @Transactional
    @PostMapping(value = "signUp")
    public String create(
            @Validated @ModelAttribute("userForm")UserForm userForm,
            BindingResult bindingResult,
            RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            FieldError passwordConfirmError =
                    bindingResult.getFieldError("passwordConfirm");
            attributes.addFlashAttribute(
                    "passwordConfirmError" , passwordConfirmError);
            return "redirect:/user/registerForm";
        }
        userService.create(userForm);
        return "redirect:/?page=0";
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
            int page = 0;
            int offset = PageUtil.calculatePageOffset(page, limit);

            mav.setViewName("articles/index");
            String flash = "あなたのアカウントは削除されています";
            mav.addObject("flash", flash);
            mav.addObject(
                    "articleIndexResponse",
                    articleService.listing(offset, limit));
            return mav;
        }
        
        mav.addObject("user", getUser());
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
            int page = 0;
            int offset = PageUtil.calculatePageOffset(page, limit);

            mav.setViewName("articles/index");
            String flash = "あなたのアカウントは削除されています";
            mav.addObject("flash", flash);
            mav.addObject(
                    "articleIndexResponse",
                    articleService.listing(offset, limit));
            return mav;
        }

        if (!userOpt.get().id.equals(userId)) {
            int page = 0;
            int offset = PageUtil.calculatePageOffset(page, limit);

            mav.addObject("user", userOpt.get());
            mav.setViewName("articles/index");
            String flash = "指定したユーザーにアクセスする権限はありません";
            mav.addObject("flash", flash);
            mav.addObject(
                    "articleIndexResponse",
                    articleService.listing(offset, limit));
            return mav;
        }

        UserUpdateForm form = userService.updateForm(userOpt.get());
        mav.addObject("user", getUser());
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
    @Transactional
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
            return "redirect:/?page=0";
        }

        if (!userOpt.get().id.equals(userUpdateForm.id)) {
            redirectAttributes.addAttribute("user", userOpt.get());
            String flash = "指定したユーザーにアクセスする権限はありません";
            redirectAttributes.addAttribute("flash", flash);
            redirectAttributes.addAttribute("articles", articleService.getAll());
            return "redirect:/?page=0";
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
        return "redirect:/?page=0";
    }
}
