package client.services;

import client.forms.CommentCreateForm;
import db.entities.User;

public interface CommentService {
    void create(CommentCreateForm commentCreateForm, User user);
}
