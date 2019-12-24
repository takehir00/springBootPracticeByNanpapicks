package admin.forms.comment;

import db.entities.Article;
import db.entities.Comment;
import db.entities.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;

@Builder
@Data
public class CommentUpdateForm {
    @Tolerate
    public CommentUpdateForm(){};

    @NotNull
    public Long id;
    @NotNull
    public String content;
    @NotNull
    public Long userId;
    @NotNull
    public Long articleId;

    public Comment asEntity() {
        User user = new User();
        user.id = userId;
        Article article = new Article();
        article.id =articleId;

        return Comment.builder()
                .id(this.id)
                .content(this.content)
                .user(user)
                .article(article)
                .build();
    }
}
