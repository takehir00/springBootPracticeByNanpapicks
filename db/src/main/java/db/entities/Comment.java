package db.entities;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public Long id;

    @Column
    public String content;

    // 紐づいてるユーザーエンティティのデータ。カラム名はuser_id。
    // 多対１、一人のユーザーが複数コメントする。
    @ManyToOne
    public User user;

    // 紐づいてる記事エンティティのデータ、カラム名はarticle_id。
    // 多対１。一つの記事にたくさんコメントがつく
    @ManyToOne
    public Article article;
}
