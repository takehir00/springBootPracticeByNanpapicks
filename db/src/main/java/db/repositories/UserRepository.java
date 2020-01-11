package db.repositories;

import db.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Optional<User> findById(Long userId) {
        User user = entityManager.createQuery(
                "select user from User user where user.id = :userId", User.class)
                .setParameter("userId", userId)
                .getSingleResult();
        return Optional.of(user);
    }

    public List<User> findAll() {
        return entityManager.createQuery(
                "select article from Article article", User.class)
                .getResultList();
    }

    public void create(User entity) {
        entityManager.persist(entity);
    }

    public void update(User entity) { entityManager.merge(entity); }

    public void delete(User entity) {
        entityManager.remove(entity);
    }
}
