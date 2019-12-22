package client.forms;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Builder
@Data
public class CommentDeleteForm {
    @Tolerate
    public CommentDeleteForm(){};

    public Long id;
    public Long articleId;
    public String content;
}
