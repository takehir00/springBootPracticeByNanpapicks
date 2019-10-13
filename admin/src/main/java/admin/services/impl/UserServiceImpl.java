package admin.services.impl;

import admin.forms.UserForm;
import admin.models.User;
import admin.repositries.UserRepository;
import admin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
}
