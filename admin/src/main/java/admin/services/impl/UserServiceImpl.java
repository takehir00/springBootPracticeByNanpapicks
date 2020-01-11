package admin.services.impl;

import admin.forms.user.UserForm;
import admin.forms.user.UserUpdateForm;
import admin.util.PageUtil;
import db.entities.User;
import db.repositories.UserRepository;
import admin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.password = encoder.encode(userForm.password);
        userRepository.create(user);
    }

    @Override
    public List<db.entities.User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public List<User> getByOffsetAndLimit(int offset, int limit) {
        return userRepository.findByOffsetAndLimit(offset, limit);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void update(UserUpdateForm userForm) {
        Optional<User> userOpt = userRepository.findById(userForm.id);
        userOpt.ifPresent(user -> {
            user.name = userForm.name;
            user.mail = userForm.mail;
            user.introduction = userForm.introduction;
            user.imageUrl = userForm.imageUrl;
            userRepository.update(user);
        });
    }

    @Override
    public void delete(UserForm userForm) {
       Optional<User> userOpt = userRepository.findById(userForm.id);
       userOpt.ifPresent(user -> {
           userRepository.delete(user);
       });
    }

    @Override
    public UserUpdateForm updateForm(Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return UserUpdateForm.builder()
                    .id(user.id)
                    .name(user.name)
                    .mail(user.mail)
                    .introduction(user.introduction)
                    .imageUrl(user.imageUrl)
                    .build();
        }
        return null;
    }

    @Override
    public int getPageCount(int limit) {
        return PageUtil.calculatePageCount(userRepository.countAll(), limit);
    }
}
