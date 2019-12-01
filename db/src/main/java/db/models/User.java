package db.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public Long id;

    @Column
    public String name;

    @Column
    public String mail;

    @Column
    public String introduction;

    @Column
    public String imageUrl;

    @Column
    public String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    @Column(nullable = true)
    public List<Comment> commentList;
}
