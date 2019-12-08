package db.repositories;

import db.entities.Comment;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CommentRepository {
    @PersistenceContext
    EntityManager entityManager;

    public List<Comment> findAll() {
        return entityManager.createQuery(
                "select comment from Comment comment", Comment.class)
                .getResultList();
    }
}
