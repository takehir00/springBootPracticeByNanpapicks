package admin.controller;


import admin.form.ArticleForm;
import admin.model.Article;
import admin.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
    @Transactional
    @RequestMapping(value = "/admin/article", method = RequestMethod.GET)
    public ModelAndView top(ModelAndView mav) {
        mav.setViewName("articles/top");
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
                                 @ModelAttribute("articleForm") ArticleForm articleForm) {
        mav.setViewName("admin/articles/top");
        articleService.create(articleForm);
        return mav;
    }

    /**
     * 更新画面
     *
     * @param mav
     * @return
     */
    @RequestMapping(value = "/admin/article/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(ModelAndView mav,
                             @PathVariable Long id) {
        Optional<Article> article = articleService.getById(id);
        if (article.isPresent()) {
            mav.setViewName("admin/articles/editForm");
            mav.addObject("article", article);
            return mav;
        } else {
            mav.setViewName("admin/articles/top");
            String flash = "該当の記事はありません";
            mav.addObject(flash);
            return mav;
        }
    }

    /**
     * 記事更新
     *
     * @param mav
     * @param articleForm
     * @return
     */
    @RequestMapping(value = "/admin/article/update", method = RequestMethod.POST)
    public ModelAndView update(ModelAndView mav,
                               @ModelAttribute("articleForm")ArticleForm articleForm) {
        mav.setViewName("admin/articles/top");
        articleService.update(articleForm);
        return mav;
    }

    /**
     * 削除画面
     *
     * @param mav
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/article/destroy/{id}", method = RequestMethod.GET)
    public ModelAndView destroy(ModelAndView mav,
                                   @PathVariable Long id) {
        Optional<Article> article = articleService.getById(id);
        if (article.isPresent()) {
            mav.setViewName("admin/articles/deleteForm");
            mav.addObject("article", article.get());
            return mav;
        } else {
            mav.setViewName("admin/articles/top");
            String flash = "該当の記事はありません";
            mav.addObject(flash);
            return mav;
        }
    }

    /**
     * 記事削除
     *
     * @param mav
     * @param articleForm
     * @return
     */
    @RequestMapping(value = "/admin/article/delete", method = RequestMethod.DELETE)
    public ModelAndView delete(ModelAndView mav,
                               @ModelAttribute("articleForm")ArticleForm articleForm) {
        mav.setViewName("admin/articles/top");
        articleService.delete(articleForm);
        return mav;
    }
}
