package client.services.impl;

import client.forms.CommentCreateForm;
import client.forms.CommentDeleteForm;
import client.responses.comments.CommentDeleteFormResponse;
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

    @Override
    public CommentDeleteFormResponse deleteForm(Long commentId) {
        Comment comment = commentRepository.findById(commentId);
        return CommentDeleteFormResponse.builder()
                .commentDeleteForm(
                        CommentDeleteForm.builder()
                                .id(comment.id)
                                .articleId(comment.article.id)
                                .content(comment.content)
                                .build())
                .build();
    }

    @Override
    public void delete(CommentDeleteForm commentDeleteForm) {
        Comment comment = commentRepository.findById(commentDeleteForm.id);
        commentRepository.delete(comment);
    }
}
