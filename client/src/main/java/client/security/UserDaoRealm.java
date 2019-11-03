package client.security;

import db.daos.impl.UserDaoImpl;
import db.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@EntityScan(basePackageClasses = User.class)
@EnableJpaRepositories(basePackageClasses = UserDaoImpl.class)
@Component
public class UserDaoRealm implements UserDetailsService {
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("USER"));
        //repositoryにmailで取得するメソッド定義する
        UserDaoImpl userDao = new UserDaoImpl(entityManager);
        User entity = userDao.findByMail(mail)
                .orElseThrow(() -> new UsernameNotFoundException(""));

        return new LoginUser(entity.mail, entity.password, authorityList);
    }
}
