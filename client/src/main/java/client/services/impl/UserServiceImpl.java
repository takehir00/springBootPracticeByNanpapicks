package client.services.impl;

import client.forms.UserForm;
import client.services.UserService;
import db.models.User;
import db.repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

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
        user.password = userForm.password;
        userRepository.save(user);
    }
}
