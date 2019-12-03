package admin.models.article;

import lombok.Builder;

@Builder
public class ArticleReadModel {
    public Long id;

    public String url;

    public String title;

    public String imageUrl;
}
