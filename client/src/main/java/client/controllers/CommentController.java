package client.controllers;

import client.forms.CommentCreateForm;
import client.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

}
