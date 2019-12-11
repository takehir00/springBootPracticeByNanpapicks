package admin.responses;

import admin.forms.comment.CommentRegisterForm;
import lombok.Builder;

import java.util.List;

@Builder
public class CommentRegisterFormResponse {
    public List<Long> userIdList;

    public List<Long> articleIdList;

    public CommentRegisterForm commentRegisterForm;
}
