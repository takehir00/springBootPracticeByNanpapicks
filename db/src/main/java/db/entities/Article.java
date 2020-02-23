package db.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public Long id;

    @Column
    public String url;

    @Column
    public String title;

    @Column
    public String imageUrl;

    @OrderBy("id DESC")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article")
    @Column(nullable = true)
    public List<Comment> commentList;
}
