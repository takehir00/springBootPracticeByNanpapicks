package db.repositories;

import db.entities.Comment;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CommentRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Comment findById(Long commentId) {
        return entityManager.createQuery(
                "select comment from Comment comment where comment.id = :commentId" ,Comment.class)
                .setParameter("commentId", commentId)
                .getSingleResult();
    }

    public List<Comment> findAll() {
        return entityManager.createQuery(
                "select comment from Comment comment", Comment.class)
                .getResultList();
    }

    public int countAll() {
        List<Comment> commentList = entityManager.createQuery(
                "select comment from Comment comment", Comment.class)
                .getResultList();
        return commentList.size();
    }

    public List<Comment> findByOffsetAndLimit(int offset, int limit) {
        TypedQuery<Comment> query = entityManager
                .createQuery("select comment from Comment comment ORDER BY comment.id DESC", Comment.class)
                .setFirstResult(offset)
                .setMaxResults(limit);
        return query.getResultList();
    }

    public void create(Comment entity) {
        entityManager.persist(entity);
    }

    public void update(Comment entity) {
        entityManager.merge(entity);
    }

    public void delete(Comment entity) {
        entityManager.remove(entity);
    }
}
