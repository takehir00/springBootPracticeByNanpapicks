package client.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserModel {
    public Long id;

    public String name;

    public String mail;

    public String introduction;

    public String imageUrl;
}
