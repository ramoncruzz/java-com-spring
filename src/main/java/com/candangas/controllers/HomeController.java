package com.candangas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v0")
public class HomeController {

	@GetMapping
	public String index()
	{
		
		return "Olá";
	}
	
}
