package admin.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    /**
     * @param loginFormUrl URL where the login page can be found. Should either be
     *                     relative to the web-app context path (include a leading {@code /}) or an absolute
     *                     URL.
     */
    public CustomLoginUrlAuthenticationEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
    }

    @Override
    protected String buildRedirectUrlToLoginPage(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) {
        String redirectUrl = super.buildRedirectUrlToLoginPage(request, response, authException);
        // sessionがtimeoutした時にクエリパラメータにtimeoutを持たせる。
        // ようにしているけどタイムアウト画面を用意する感じでもいいと思う。要件次第。
        if (isRequestedSessionInvalid(request)) {
            redirectUrl += redirectUrl.contains("?") ? "&" : "?";
            redirectUrl += "timeout";
        }
        return redirectUrl;
    }

    private boolean isRequestedSessionInvalid(HttpServletRequest request) {
        return request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid();
    }
}
