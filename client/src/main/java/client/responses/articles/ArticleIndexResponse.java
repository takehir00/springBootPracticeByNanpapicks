package client.responses.articles;

import db.entities.Article;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ArticleIndexResponse {
    List<Article> articleList;
    int pageCount;
}
