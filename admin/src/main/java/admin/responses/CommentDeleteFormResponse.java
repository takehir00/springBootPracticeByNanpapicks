package admin.responses;

import admin.forms.comment.CommentDeleteForm;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentDeleteFormResponse {
    public CommentDeleteForm commentDeleteForm;
}
