package client.responses.users;

import client.model.ArticleModel;
import client.model.UserModel;
import lombok.Builder;

import java.util.List;

@Builder
public class UserShowResponse {
    public UserModel userModel;
    public List<ArticleModel> articleModelList;
}
