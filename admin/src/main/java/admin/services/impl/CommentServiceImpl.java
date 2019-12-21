package admin.services.impl;

import admin.forms.comment.CommentDeleteForm;
import admin.forms.comment.CommentRegisterForm;
import admin.forms.comment.CommentUpdateForm;
import admin.models.comment.CommentReadModel;
import admin.responses.CommentDeleteFormResponse;
import admin.responses.CommentRegisterFormResponse;
import admin.responses.CommentTopResponse;
import admin.responses.CommentUpdateFormResponse;
import admin.services.CommentService;
import db.entities.Article;
import db.entities.Comment;
import db.entities.User;
import db.repositories.ArticleRepository;
import db.repositories.CommentRepository;
import db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ComponentScan("db.repositories")
@EntityScan(basePackageClasses = {Comment.class, User.class, Article.class})
@EnableJpaRepositories(basePackageClasses = {User.class, Article.class})
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public CommentTopResponse listing() {
        List<Comment> commentEntityList = commentRepository.findAll();

        return CommentTopResponse.builder()
                .commentList(
                        commentEntityList.stream()
                                .map(CommentReadModel::convertToCommentReadModel)
                                .collect(Collectors.toList()))
                .build();
    }

    @Override
    public CommentRegisterFormResponse registerForm() {
        List<User> userList = userRepository.findAll();
        List<Article> articleList = articleRepository.findAll();

        return CommentRegisterFormResponse.builder()
                //ユーザーリポジトリのfindAll呼び出してidのリスト作る
                .userIdList(userList.stream()
                        .map(user -> user.id)
                        .collect(Collectors.toList()))
                .articleIdList(articleList.stream()
                        .map(article -> article.id)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public void create(CommentRegisterForm commentRegisterForm) {
        commentRepository.create(commentRegisterForm.asEntity());
    }

    @Override
    public CommentUpdateFormResponse updateForm(Long commentId) {
        List<User> userList = userRepository.findAll();
        List<Article> articleList = articleRepository.findAll();
        Comment comment = commentRepository.findById(commentId);

        return CommentUpdateFormResponse.builder()
                .userIdList(userList.stream()
                        .map(user -> user.id)
                        .collect(Collectors.toList()))
                .articleIdList(articleList.stream()
                        .map(article -> article.id)
                        .collect(Collectors.toList()))
                .commentUpdateForm(
                        CommentUpdateForm.builder()
                                .id(comment.id)
                                .content(comment.content)
                                .userId(comment.user.id)
                                .articleId(comment.article.id)
                                .build())
                .build();
    }

    @Override
    public void update(CommentUpdateForm commentUpdateForm) {
        commentRepository.update(commentUpdateForm.asEntity());
    }

    @Override
    public CommentDeleteFormResponse deleteForm(Long commentId) {
        Comment comment = commentRepository.findById(commentId);

        return CommentDeleteFormResponse.builder()
                .commentDeleteForm(
                        CommentDeleteForm.builder()
                                .id(comment.id)
                                .content(comment.content)
                                .userId(comment.user.id)
                                .articleId(comment.article.id)
                                .build())
                .build();
    }

    @Override
    public void delete(CommentDeleteForm commentDeleteForm) {
        Comment comment = commentRepository.findById(commentDeleteForm.id);
        commentRepository.delete(comment);
    }
}
