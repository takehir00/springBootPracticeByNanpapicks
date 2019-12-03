package admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {

    @GetMapping(value = "/admin/comment")
    public ModelAndView top(ModelAndView mav) {
        // ここでのaddObjectを一回で済ませたいので、レスポンスモデルを作る
        mav.setViewName("comments/top");
        return mav;
    }
}
