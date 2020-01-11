package db.repositories;

import db.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
                "select user from User user", User.class)
                .getResultList();
    }

    public int countAll() {
        List<User> articleList = entityManager.createQuery(
                "select user from User user", User.class)
                .getResultList();
        return articleList.size();
    }

    public void create(User entity) {
        entityManager.persist(entity);
    }

    public void update(User entity) { entityManager.merge(entity); }

    public void delete(User entity) {
        entityManager.remove(entity);
    }

    public List<User> findByOffsetAndLimit(int offset, int limit) {
        TypedQuery<User> query = entityManager
                .createQuery(
                        "select user from User user ORDER BY user.id DESC", User.class)
                .setFirstResult(offset)
                .setMaxResults(limit);
        return query.getResultList();
    }
}
