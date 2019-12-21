package admin.forms.comment;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
public class CommentUpdateForm {
    @Tolerate
    public CommentUpdateForm(){};

    public Long id;
    public String content;
    public Long userId;
    public Long articleId;
}
