package client.controllers;

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
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

/**
 * 記事コントローラ
 */
@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PersistenceContext
    EntityManager entityManager;

    /**
     * 記事一覧画面表示
     *
     * @param mav ModelAndViewクラス
     * @return 記事一覧画面
     */
    @GetMapping(value = "/")
    public ModelAndView index(ModelAndView mav) {
        String mail;
        //対話中のユーザーのプリンシパルを取得する
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails) {
            //ユーザー名からユーザー情報を取得する
            mail = ((UserDetails)principal).getUsername();
        } else {
            mail = principal.toString();
        }
        UserDaoImpl userDao = new UserDaoImpl(entityManager);
        User user = userDao.findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException(""));
        mav.addObject("user", user);

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
        String mail;
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal instanceof UserDetails) {
            //ユーザー名からユーザー情報を取得する
            mail = ((UserDetails)principal).getUsername();
        } else {
            mail = principal.toString();
        }
        UserDaoImpl userDao = new UserDaoImpl(entityManager);
        User user = userDao.findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException(""));
        mav.addObject("user", user);

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
