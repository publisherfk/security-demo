package com.heshi.greendill.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

//@Component
public class GreenDillLoginEntryPoint implements AuthenticationEntryPoint {

	private static final Logger logger = LoggerFactory.getLogger(GreenDillLoginEntryPoint.class);
	private String tagetUrl = "https://www.baidu.com";

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException paramAuthenticationException) throws IOException, ServletException {
		logger.error(paramAuthenticationException.getLocalizedMessage(), paramAuthenticationException);
		response.sendRedirect(tagetUrl);

	}

	public String getTagetUrl() {
		return tagetUrl;
	}

	public void setTagetUrl(String tagetUrl) {
		this.tagetUrl = tagetUrl;
	}

}
