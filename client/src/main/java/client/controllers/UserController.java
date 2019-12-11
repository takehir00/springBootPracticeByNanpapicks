package client.controllers;

import client.forms.UserForm;
import client.services.ArticleService;
import client.services.UserService;
import db.entities.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
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
public class UserController {
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
        String mail = getMailByPrincipal();
        
        Optional<User> userOpt = userService.getByMail(mail);
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
        mav.addObject("user",userOpt.get());
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
        String mail = getMailByPrincipal();

        Optional<User> userOpt = userService.getByMail(mail);
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

        mav.addObject("user",
                userService.createEditForm(userOpt.get()));
        mav.setViewName("users/editForm");
        return mav;
    }

    /**
     * 更新
     *
     * @param userForm
     * @return
     */
    @PostMapping(value = "user/update")
    public String update(RedirectAttributes redirectAttributes,
                         @ModelAttribute("userForm")UserForm userForm) throws NotFoundException {
        String mail = getMailByPrincipal();

        Optional<User> userOpt = userService.getByMail(mail);
        if (!userOpt.isPresent()) {
            String flash = "あなたのアカウントは削除されています";
            redirectAttributes.addAttribute("flash", flash);
            redirectAttributes.addAttribute("articles", articleService.getAll());
            return "redirect:/";
        }

        if (!userOpt.get().id.equals(userForm.id)) {
            redirectAttributes.addAttribute("user", userOpt.get());
            String flash = "指定したユーザーにアクセスする権限はありません";
            redirectAttributes.addAttribute("flash", flash);
            redirectAttributes.addAttribute("articles", articleService.getAll());
            return "redirect:/";
        }
        userService.update(userForm);
        return "redirect:/";
    }

    /**
     * プリンシパルからアクセス者のメールアドレスを取得する
     *
     * @return
     */
    private String getMailByPrincipal() {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }

}
