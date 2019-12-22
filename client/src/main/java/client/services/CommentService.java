package client.services;

import client.forms.CommentCreateForm;
import client.forms.CommentDeleteForm;
import client.responses.comments.CommentDeleteFormResponse;
import db.entities.User;

public interface CommentService {
    void create(CommentCreateForm commentCreateForm, User user);

    /**
     * 削除フォーム
     *
     * @param commentId
     * @return
     */
    CommentDeleteFormResponse deleteForm(Long commentId);

    /**
     * 削除
     *
     * @param commentDeleteForm
     */
    void delete(CommentDeleteForm commentDeleteForm);
}
