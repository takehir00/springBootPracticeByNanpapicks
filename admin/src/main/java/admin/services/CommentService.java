package admin.services;

import admin.forms.comment.CommentRegisterForm;
import admin.responses.CommentRegisterFormResponse;
import admin.responses.CommentTopResponse;

public interface CommentService {
    /**
     * 一覧
     *
     * @return
     */
    CommentTopResponse listing();

    /**
     * 登録フォーム
     *
     * @return
     */
    CommentRegisterFormResponse registerForm();

    /**
     * 登録
     *
     * @param commentRegisterForm
     */
    void create(CommentRegisterForm commentRegisterForm);
}
