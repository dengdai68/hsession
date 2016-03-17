package com.hsession.session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;


public class HSession implements HttpSession ,Serializable{

	private static final long serialVersionUID = -3185547114527487307L;
	
	private String sessionId;
	private boolean delay = false;
	private HSessionHeader sessionHeader;
	private HSessionAttribute sessionAttribute;
	private ServletContext context;
	private int MaxInactiveInterval = 0;
	private boolean isNew = true;


	public HSession(ServletContext context,String sessionId){
		this.context = context;
		this.sessionId = sessionId;
	}
	public HSession(){
	}
	@Override
	public long getCreationTime() {
		return sessionHeader.getCreationTime();
	}

	@Override
	public String getId() {
		return sessionId;
	}

	@Override
	public long getLastAccessedTime() {
		return sessionHeader.getLastAccessedTime();
	}

	@Override
	public ServletContext getServletContext() {
		return context;
	}

	@Override
	public void setMaxInactiveInterval(int i) {
		this.MaxInactiveInterval = i;
	}

	@Override
	public int getMaxInactiveInterval() {
		return this.MaxInactiveInterval;
	}

	@Override
	@Deprecated
	public HttpSessionContext getSessionContext() {
		return null;
	}

	@Override
	public Object getAttribute(String s) {
		return this.getSessionAttribute().getAttribute(s);
	}

	@Override
	public Object getValue(String s) {
		return getAttribute(s);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return this.getSessionAttribute().getAttributeNames();
	}

	@Override
	public String[] getValueNames() {
		List<String> list = new ArrayList<String>();
		Enumeration<String> enums = getAttributeNames();
		while (enums.hasMoreElements()){
			list.add(enums.nextElement());
		}
		return list.toArray(new String[list.size()]);
	}

	@Override
	public void setAttribute(String s, Object o) {
		this.getSessionAttribute().setAttribute(s,o);
	}

	@Override
	public void putValue(String s, Object o) {
		setAttribute(s,o);
	}

	@Override
	public void removeAttribute(String s) {
		this.getSessionAttribute().removeAttribute(s);
	}

	@Override
	public void removeValue(String s) {
		removeAttribute(s);
	}

	@Override
	public void invalidate() {
		//TODO
	}

	@Override
	public boolean isNew() {
		if(isNew){
			isNew = false;
			return true;
		}
		return false;
	}

	public void syncCache(){
		//TODO
	}

	private HSessionAttribute getSessionAttribute() {
		if(sessionAttribute == null){
			sessionAttribute = new HSessionAttribute();
		}
		return sessionAttribute;
	}

	private HSessionHeader getSessionHeader(){
		if(sessionHeader == null){
			sessionHeader = new HSessionHeader();
		}
		return sessionHeader;
	}
}
