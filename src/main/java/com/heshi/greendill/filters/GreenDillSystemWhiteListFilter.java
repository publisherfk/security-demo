package com.heshi.greendill.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

public class GreenDillSystemWhiteListFilter extends OncePerRequestFilter {

	private String[] whiteList;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		for (String url : whiteList) {
			RequestMatcher matcher = new AntPathRequestMatcher(url.trim());
			if (matcher.matches(request)) {
//				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	public String[] getWhiteList() {
		return whiteList;
	}

	public void setWhiteList(String[] whiteList) {
		this.whiteList = whiteList;
	}

}
