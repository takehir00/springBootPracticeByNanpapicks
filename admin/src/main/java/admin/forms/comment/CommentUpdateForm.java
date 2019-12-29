package admin.forms.comment;

import db.entities.Article;
import db.entities.Comment;
import db.entities.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;

@Builder
@Data
public class CommentUpdateForm {
    @Tolerate
    public CommentUpdateForm(){};

    @NotEmpty
    public Long id;
    @NotEmpty
    public String content;
    @NotEmpty
    public Long userId;
    @NotEmpty
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
