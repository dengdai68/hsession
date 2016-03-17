package com.hsession.request;

import com.hsession.session.HSessionSession;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HSessionRequest extends HttpServletRequestWrapper {

    private ServletContext context;
    private HSessionSession session;
    private HttpServletResponse response;

    public HSessionRequest(HttpServletRequest request,
                           HttpServletResponse response,
                           ServletContext context) {
        super(request);
    }
    public HttpSession getSession(boolean create) {
        return session;
    }

    public HttpSession getSession() {
        return getSession(false);
    }

}