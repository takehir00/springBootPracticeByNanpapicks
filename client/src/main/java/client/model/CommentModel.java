package client.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentModel {
    public Long id;

    public String content;

    public UserModel user;
}
