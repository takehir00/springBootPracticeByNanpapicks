package client.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    /** ユーザープリンシパル */
    protected final Object principal;

    public HomeController() {
        this.principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    /**
     * ユーザープリンシパルからユーザーのメールアドレスを取得する
     *
     * @return
     */
    protected String getMail() {
        if (principal instanceof UserDetails) {
            //ユーザー名からユーザー情報を取得する
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
