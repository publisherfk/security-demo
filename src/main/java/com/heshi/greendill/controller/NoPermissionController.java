package com.heshi.greendill.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/NoPermission/base")
public class NoPermissionController {

	@GetMapping("/noPermission1")
	public String noPermission1() {
		return "无权限就可以访问";
	}
}
