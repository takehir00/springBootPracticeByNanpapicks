package client.forms;

import db.entities.Article;
import db.entities.Comment;
import db.entities.User;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
public class CommentCreateForm {
    @Tolerate
    public CommentCreateForm(){};

    @NotEmpty
    @Size(max = 300, message = "300文字以下で入力してください")
    public String comment;
    @NotNull
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
