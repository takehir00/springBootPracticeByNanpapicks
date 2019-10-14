package client.services;

import client.forms.UserForm;

public interface UserService {
    /**
     * 登録
     *
     * @param userForm
     */
    void create(UserForm userForm);
}
