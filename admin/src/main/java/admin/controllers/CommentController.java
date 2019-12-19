package admin.controllers;

import admin.forms.comment.CommentRegisterForm;
import admin.responses.CommentRegisterFormResponse;
import admin.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     *一覧画面
     *
     * @param mav
     * @return
     */
    @GetMapping(value = "/admin/comment")
    public ModelAndView top(ModelAndView mav) {
        mav.addObject("articleResponse", commentService.listing());
        mav.setViewName("comments/top");
        return mav;
    }

    /**
     * 登録画面
     *
     * @param mav
     * @return
     */
    @GetMapping(value = "/admin/comment/register")
    public ModelAndView registerForm(ModelAndView mav) {
        CommentRegisterFormResponse form =
                commentService.registerForm();
        mav.addObject("commentRegisterFormResponse", form);
        mav.setViewName("comments/registerForm");
        return mav;
    }

    /**
     * 登録
     *
     * @param commentRegisterForm
     * @return
     */
    @Transactional
    @PostMapping(value = "/admin/comment/register")
    public String register(
            @ModelAttribute("commentRegisterForm") CommentRegisterForm commentRegisterForm) {
        commentService.create(commentRegisterForm);
        return "redirect:/admin/comment";
    }

    public ModelAndView updateForm(ModelAndView mav) {
        // レスポンスクラスに値の入ったフォームクラスを持たせてviewに渡す

        return mav;

    }
}
