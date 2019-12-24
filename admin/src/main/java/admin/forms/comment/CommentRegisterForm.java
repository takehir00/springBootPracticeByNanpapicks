package admin.forms.comment;

import db.entities.Article;
import db.entities.Comment;
import db.entities.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Data
public class CommentRegisterForm {
    @Tolerate
    public CommentRegisterForm(){};

    @NotNull
    @Size(max = 300)
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
                .content(this.content)
                .user(user)
                .article(article)
                .build();
    }
}
