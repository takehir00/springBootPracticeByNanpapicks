package client.forms;

import lombok.Data;

@Data
public class UserForm {
    public Long id;

    public String name;

    public String mail;

    public String introduction;

    public String imageUrl;

    public String password;
}
