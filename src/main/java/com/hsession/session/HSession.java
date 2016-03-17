package com.hsession.session;

import com.hsession.cache.CacheManager;
import com.hsession.exception.ConstructorException;
import com.hsession.utils.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class HSession implements HttpSession ,Serializable{

	private static final long serialVersionUID = -3185547114527487307L;
	
	private String id;
	private boolean delay = false;
	private HSessionHeader sessionHeader;
	private HSessionAttribute sessionAttribute;
	private ServletContext context;
	private int maxInactiveInterval = 60*10;
	private boolean isNew = true;
	private String header_cache_key;
	private String attribute_cache_key;


	public HSession(ServletContext context,String id) throws Exception{
		if(StringUtils.isEmpty(id) || context == null){
			throw new ConstructorException("session Constructor error");
		}
		this.context = context;
		this.id = id;
		this.attribute_cache_key = this.id + "_attribute";
		this.header_cache_key = this.id + "_header";
		sessionHeader = (HSessionHeader) CacheManager.get(this.header_cache_key);
		sessionAttribute = (HSessionAttribute) CacheManager.get(this.attribute_cache_key);
	}
	public HSession(){
	}

	public long getCreationTime() {
		return sessionHeader.getCreationTime();
	}

	public String getId() {
		return id;
	}

	public long getLastAccessedTime() {
		return sessionHeader.getLastAccessedTime();
	}

	public ServletContext getServletContext() {
		return context;
	}

	public void setMaxInactiveInterval(int i) {
		this.maxInactiveInterval = i;
	}

	public int getMaxInactiveInterval() {
		return this.maxInactiveInterval;
	}

	@Deprecated
	public HttpSessionContext getSessionContext() {
		return null;
	}

	public Object getAttribute(String s) {
		return this.getSessionAttribute().getAttribute(s);
	}

	@Deprecated
	public Object getValue(String s) {
		return getAttribute(s);
	}

	public Enumeration<String> getAttributeNames() {
		return this.getSessionAttribute().getAttributeNames();
	}

	@Deprecated
	public String[] getValueNames() {
		List<String> list = new ArrayList<String>();
		Enumeration<String> enums = getAttributeNames();
		while (enums.hasMoreElements()){
			list.add(enums.nextElement());
		}
		return list.toArray(new String[list.size()]);
	}

	public void setAttribute(String s, Object o) {
		this.getSessionAttribute().setAttribute(s,o);
	}
	@Deprecated
	public void putValue(String s, Object o) {
		setAttribute(s,o);
	}

	public void removeAttribute(String s) {
		this.getSessionAttribute().removeAttribute(s);
	}

	@Deprecated
	public void removeValue(String s) {
		removeAttribute(s);
	}

	public void invalidate() {
		getSessionAttribute().removeAllAttribute();
	}

	public boolean isNew() {
		if(isNew){
			isNew = false;
			return true;
		}
		return false;
	}

	public void syncCache(){
		CacheManager.set(this.header_cache_key,this.sessionHeader,this.maxInactiveInterval);
		CacheManager.set(this.attribute_cache_key,this.sessionAttribute,this.maxInactiveInterval);
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
