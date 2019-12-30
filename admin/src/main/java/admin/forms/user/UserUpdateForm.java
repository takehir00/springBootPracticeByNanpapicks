package admin.forms.user;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@Data
public class UserUpdateForm {
    @Tolerate
    public UserUpdateForm(){};

    public Long id;

    @NotEmpty
    @Size(max = 30, message = "30文字以下で入力してください")
    public String name;

    @NotEmpty
    @Email
    public String mail;

    @Size(max = 500, message = "500文字以下で入力してください")
    public String introduction;

    public String imageUrl;
}
