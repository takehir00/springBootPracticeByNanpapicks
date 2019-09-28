package com.nanpa.nanpa_picks.service;

import com.nanpa.nanpa_picks.form.ArticleForm;
import com.nanpa.nanpa_picks.model.Article;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    /**
     * 記事一覧取得
     *
     * @return
     */
    public List<Article> getAll();

    /**
     * 記事登録
     */
    public void create(ArticleForm articleform);

    /**
     * IDで記事取得
     *
     * @param id
     * @return
     */
    Optional<Article> getById(Long id);

    /**
     * 記事更新
     *
     * @param articleForm
     */
    void update(ArticleForm articleForm);

    /**
     * 記事削除
     *
     * @param articleForm
     */
    void delete(ArticleForm articleForm);
}
