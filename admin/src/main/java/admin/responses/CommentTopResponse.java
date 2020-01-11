package admin.responses;

import admin.models.comment.CommentReadModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CommentTopResponse {
    public List<CommentReadModel> commentList;
    public String flash;
    public int pageCount;
}
