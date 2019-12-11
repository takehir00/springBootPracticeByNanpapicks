package admin.forms.article;

import lombok.Data;

@Data
public class ArticleForm {
    public Long id;

    public String url;

    public String title;

    public String imageUrl;
}
