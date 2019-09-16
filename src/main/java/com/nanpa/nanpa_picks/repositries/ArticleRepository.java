package com.nanpa.nanpa_picks.repositries;

import com.nanpa.nanpa_picks.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
