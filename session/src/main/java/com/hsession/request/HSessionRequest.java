package com.hsession.request;

import com.hsession.session.HSession;
import com.hsession.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

public class HSessionRequest extends HttpServletRequestWrapper {

    private static final Logger logger = LoggerFactory.getLogger(HSessionRequest.class);

    private ServletContext context;
    private HSession session;
    private HttpServletResponse response;
    private String sessionKey = "JSESSIONID";
    private boolean sessionIdValid = false;

    public HSessionRequest(HttpServletRequest request,
                           HttpServletResponse response,
                           ServletContext context) {
        super(request);
        this.response = response;
        this.context = context;
    }
    public HttpSession getSession(boolean create) {
        try {
            Cookie[] cookies = this.getCookies();
            String sessionId = null;
            if(cookies != null){
                for(Cookie cookie : cookies){
                    if(sessionKey.equals(cookie.getName())){
                        sessionId = cookie.getValue();
                        break;
                    }
                }
            }
            if(StringUtils.isEmpty(sessionId)){
                sessionId = UUID.randomUUID().toString();
                Cookie cookie = new Cookie(sessionKey, sessionId);
                this.response.addCookie(cookie);
            }
            this.session = new HSession(this.context,sessionId);
        }catch (Exception e){
            logger.error("",e);
        }
        return this.session;
    }

    public HttpSession getSession() {
        return getSession(false);
    }

}