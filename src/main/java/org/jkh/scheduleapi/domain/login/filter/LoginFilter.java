package org.jkh.scheduleapi.domain.login.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jkh.scheduleapi.common.config.AuthProperties;
import org.jkh.scheduleapi.common.exception.login.LoginRequiredException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginFilter implements Filter {

    private final AuthProperties authProperties;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        if ( !isWhitelist(requestURI) ){
            HttpSession session = httpRequest.getSession(false);
            if ( session == null || session.getAttribute("loginMember") == null ){
                throw new LoginRequiredException();
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    private boolean isWhitelist(String requestURI) {
        return authProperties.getWhitelist().stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, requestURI));
    }
}
