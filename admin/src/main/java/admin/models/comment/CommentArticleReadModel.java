package admin.models.comment;

import lombok.Builder;

@Builder
public class CommentArticleReadModel {
    public Long id;

    public String url;

    public String title;

    public String imageUrl;
}
