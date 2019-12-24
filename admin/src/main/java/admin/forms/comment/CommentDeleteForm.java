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
public class CommentDeleteForm {
    @Tolerate
    public CommentDeleteForm(){};

    @NotNull
    public Long id;
    public String content;
    public Long userId;
    public Long articleId;
}
