package client.responses.articles;

import client.model.ArticleModel;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ArticleDetailResponse {
    ArticleModel articleModel;
}
