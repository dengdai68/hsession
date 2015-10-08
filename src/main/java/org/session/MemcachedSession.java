package org.session;

import java.io.Serializable;

import javax.servlet.http.HttpSession;


public class MemcachedSession implements HttpSession ,Serializable{

	private static final long serialVersionUID = -3185547114527487307L;
	
	private String sessionId;
	
	public MemcachedSession(String sessionId){
		this.sessionId = sessionId;
	}
	public MemcachedSession(){
		
	}

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public void put(String key, Object value) {
		MemcachedUtils.set(sessionId + "_" + key, value);
	}

	@Override
	public Object get(String key) {
		return MemcachedUtils.get(sessionId + "_" + key);
	}

	@Override
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Override
	public void reset() {
		this.sessionId = null;
		
	}

}
