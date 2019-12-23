package client.services.impl;

import client.forms.UserForm;
import client.model.ArticleModel;
import client.model.UserModel;
import client.responses.users.UserShowResponse;
import client.responses.users.UserUpdateFormResponse;
import client.services.UserService;
import db.daos.impl.UserDaoImpl;
import db.entities.Article;
import db.entities.User;
import db.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EntityScan(basePackageClasses = User.class)
@EnableJpaRepositories(basePackageClasses = User.class)
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(UserForm userForm) {
        User user = new User();
        user.name = userForm.name;
        user.mail = userForm.mail;
        user.imageUrl = userForm.imageUrl;
        user.introduction = userForm.introduction;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.password = encoder.encode(userForm.password);
        userRepository.save(user);
    }

    @Override
    public Optional<User> getById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getByMail(String mail) {
        UserDaoImpl userDao = new UserDaoImpl(entityManager);
        return userDao.findByMail(mail);
    }

    @Override
    public Optional<User> getByIdAndMail(Long userId, String mail) {
        UserDaoImpl userDao = new UserDaoImpl(entityManager);
        return userDao.findByIdAndMail(userId, mail);
    }

    @Override
    public UserUpdateFormResponse createEditForm(User user) {
        return UserUpdateFormResponse.builder()
                .id(user.id)
                .name(user.name)
                .mail(user.mail)
                .introduction(user.introduction)
                .imageUrl(user.imageUrl)
                .password(user.password)
                .build();
    }

    @Override
    public UserShowResponse show(User user) {
        return UserShowResponse.builder()
                .userModel(
                        UserModel.builder()
                                .id(user.id)
                                .name(user.name)
                                .mail(user.mail)
                                .introduction(user.introduction)
                                .imageUrl(user.imageUrl)
                                .build())
                .articleModelList(
                        user.commentList.stream()
                                .map(comment -> {
                                    Article article = comment.article;
                                    return ArticleModel.builder()
                                            .id(article.id)
                                            .title(article.title)
                                            .imageUrl(article.imageUrl)
                                            .url(article.url)
                                            .build();
                                }).collect(Collectors.toList()))
                .build();
    }

    @Override
    public void update(UserForm userForm) throws NotFoundException {
        User user = userRepository.findById(userForm.id)
                .orElseThrow(() ->new NotFoundException("見つかりませんでした"));
        user.name = userForm.name;
        user.mail = userForm.mail;
        user.imageUrl = userForm.imageUrl;
        user.introduction = userForm.introduction;
        userRepository.saveAndFlush(user);
    }
}
