package com.example.grpcdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.grpcdemo.service.RestProxyHelloService;

@RestController
public class HelloRestProxyController {

	@Autowired
	RestProxyHelloService restProxyHelloService;

	@GetMapping("/hello/{first}/{last}/")
	public String sayHello(@PathVariable final String first, @PathVariable final String last) {
		return restProxyHelloService.sayHello(first, last);
	}
}
