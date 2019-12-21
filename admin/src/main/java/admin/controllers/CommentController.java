package admin.controllers;

import admin.forms.comment.CommentRegisterForm;
import admin.forms.comment.CommentUpdateForm;
import admin.responses.CommentDeleteFormResponse;
import admin.responses.CommentRegisterFormResponse;
import admin.responses.CommentUpdateFormResponse;
import admin.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        CommentRegisterFormResponse response =
                commentService.registerForm();
        mav.addObject("commentRegisterFormResponse", response);
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

    /**
     * 更新フォーム
     *
     * @param mav
     * @param commentId
     * @return
     */
    @GetMapping(value = "/admin/comment/edit/{commentId}")
    public ModelAndView updateForm(ModelAndView mav,
                                   @PathVariable Long commentId) {
        CommentUpdateFormResponse response =
                commentService.updateForm(commentId);
        mav.addObject("commentUpdateFormResponse", response);
        mav.setViewName("comments/updateForm");
        return mav;
    }

    /**
     * 更新
     *
     * @param commentUpdateForm
     * @return
     */
    @Transactional
    @PostMapping("/admin/comment/edit")
    public String update(
            @ModelAttribute("commentUpdateForm")CommentUpdateForm commentUpdateForm) {
        commentService.update(commentUpdateForm);
        return "redirect:/admin/comment";
    }

    @GetMapping("/admin/comment/delete/{commentId}")
    public ModelAndView deleteForm(ModelAndView mav,
                             @PathVariable Long commentId) {
        CommentDeleteFormResponse response =
                commentService.deleteForm(commentId);
        mav.addObject("commentDeleteFormResponse", response);
        mav.setViewName("comments/deleteForm");
        return mav;
    }
}
