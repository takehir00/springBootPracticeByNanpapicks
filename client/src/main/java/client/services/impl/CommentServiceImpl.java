package client.services.impl;

import client.forms.CommentCreateForm;
import client.services.CommentService;
import db.entities.Article;
import db.entities.Comment;
import db.entities.User;
import db.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@Service
@ComponentScan("db.repositories")
@EntityScan(basePackageClasses = {Comment.class, User.class, Article.class})
@EnableJpaRepositories(basePackageClasses = {User.class, Article.class})
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public void create(CommentCreateForm commentCreateForm, User user) {
        commentRepository.create(commentCreateForm.asEntity(user));
    }
}
