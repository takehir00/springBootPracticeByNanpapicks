package admin.forms.comment;

import db.entities.Article;
import db.entities.Comment;
import db.entities.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
public class CommentRegisterForm {
    @Tolerate
    public CommentRegisterForm(){};

    @NotEmpty
    @Size(max = 300, message = "300文字以下で入力してください")
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
