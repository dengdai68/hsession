package com.hsession.session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.Serializable;
import java.util.Enumeration;


public class HSessionSession implements HttpSession ,Serializable{

	private static final long serialVersionUID = -3185547114527487307L;
	
	private String sessionId;
	
	public HSessionSession(String sessionId){
		this.sessionId = sessionId;
	}
	public HSessionSession(){
		
	}

	@Override
	public long getCreationTime() {
		return 0;
	}

	@Override
	public String getId() {
		return sessionId;
	}

	@Override
	public long getLastAccessedTime() {
		return 0;
	}

	@Override
	public ServletContext getServletContext() {
		return null;
	}

	@Override
	public void setMaxInactiveInterval(int i) {

	}

	@Override
	public int getMaxInactiveInterval() {
		return 0;
	}

	@Override
	public HttpSessionContext getSessionContext() {
		return null;
	}

	@Override
	public Object getAttribute(String s) {
		return null;
	}

	@Override
	public Object getValue(String s) {
		return null;
	}

	@Override
	public Enumeration getAttributeNames() {
		return null;
	}

	@Override
	public String[] getValueNames() {
		return new String[0];
	}

	@Override
	public void setAttribute(String s, Object o) {

	}

	@Override
	public void putValue(String s, Object o) {

	}

	@Override
	public void removeAttribute(String s) {

	}

	@Override
	public void removeValue(String s) {

	}

	@Override
	public void invalidate() {

	}

	@Override
	public boolean isNew() {
		return false;
	}
}
