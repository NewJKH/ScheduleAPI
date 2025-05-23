package org.jkh.scheduleapi.domain.login.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jkh.scheduleapi.common.config.AuthProperties;
import org.jkh.scheduleapi.common.exception.login.LoginRequiredException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
public class LoginFilter implements Filter {

    private final AuthProperties authProperties;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final HandlerExceptionResolver resolver;

    public LoginFilter(AuthProperties authProperties,@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.authProperties = authProperties;
        this.resolver = resolver;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String requestURI = httpRequest.getRequestURI();
        if ( !isWhitelist(requestURI) ){
            HttpSession session = httpRequest.getSession(false);
            if ( session == null || session.getAttribute("loginMember") == null ){
                resolver.resolveException(httpRequest, httpResponse, null, new LoginRequiredException());
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);


    }

    private boolean isWhitelist(String requestURI) {
        return authProperties.getWhitelist().stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, requestURI));
    }
}
