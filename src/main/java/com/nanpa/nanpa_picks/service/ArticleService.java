package com.nanpa.nanpa_picks.service;

import com.nanpa.nanpa_picks.form.ArticleForm;
import com.nanpa.nanpa_picks.model.Article;
import org.springframework.stereotype.Service;

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
     */
    public void create(ArticleForm articleform);
}
