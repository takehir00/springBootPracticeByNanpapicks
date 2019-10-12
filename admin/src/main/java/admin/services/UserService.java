package admin.services;

import admin.forms.ArticleForm;
import admin.forms.UserForm;
import admin.models.User;

import java.util.List;

public interface UserService {
    /**
     * ユーザー登録
     *
     * @param userForm
     */
    public void create(UserForm userForm);

    /**
     * ユーザー一覧取得
     *
     * @return ユーザー一覧
     */
    List<User> getAll();
}
