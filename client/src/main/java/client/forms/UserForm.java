package client.forms;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserForm {
    public Long id;

    @NotNull
    @Size(max = 30)
    public String name;

    @NotNull
    @Email
    public String mail;

    @Size(max = 500)
    public String introduction;

    public String imageUrl;

    @Size(max = 20, min = 4)
    public String password;
}
