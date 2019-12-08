package admin.controllers;

import admin.responses.CommentTopResponse;
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
     *
     * @param mav
     * @return
     */
    @GetMapping(value = "/admin/comment")
    public ModelAndView top(ModelAndView mav) {
        CommentTopResponse response = commentService.listing();
        mav.addObject("articleResponse", response);
        mav.setViewName("comments/top");
        return mav;
    }

    @GetMapping(value = "/admin/comment/register")
    public ModelAndView registerForm(ModelAndView mav) {
        return mav;
    }
}
