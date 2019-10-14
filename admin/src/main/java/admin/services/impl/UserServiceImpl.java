package admin.services.impl;

import admin.forms.UserForm;
import db.models.User;
import db.repositries.UserRepository;
import admin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
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
        user.introduction = userForm.introduction;
        user.imageUrl = userForm.imageUrl;
        user.password = userForm.password;
        userRepository.save(user);
    }

    @Override
    public List<db.models.User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public Optional<db.models.User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void update(UserForm userForm) {
        User user = new User();
        user.id = userForm.id;
        user.name = userForm.name;
        user.mail = userForm.mail;
        user.introduction = userForm.introduction;
        user.imageUrl = userForm.imageUrl;
        user.password = userForm.password;
        userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(UserForm userForm) {
       Optional<User> userOpt = userRepository.findById(userForm.id);
       userOpt.ifPresent(user -> {
           userRepository.delete(user);
       });
    }
}
