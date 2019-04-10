package com.power.using.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetCharacterEncodingFilter implements Filter {

	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request;
		HttpServletResponse response;

		try {
			request = (HttpServletRequest) req;
			response = (HttpServletResponse) res;
		} catch (ClassCastException e) {
			throw new ServletException("http.non_http");
		}
		String encoding = filterConfig.getInitParameter("encoding");
		if(encoding==null){
			encoding="UTF-8";
		}
		
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		response.setContentType("text/html;chatset="+encoding);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
