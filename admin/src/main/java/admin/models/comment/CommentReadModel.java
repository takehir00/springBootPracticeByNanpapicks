package admin.models.comment;

import admin.models.article.ArticleReadModel;
import admin.models.user.UserReadModel;
import db.entities.Article;
import lombok.Builder;

@Builder
public class CommentReadModel {
    public Long id;

    public String content;

    public UserReadModel user;

    public ArticleReadModel article;
}
