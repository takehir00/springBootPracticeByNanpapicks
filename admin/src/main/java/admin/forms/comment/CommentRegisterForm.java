package admin.forms.comment;

import db.entities.Article;
import db.entities.Comment;
import db.entities.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.List;

@Builder
@Data
public class CommentRegisterForm {
    @Tolerate
    public CommentRegisterForm(){};

    public String content;
    public Long userId;
    public Long articleId;

    public Comment asEntity() {
        User user = new User();
        user.id = userId;
        Article article = new Article();
        article.id =articleId;
        return Comment.builder()
                .content(this.content)
                .user(user)
                .article(article)
                .build();
    }
}
