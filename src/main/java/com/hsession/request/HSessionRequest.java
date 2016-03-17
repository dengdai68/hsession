package com.hsession.request;

import com.hsession.session.HSession;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

public class HSessionRequest extends HttpServletRequestWrapper {

    private static final Logger logger = Logger.getLogger(HSessionRequest.class);

    private ServletContext context;
    private HSession session;
    private HttpServletResponse response;
    private String sessionKey = "sessionId";

    public HSessionRequest(HttpServletRequest request,
                           HttpServletResponse response,
                           ServletContext context) {
        super(request);
    }
    public HttpSession getSession(boolean create) {
        if(create){
            if(this.session == null){
                buildSession();
            }
        }
        return session;
    }

    public HttpSession getSession() {
        return getSession(false);
    }

    private void buildSession(){
        try {
            Cookie[] cookies = this.getCookies();
            String sessionId = null;
            for(Cookie cookie : cookies){
                if(sessionKey.equals(cookie.getName())){
                    sessionId = cookie.getValue();
                    break;
                }
            }
            if(sessionId == null){
                sessionId = System.currentTimeMillis() + "";
                Cookie cookie = new Cookie(sessionKey, sessionId);
                this.response.addCookie(cookie);
            }
            this.session = new HSession(this.context,sessionId);
        }catch (Exception e){
            logger.error("",e);
        }
    }
}