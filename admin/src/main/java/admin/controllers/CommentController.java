package admin.controllers;

import admin.forms.comment.CommentDeleteForm;
import admin.forms.comment.CommentRegisterForm;
import admin.forms.comment.CommentUpdateForm;
import admin.responses.CommentDeleteFormResponse;
import admin.responses.CommentRegisterFormResponse;
import admin.responses.CommentUpdateFormResponse;
import admin.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView registerForm(
            ModelAndView mav,
            @ModelAttribute("commentRegisterForm") CommentRegisterForm commentRegisterForm) {
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
            @Validated @ModelAttribute("commentRegisterForm") CommentRegisterForm commentRegisterForm,
            BindingResult bindingResult,
            RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            CommentRegisterFormResponse response =
                    commentService.registerForm();
            FieldError contentError =bindingResult.getFieldError("content");
            attributes.addFlashAttribute("commentRegisterForm", commentRegisterForm);
            attributes.addFlashAttribute("commentRegisterFormResponse", response);
            attributes.addFlashAttribute("contentError", contentError);
            return "redirect:/admin/comment/register";
        }
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
                commentService.updateFormResponse(commentId);
        CommentUpdateForm form=
                commentService.updateForm(commentId);
        mav.addObject("commentUpdateFormResponse", response);
        mav.addObject("commentUpdateForm", form);
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
            @Validated @ModelAttribute("commentUpdateForm")CommentUpdateForm commentUpdateForm,
            BindingResult bindingResult,
            RedirectAttributes attributes) {
        if (bindingResult.hasErrors()) {
            CommentUpdateFormResponse response =
                    commentService.updateFormResponse(commentUpdateForm.getId());
            FieldError contentError =bindingResult.getFieldError("content");
            FieldError userIdError =bindingResult.getFieldError("userId");
            FieldError articleId =bindingResult.getFieldError("articleId");
            attributes.addFlashAttribute("commentUpdateForm", commentUpdateForm);
            attributes.addFlashAttribute("commentUpdateFormResponse", response);
            attributes.addFlashAttribute("contentError", contentError);
            attributes.addFlashAttribute("userIdError", userIdError);
            attributes.addFlashAttribute("articleId", articleId);
            return "redirect:/admin/comment/edit/" + commentUpdateForm.getId();
        }
        commentService.update(commentUpdateForm);
        return "redirect:/admin/comment";
    }

    /**
     * 削除フォーム
     *
     * @param mav
     * @param commentId
     * @return
     */
    @GetMapping("/admin/comment/delete/{commentId}")
    public ModelAndView deleteForm(ModelAndView mav,
                             @PathVariable Long commentId) {
        CommentDeleteFormResponse response =
                commentService.deleteForm(commentId);
        mav.addObject("commentDeleteFormResponse", response);
        mav.setViewName("comments/deleteForm");
        return mav;
    }

    /**
     * 削除
     *
     * @param commentDeleteForm
     * @return
     */
    @Transactional
    @DeleteMapping("/admin/comment/delete")
    public String delete(
            @ModelAttribute("commentDeleteForm") CommentDeleteForm commentDeleteForm) {
        commentService.delete(commentDeleteForm);
        return "redirect:/admin/comment";
    }
}
