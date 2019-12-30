package client.forms;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Data
public class CommentDeleteForm {
    @Tolerate
    public CommentDeleteForm(){};

    @NotNull
    public Long id;
    @NotNull
    public Long articleId;

    public String content;
}
