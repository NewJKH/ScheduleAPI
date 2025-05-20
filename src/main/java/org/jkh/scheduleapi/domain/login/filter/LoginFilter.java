package org.jkh.scheduleapi.domain.login.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/login","/api/members","/comment","/comment/*","/api/schedule"};
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        if ( !isWhitelist(requestURI) ){
            HttpSession session = httpRequest.getSession(false);
            if ( session == null || session.getAttribute("loginMember") == null ){
                throw new RuntimeException(" 로그인해주세요.");
            }
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    private boolean isWhitelist(String requestURI){
        return PatternMatchUtils.simpleMatch(WHITE_LIST,requestURI);
    }
}
