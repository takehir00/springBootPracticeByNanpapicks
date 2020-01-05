package client.controllers;

import client.responses.articles.ArticleDetailResponse;
import client.responses.articles.ArticleIndexResponse;
import client.util.PageUtil;
import db.daos.impl.UserDaoImpl;
import db.entities.Article;
import client.services.ArticleService;
import db.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;
import java.util.Optional;

/**
 * 記事コントローラ
 */
@Controller
public class ArticleController extends HomeController {
    @Autowired
    ArticleService articleService;

    /**
     * 記事一覧画面表示
     *
     * @param mav ModelAndViewクラス
     * @return 記事一覧画面
     */
    @GetMapping(value = "/")
    public ModelAndView index(ModelAndView mav, @RequestParam int page) {
        int limit = 15;
        int offset = PageUtil.calculatePageOffset(page, limit);

        mav.addObject("user", getUser());

        mav.setViewName("articles/index");
        mav.addObject(
                "articleIndexResponse",
                articleService.listing(offset, limit));
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
        mav.addObject("user", getUser());

        ArticleDetailResponse response = articleService.detail(articleId);
        if (response == null) {
            mav.setViewName("articles/index");
            mav.addObject("flash", "指定した記事は存在しません");
            return mav;
        }
        mav.setViewName("articles/show");
        mav.addObject("response", response);
        return mav;
    }
}
