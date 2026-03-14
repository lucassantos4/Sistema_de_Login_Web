package br.appLogin.appLogin.service.authenticator;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorAppConig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor logininterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logininterceptor).excludePathPatterns(
                "/login",
                "/cadastroUsuario",
                "/",
                "/error",
                "/static/**",
                "/**/*.css",
                "/**/*.js"

        );
    }
}
