package admin.forms.comment;

import lombok.Builder;

import java.util.List;

@Builder
public class CommentRegisterForm {
    public List<Long> userIdList;

    public List<Long> articleIdList;
}
