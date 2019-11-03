package db.daos;

import db.models.User;

import java.util.Optional;

public interface UserDao {

    public Optional<User> findByMail(String mail);
}
