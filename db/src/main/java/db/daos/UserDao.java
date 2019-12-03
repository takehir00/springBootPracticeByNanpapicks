package db.daos;

import db.entities.User;

import java.util.Optional;

public interface UserDao {

    public Optional<User> findByMail(String mail);

    public Optional<User> findByIdAndMail(Long id, String mail);
}
