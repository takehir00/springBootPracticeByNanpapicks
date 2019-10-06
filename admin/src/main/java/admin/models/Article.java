package admin.models;

import javax.persistence.*;

@Entity
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

}
