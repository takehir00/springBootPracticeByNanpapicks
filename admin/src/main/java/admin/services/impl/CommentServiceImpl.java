package admin.services.impl;

import admin.forms.comment.CommentRegisterForm;
import admin.models.comment.CommentReadModel;
import admin.responses.CommentTopResponse;
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
    public CommentRegisterForm registerForm() {
        List<User> userList = userRepository.findAll();
        List<Article> articleList = articleRepository.findAll();
        return CommentRegisterForm.builder()
                //ユーザーリポジトリのfindAll呼び出してidのリスト作る
                .userIdList(userList.stream()
                        .map(user -> user.id)
                        .collect(Collectors.toList()))
                .articleIdList(articleList.stream()
                        .map(article -> article.id)
                        .collect(Collectors.toList()))
                .build();
    }
}
