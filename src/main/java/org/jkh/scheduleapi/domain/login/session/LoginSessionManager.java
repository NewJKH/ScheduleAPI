package org.jkh.scheduleapi.domain.login.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.jkh.scheduleapi.domain.login.dto.response.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class LoginSessionManager {
    public void save(HttpServletRequest httpRequest, LoginResponse loginResponse) {
        HttpSession session = httpRequest.getSession();
        session.setAttribute("loginMember", loginResponse);
    }
}
