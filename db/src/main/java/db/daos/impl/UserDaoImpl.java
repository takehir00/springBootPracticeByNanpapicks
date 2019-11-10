package db.daos.impl;

import db.daos.UserDao;
import db.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
        query.select(root).where(builder.equal(root.get("mail"), mail));
        try {
            return Optional.of(
                    entityManager.createQuery(query)
                            .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByIdAndMail(Long id, String mail) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        Predicate predicateForId = builder.equal(root.get("id"), id);
        Predicate predicateForMail = builder.equal(root.get("mail"), mail);
        Predicate finalPredicate = builder.and(predicateForId, predicateForMail);

        query.select(root)
                .where(finalPredicate);

     try {
         return Optional.of(
               entityManager.createQuery(query)
                       .getSingleResult());
     } catch(NoResultException e) {
         return Optional.empty();
     }
    }
}
