package db.repositories;


import db.entities.Article;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class ArticleRepository {
    @PersistenceContext
    EntityManager entityManager;

    public Article findById(Long articleId) {
        try {
            return entityManager.createQuery(
                    "select article from Article article where article.id = :articleId", Article.class)
                    .setParameter("articleId", articleId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Article> findAll() {
        return entityManager.createQuery(
                "select article from Article article", Article.class)
                .getResultList();
    }

    public int countAll() {
        List<Article> articleList = entityManager.createQuery(
                "select article from Article article", Article.class)
                .getResultList();
        return articleList.size();
    }

    public List<Article> findByOffsetAndLimit(int offset, int limit) {
        TypedQuery<Article> query = entityManager
                .createQuery(
                        "select article from Article article ORDER BY article.id DESC", Article.class)
                .setFirstResult(offset)
                .setMaxResults(limit);
        return query.getResultList();
    }

    public void create(Article entity) {
        entityManager.persist(entity);
    }

    public void update(Article entity) {
        entityManager.merge(entity);
    }

    public void delete(Article entity) {
        entityManager.remove(entity);
    }
}
