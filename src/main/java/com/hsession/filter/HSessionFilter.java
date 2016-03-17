package com.hsession.filter;

import com.hsession.request.HSessionRequest;
import com.hsession.session.HSession;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HSessionFilter implements Filter{
	private static final Logger logger = Logger.getLogger(HSessionFilter.class);

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
		HSession session = (HSession)hsessionRequest.getSession();
		if (session != null){
			session.syncCache();
		}
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("RequestFilter Initializing ");
		this.filterConfig = filterConfig;
	}
}
