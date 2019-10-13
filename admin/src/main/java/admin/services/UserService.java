package admin.services;

import admin.forms.UserForm;
import admin.models.User;

import java.util.List;
import java.util.Optional;

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

    /**
     * IDでユーザー取得
     *
     * @param id
     * @return ユーザー
     */
    Optional<User> getById(Long id);
}
