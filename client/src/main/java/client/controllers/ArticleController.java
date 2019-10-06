package client.controllers;

import client.models.Article;
import client.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 記事コントローラ
 */
@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    /**
     * 記事一覧画面表示
     *
     * @param mav ModelAndViewクラス
     * @return 記事一覧画面
     */
    @GetMapping(value = "/")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("articles/index");
        mav.addObject("articles", articleService.getAll());
        return mav;
    }

    /**
     * 記事詳細画面表示
     *
     * @param mav
     * @return 記事詳細画面
     */
    @GetMapping(value = "article/{articleId}")
    public ModelAndView show(ModelAndView mav,
                             @PathVariable Long articleId) {
        Optional<Article> article = articleService.getById(articleId);
        if (article.isPresent()) {
            mav.setViewName("articles/show");
            mav.addObject("article", article.get());
            return mav;
        }
        mav.setViewName("articles/index");
        mav.addObject("flash", "指定した記事は存在しません");
        return mav;
    }
}
