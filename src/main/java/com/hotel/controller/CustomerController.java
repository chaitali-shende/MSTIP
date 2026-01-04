package com.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotel.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
	private final CustomerService customerService;
	 @GetMapping
	    public String showCustomers(Model model) {
	        model.addAttribute("customers", customerService.getAllCustomers());
	        return "customers";  
	    }
}
