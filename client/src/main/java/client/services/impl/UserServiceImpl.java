package client.services.impl;

import client.forms.UserForm;
import client.services.UserService;
import db.models.User;
import db.repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EntityScan(basePackageClasses = User.class)
@EnableJpaRepositories(basePackageClasses = User.class)
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

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
    public void update(UserForm userForm) {
        User user = new User();
        user.id = userForm.id;
        user.name = userForm.name;
        user.mail = userForm.mail;
        user.imageUrl = userForm.imageUrl;
        user.introduction = userForm.introduction;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.password = encoder.encode(userForm.password);
        userRepository.saveAndFlush(user);
    }
}
