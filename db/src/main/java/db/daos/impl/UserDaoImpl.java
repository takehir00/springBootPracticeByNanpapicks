package db.daos.impl;

import db.daos.UserDao;
import db.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> findByMail(String mail) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        query.select(root).where(builder.equal(root.get("mail"), mail));

        try {
            return Optional.of(
                    entityManager.createQuery(query)
                            .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
