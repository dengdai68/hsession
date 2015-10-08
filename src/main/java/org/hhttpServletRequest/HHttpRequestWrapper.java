package org.hhttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

/**
 * @author
 * @date
 * @describe 使用memcached，替换原始的request的getSession相关功能
 * 
 */
public class HHttpRequestWrapper extends HttpServletRequestWrapper {
	
	private HttpSession _session;

	/**
	 * 封装http请求
	 * 
	 * @param request
	 */
	public HHttpRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	
	public HttpSession getSession(Boolean bool){
		return getSession();
	}
	
	public HttpSession getSession(){
		if(_session == null){
			_session = 
		}
		return _session;
	}
}
