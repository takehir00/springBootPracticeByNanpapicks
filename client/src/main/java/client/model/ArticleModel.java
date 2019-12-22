package client.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ArticleModel {
    public Long id;

    public String url;

    public String title;

    public String imageUrl;

    public List<CommentModel> commentList;
}
