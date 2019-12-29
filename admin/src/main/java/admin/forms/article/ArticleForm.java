package admin.forms.article;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ArticleForm {
    public Long id;

    @NotEmpty
    public String url;

    @NotEmpty
    @Size(max = 100, message = "100文字以下で入力してください")
    public String title;

    @NotEmpty
    public String imageUrl;
}
