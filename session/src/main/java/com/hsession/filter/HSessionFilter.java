package com.hsession.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsession.request.HSessionRequest;
import com.hsession.session.HSession;

public class HSessionFilter implements Filter{
	private static final Logger logger = LoggerFactory.getLogger(HSessionFilter.class);

	private FilterConfig filterConfig;
	@Override
	public void destroy() {
		logger.debug("RequestFilter destroy end");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HSessionRequest hsessionRequest = new HSessionRequest(httpRequest, httpResponse,
				filterConfig.getServletContext());
		chain.doFilter(hsessionRequest,httpResponse);
		HttpSession session = hsessionRequest.getSession();
		session.setAttribute("userName","nihao");
		if (session != null && session instanceof HSession){
			((HSession)session).syncCache();
		}
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("RequestFilter Initializing ");
		this.filterConfig = filterConfig;
	}
}
