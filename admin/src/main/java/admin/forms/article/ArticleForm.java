package admin.forms.article;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ArticleForm {
    public Long id;

    @NotNull
    public String url;

    @NotNull
    @Size(max = 100)
    public String title;

    @NotNull
    public String imageUrl;
}
