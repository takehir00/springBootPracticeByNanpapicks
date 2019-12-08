package admin.models.comment;

import admin.models.article.ArticleReadModel;
import admin.models.user.UserReadModel;
import db.entities.Comment;
import lombok.Builder;

@Builder
public class CommentReadModel {
    public Long id;

    public String content;

    public UserReadModel user;

    public Long userId;

    public ArticleReadModel article;

    public Long articleId;

    public static CommentReadModel convertToCommentReadModel(Comment comment) {
        return CommentReadModel.builder()
                .id(comment.id)
                .content(comment.content)
                .userId(comment.user.id)
                .articleId(comment.article.id)
                .build();
    }
}
