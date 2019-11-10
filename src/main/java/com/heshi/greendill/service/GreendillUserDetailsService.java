package com.heshi.greendill.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class GreendillUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(GreendillUserDetailsService.class);

	@Value("${spring.security.user.name}")
	private String userName;
	@Value("${spring.security.user.password}")
	private String password;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("curent user name is {}", userName);
		logger.info("curent password is {}", password);
		logger.info("dto username is {}", username);
		return new User(userName, passwordEncoder.encode(password),
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
