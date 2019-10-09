package client.services;

import client.models.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    /**
     * 記事一覧取得
     *
     * @return 記事一覧
     */
    public List<Article> getAll();

    /**
     * idで記事取得
     *
     * @param id
     * @return 記事
     */
    Optional<Article> getById(Long id);
}
