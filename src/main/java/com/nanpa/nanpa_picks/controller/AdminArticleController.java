package com.nanpa.nanpa_picks.controller;

import com.nanpa.nanpa_picks.form.ArticleForm;
import com.nanpa.nanpa_picks.model.Article;
import com.nanpa.nanpa_picks.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;

@Controller
public class AdminArticleController {
    @Autowired
    ArticleService articleService;

    /**
     * 記事管理画面TOP
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/admin/article", method = RequestMethod.GET)
    public ModelAndView top(ModelAndView mav) {
        mav.setViewName("admin/articles/top");
        List<Article> articles = articleService.getAll();
        mav.addObject("articles", articles);
        return mav;
    }

    /**
     * 記事登録画面
     *
     * @return
     */
    @RequestMapping(value = "/admin/article/registerForm", method = RequestMethod.GET)
    public ModelAndView registerForm(ModelAndView mav) {
        mav.setViewName("admin/articles/registerForm");
        return mav;
    }

    /**
     * 記事登録
     *
     * @param mav
     * @return
     */
    @Transactional
    @RequestMapping(value = "/admin/article", method = RequestMethod.POST)
    public ModelAndView register(ModelAndView mav,
                                 @ModelAttribute("articleForm")ArticleForm articleForm) {
        articleService.create(articleForm);
        mav.setViewName("admin/articles/top");
        return mav;
    }
}
