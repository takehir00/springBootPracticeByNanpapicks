package client.controllers;

import client.forms.CommentCreateForm;
import client.forms.CommentDeleteForm;
import client.responses.comments.CommentDeleteFormResponse;
import client.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            @Validated @ModelAttribute("commentCreateForm")CommentCreateForm commentCreateForm,
            BindingResult bindingResult,
            RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            FieldError contentError =bindingResult.getFieldError("content");
            attributes.addFlashAttribute("commentCreateForm", commentCreateForm);
            attributes.addFlashAttribute("contentError", contentError);
            return "redirect:/article/" + commentCreateForm.articleId;
        }
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
