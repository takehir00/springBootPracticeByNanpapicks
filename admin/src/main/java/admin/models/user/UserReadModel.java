package admin.models.user;

import db.entities.User;
import lombok.Builder;

@Builder
public class UserReadModel {
    public Long id;

    public String name;

    public String mail;

    public String introduction;

    public String imageUrl;

    public static UserReadModel convertToUserReadModel(User user) {
        return UserReadModel.builder()
                .id(user.id)
                .name(user.name)
                .mail(user.mail)
                .introduction(user.introduction)
                .imageUrl(user.imageUrl)
                .build();
    }
}
