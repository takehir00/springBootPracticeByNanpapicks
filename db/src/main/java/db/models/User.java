package db.models;

import javax.persistence.*;

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
}
