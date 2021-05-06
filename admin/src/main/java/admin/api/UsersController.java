package admin.api;

import admin.models.user.UserReadModel;
import admin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserReadModel> index() {
        return userService.getAll()
                .stream()
                .map(user -> {
                    return UserReadModel.builder()
                            .id(user.id)
                            .name(user.name)
                            .mail(user.mail)
                            .introduction(user.introduction)
                            .build();
                }).collect(Collectors.toList());
    }
}
