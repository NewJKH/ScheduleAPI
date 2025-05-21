package org.jkh.scheduleapi.common.exception.login;

public class LoginRequiredException extends RuntimeException {
    public LoginRequiredException(String message) {
        super(message);
    }
    public LoginRequiredException(){
      super(" 로그인 해주세요. ");
    }
}
