package client.controllers;

import db.daos.impl.UserDaoImpl;
import db.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
public class HomeController {
    @PersistenceContext
    EntityManager entityManager;

    /**
     * ユーザープリンシパルからユーザーのメールアドレスを取得する
     *
     * @return
     */
    protected String getMail() {
        Object principal = getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    /**
     * メールアドレスからユーザーエンティティを取得する
     *
     * @return
     */
    protected User getUser() {
        UserDaoImpl userDao = new UserDaoImpl(entityManager);
        return userDao.findByMail(getMail())
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }

    /**
     * ユーザープリンシパルを取得
     *
     * @return
     */
    private Object getPrincipal() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
