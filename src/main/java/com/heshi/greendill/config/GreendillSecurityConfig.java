package com.heshi.greendill.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.heshi.greendill.filters.GreenDillAuthenticationFilter;
import com.heshi.greendill.filters.GreenDillSystemWhiteListFilter;

@Configuration
@EnableWebSecurity // 启用web权限
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法验证
public class GreendillSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(GreendillSecurityConfig.class);

	@Autowired
	public GreenDillAuthenticationFilter authenticationFilter;

	@Autowired(required = false)
	private GreenDillLoginEntryPoint loginEntryPoint;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOGGER.debug(
				"Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");

		http.authorizeRequests().antMatchers(getWhiteList()).permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/greendill-login.html").loginProcessingUrl("/greendill/login").and().httpBasic();
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
//		http.addFilterBefore(systemWhiteFilter(), FilterSecurityInterceptor.class);
//		http.exceptionHandling().authenticationEntryPoint(loginEntryPoint);
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
		http.csrf().disable(); // 禁用CSRF
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
//		web.ignoring().antMatchers("/NoPermission/base/noPermission1");
	}

	public String[] getWhiteList() {
		List<String> list = new ArrayList<String>();
		list.add("/NoPermission/base/noPermission1");
		list.add("/v2/**");
		list.add("/**.html");
		list.add("/greendill-login.html"); 	
		return list.toArray(new String[0]);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

//	@Bean
	public GreenDillSystemWhiteListFilter systemWhiteFilter() {
		GreenDillSystemWhiteListFilter systemWhiteFilter = new GreenDillSystemWhiteListFilter();
		systemWhiteFilter.setWhiteList(getWhiteList());
		return systemWhiteFilter;
	}
}