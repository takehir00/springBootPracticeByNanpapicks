package client.responses.comments;

import client.forms.CommentDeleteForm;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentDeleteFormResponse {
    public CommentDeleteForm commentDeleteForm;
}
