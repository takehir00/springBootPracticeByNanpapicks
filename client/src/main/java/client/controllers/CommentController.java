package client.controllers;

import client.forms.CommentCreateForm;
import client.forms.CommentDeleteForm;
import client.responses.comments.CommentDeleteFormResponse;
import client.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * コメントコントローラ
 */
@Controller
public class CommentController extends HomeController {
    @Autowired
    CommentService commentService;

    /**
     * 作成
     *
     * @param commentCreateForm
     * @return
     */
    @Transactional
    @PostMapping("/comment/create")
    public String create(
            @ModelAttribute("commentCreateForm")CommentCreateForm commentCreateForm) {
        commentService.create(commentCreateForm, getUser());
        return "redirect:/article/" + commentCreateForm.articleId;
    }

    /**
     * 削除画面
     *
     * @param mav
     * @param commentId
     * @return
     */
    @GetMapping("/comment/delete/{commentId}")
    public ModelAndView deleteForm(
            ModelAndView mav, @PathVariable Long commentId) {
        CommentDeleteFormResponse response =
                commentService.deleteForm(commentId);
        mav.addObject("response", response);
        mav.setViewName("comments/deleteForm");
        return mav;
    }

    @Transactional
    @DeleteMapping("/comment/delete")
    public String delete(
            @ModelAttribute("commentDeleteForm") CommentDeleteForm commentDeleteForm) {
        commentService.delete(commentDeleteForm);
        return "redirect:/article/" + commentDeleteForm.articleId;
    }

}
