package br.appLogin.appLogin.service.authenticator;

import br.appLogin.appLogin.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuarioLogado") != null) {
            return true;
        }
        if (CookieService.getCookie(request, "UsuarioId") != null) {
            return true;
        }
        response.sendRedirect("/login");
        return false;
    }
}
