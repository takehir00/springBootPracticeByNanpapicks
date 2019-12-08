package admin.models.article;

import db.entities.Article;
import lombok.Builder;

@Builder
public class ArticleReadModel {
    public Long id;

    public String url;

    public String title;

    public String imageUrl;

    public static ArticleReadModel convertToArticleReadModel(Article article) {
        return ArticleReadModel.builder()
                .id(article.id)
                .url(article.url)
                .title(article.title)
                .imageUrl(article.imageUrl)
                .build();
    }
}
