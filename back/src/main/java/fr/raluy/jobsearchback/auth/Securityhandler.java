package fr.raluy.jobsearchback.auth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
public class Securityhandler implements AuthenticationSuccessHandler, LogoutHandler, AuthenticationEntryPoint {

    public void onAuthenticationSuccess(HttpServletRequest request,   HttpServletResponse response, Authentication authentication) throws IOException  {
        response.sendRedirect("/hello");
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            response.sendRedirect("/hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}