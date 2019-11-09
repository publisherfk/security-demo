package com.heshi.greendill.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission/base")
public class PermissionController {

	@GetMapping("/permission1")
	public String permission1() {
		return "需要登录认证通过才可以访问";
	}
}
