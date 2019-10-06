package admin.models;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    public Long id;

    @Column
    String name;

    @Column
    String imageName;

    @Column
    String passWord;

    @Column
    String email;
}
