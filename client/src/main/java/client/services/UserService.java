package client.services;

import client.forms.UserForm;
import client.responses.users.UserUpdateFormResponse;
import db.entities.User;
import javassist.NotFoundException;

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
     * メールアドレスで取得
     *
     * @param mail
     * @return
     */
    Optional<User> getByMail(String mail);

    /**
     * 更新
     *
     * @param userForm
     */
    void update(UserForm userForm) throws NotFoundException;

    /**
     * IDとメールアドレスで取得
     *
     * @param userId
     * @param mail
     * @return
     */
    Optional<User> getByIdAndMail(Long userId, String mail);

    /**
     * 更新フォームを生成する
     *
     * @param user
     */
    UserUpdateFormResponse createEditForm(User user);
}
