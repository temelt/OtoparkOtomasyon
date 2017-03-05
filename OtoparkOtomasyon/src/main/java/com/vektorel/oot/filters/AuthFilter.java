package com.vektorel.oot.filters;

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

public class AuthFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain filterChain) throws IOException, ServletException {
		try {
			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);

			String reqURI = reqt.getRequestURI();
			if (reqURI.indexOf("/login.jsf") >= 0 || (ses != null && ses.getAttribute("username") != null)
			    || reqURI.contains("javax.faces.resource"))
				filterChain.doFilter(request, response);
			else
				resp.sendRedirect(reqt.getContextPath() + "/login.jsf");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
