package org.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class HHttpServletRequestFilter  implements Filter{
	
	private static final Logger logger = Logger.getLogger(HHttpServletRequestFilter.class);

	@Override
	public void destroy() {
		logger.debug("RequestFilter destroy end");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
	
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		logger.info("RequestFilter Initializing ");
		
	}

}
