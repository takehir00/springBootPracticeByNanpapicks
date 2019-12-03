package admin.models.user;

import lombok.Builder;

@Builder
public class UserReadModel {
    public Long id;

    public String name;

    public String mail;

    public String introduction;

    public String imageUrl;
}
