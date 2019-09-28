package client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 記事コントローラ
 */
@Controller
public class ArticleController {

    @GetMapping(value = "/")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("articles/index");
        return mav;
    }
}
