package client.forms;

import db.entities.Article;
import db.entities.Comment;
import db.entities.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
public class CommentCreateForm {
    @Tolerate
    public CommentCreateForm(){};

    public String comment;
    public Long articleId;

    public Comment asEntity(User user) {
        Article article = new Article();
        article.id =articleId;
        return Comment.builder()
                .content(this.comment)
                .user(user)
                .article(article)
                .build();
    }
}
