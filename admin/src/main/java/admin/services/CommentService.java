package admin.services;

import admin.forms.comment.CommentRegisterForm;
import admin.forms.comment.CommentUpdateForm;
import admin.responses.CommentRegisterFormResponse;
import admin.responses.CommentTopResponse;
import admin.responses.CommentUpdateFormResponse;

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

    /**
     * 更新フォーム
     *
     * @param commentId
     * @return
     */
    CommentUpdateFormResponse updateForm(Long commentId);

    /**
     * 更新
     *
     * @param commentUpdateForm
     */
    void update(CommentUpdateForm commentUpdateForm);
}
