package admin.controllers;


import admin.forms.ArticleForm;
import admin.models.Article;
import admin.services.ArticleService;
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
public class ArticleController {
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
        mav = setArticleTopModelAndView(null);
        return mav;
    }

    /**
     * 記事登録画面
     *
     * @return
     */
    @RequestMapping(value = "/admin/article/registerForm", method = RequestMethod.GET)
    public ModelAndView registerForm(ModelAndView mav) {
        mav.setViewName("articles/registerForm");
        return mav;
    }

    /**
     * 記事登録
     *
     * @param articleForm
     * @return
     */
    @Transactional
    @RequestMapping(value = "/admin/article", method = RequestMethod.POST)
    public String register(@ModelAttribute("articleForm") ArticleForm articleForm) {
        articleService.create(articleForm);
        return "redirect:/admin/article";
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
            mav.setViewName("articles/editForm");
            mav.addObject("article", article.get());
            return mav;
        } else {
            String flash = "該当の記事はありません";
            mav = setArticleTopModelAndView(flash);
            return mav;
        }
    }

    /**
     * 記事更新
     *
     * @param articleForm
     * @return
     */
    @RequestMapping(value = "/admin/article/update", method = RequestMethod.POST)
    public String  update(@ModelAttribute("articleForm")ArticleForm articleForm) {
        articleService.update(articleForm);
        return "redirect:/admin/article";
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
            mav.setViewName("articles/deleteForm");
            mav.addObject("article", article.get());
            return mav;
        } else {
            String flash = "該当の記事はありません";
            mav = setArticleTopModelAndView(flash);
            return mav;
        }
    }

    /**
     * 記事削除
     *
     * @param articleForm
     * @return
     */
    @RequestMapping(value = "/admin/article/delete", method = RequestMethod.DELETE)
    public String delete(@ModelAttribute("articleForm")ArticleForm articleForm) {
        articleService.delete(articleForm);
        return "redirect:/admin/article";
    }

    /**
     * 記事TOP画面のModelAndViewを生成
     *
     * @return
     */
    private ModelAndView setArticleTopModelAndView(String flash) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("articles/top");
        List<Article> articles = articleService.getAll();
        mav.addObject("articles", articles);
        mav.addObject("flash",flash);
        return mav;
    }
}
