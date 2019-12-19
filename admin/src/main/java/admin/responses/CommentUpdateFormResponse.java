package admin.responses;

import admin.forms.comment.CommentUpdateForm;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CommentUpdateFormResponse {
    public List<Long> userIdList;
    public List<Long> articleIdList;
    public CommentUpdateForm commentUpdateForm;
}
