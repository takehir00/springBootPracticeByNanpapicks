package admin.services;


import admin.forms.article.ArticleForm;
import admin.forms.article.ArticleRegisterForm;
import admin.forms.article.ArticleUpdateForm;
import db.entities.Article;

import java.util.List;

public interface ArticleService {
    /**
     * 記事一覧取得
     *
     * @return
     */
    public List<Article> getAll();

    /**
     * 記事登録
     * @param articleform
     */
    public void create(ArticleRegisterForm articleform);

    /**
     * IDで記事取得
     *
     * @param id
     * @return
     */
    Article getById(Long id);

    /**
     * 記事更新
     *
     * @param articleForm
     */
    void update(ArticleUpdateForm articleForm);

    /**
     * 記事削除
     *
     * @param articleForm
     */
    void delete(ArticleForm articleForm);

    /**
     * 更新フォーム
     *
     * @param articleId
     * @return
     */
    ArticleUpdateForm updateForm(Long articleId);
}
