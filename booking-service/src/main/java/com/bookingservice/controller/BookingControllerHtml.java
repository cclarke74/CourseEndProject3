package com.bookingservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookingControllerHtml {
	@GetMapping("/test")
	public String test(){
		return "test";
	}
	@GetMapping("/home")
	public String indexPage() {
		System.out.println("---------------------------------------");
		return "home.html";
	}
	@GetMapping("/bookcab")
	public String bookCab() {
		 return "book-cab";
	}
	@GetMapping("/register")
	public String registerUser() {
		return "register.html";
	}
	
	@GetMapping("/calculatefare")
	public String calculateFare() {
		return "fare.html";
	}




}
