package admin.controllers;

import admin.forms.comment.CommentRegisterForm;
import admin.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        CommentRegisterForm form = commentService.registerForm();
        mav.addObject("commentRegisterForm", form);
        mav.setViewName("comments/registerForm");
        return mav;
    }
}
