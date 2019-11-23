package client.responses.users;

import lombok.Builder;

@Builder
public class UserUpdateFormResponse {
    public Long id;

    public String name;

    public String mail;

    public String introduction;

    public String imageUrl;

    public String password;
}
