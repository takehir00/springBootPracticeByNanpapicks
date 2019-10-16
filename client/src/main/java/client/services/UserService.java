package client.services;

import client.forms.UserForm;
import db.models.User;

import java.util.Optional;

public interface UserService {
    /**
     * 登録
     *
     * @param userForm
     */
    void create(UserForm userForm);

    /**
     * IDで取得
     *
     * @param userId
     * @return
     */
    Optional<User> getById(Long userId);

    /**
     * 更新
     *
     * @param userForm
     */
    void update(UserForm userForm);
}
