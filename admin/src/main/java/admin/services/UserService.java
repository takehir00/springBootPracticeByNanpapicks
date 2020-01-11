package admin.services;

import admin.forms.user.UserForm;
import admin.forms.user.UserUpdateForm;
import db.entities.User;

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
     * ユーザーをoffsetとlimitを指定して取得
     *
     * @param offset
     * @param limit
     * @return
     */
    List<User> getByOffsetAndLimit(int offset, int limit);

    /**
     * IDでユーザー取得
     *
     * @param id
     * @return ユーザー
     */
    Optional<User> getById(Long id);

    /**
     * ユーザー更新
     *
     * @param userForm
     */
    void update(UserUpdateForm userForm);

    /**
     * ユーザー削除
     *
     * @param userForm
     */
    void delete(UserForm userForm);

    /**
     * ユーザー更新フォーム
     *
     * @param userId
     * @return
     */
    UserUpdateForm updateForm(Long userId);
}
