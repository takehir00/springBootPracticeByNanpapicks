package admin.forms.article;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@Data
public class ArticleRegisterForm {
    @Tolerate
    public ArticleRegisterForm(){};
    public Long id;

    @NotEmpty
    public String url;

    @NotEmpty
    @Size(max = 100, message = "100文字以下で入力してください")
    public String title;

    @NotEmpty
    public String imageUrl;
}
