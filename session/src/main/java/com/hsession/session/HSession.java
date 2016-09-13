package com.hsession.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsession.cache.CacheManager;
import com.hsession.exception.ConstructorException;
import com.hsession.utils.StringUtils;


public class HSession implements HttpSession ,Serializable{

	private static final Logger logger = LoggerFactory.getLogger(HSession.class);

	private static final long serialVersionUID = -3185547114527487307L;
	
	private String id;
	private boolean delay = false;
	private HSessionHeader sessionHeader;
	private HSessionAttribute sessionAttribute;
	private ServletContext context;
	private int maxInactiveInterval = 60*1000*30;
	private boolean isNew = true;
	private String header_cache_key;
	private String attribute_cache_key;
	private boolean idValid;


	public HSession(){
	}

	public HSession(ServletContext context, String sessionId, int
			maxInactiveInterval) throws Exception{
		if(StringUtils.isEmpty(sessionId) || context == null){
			throw new ConstructorException("session Constructor error");
		}
		this.context = context;
		this.id = sessionId;
		this.attribute_cache_key = this.id + "_attribute";
		this.header_cache_key = this.id + "_header";
		sessionHeader = (HSessionHeader) CacheManager.get(this.header_cache_key);
		if(sessionHeader == null){
			sessionHeader = new HSessionHeader();
		}else{
			sessionHeader.setNew(false);
		}
		sessionAttribute = (HSessionAttribute) CacheManager.get(this.attribute_cache_key);
		if(sessionAttribute == null){
			sessionAttribute = new HSessionAttribute();
		}
		this.sessionHeader.setLastAccessedTime(System.currentTimeMillis());
		if(maxInactiveInterval != 0){
			this.maxInactiveInterval = maxInactiveInterval;
		}
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
		return this.sessionHeader.isNew();
	}

	public void syncCache(){
		CacheManager.set(this.header_cache_key,this.sessionHeader,this.maxInactiveInterval);
		logger.debug("synchronize cache key:{} value:{}",this.header_cache_key,this.sessionHeader);
		CacheManager.set(this.attribute_cache_key,this.sessionAttribute,this.maxInactiveInterval);
		logger.debug("synchronize cache key:{} value:{}",this.attribute_cache_key,this.sessionAttribute);
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

	public boolean isIdValid() {
		return idValid;
	}

	public void setIdValid(boolean idValid) {
		this.idValid = idValid;
	}
}
