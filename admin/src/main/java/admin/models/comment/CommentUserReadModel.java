package admin.models.comment;

import lombok.Builder;

@Builder
public class CommentUserReadModel {
    public Long id;

    public String name;

    public String mail;

    public String introduction;

    public String imageUrl;
}
