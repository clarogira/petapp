package com.petapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.petapp.repository.ClienteRepository;



@Controller
@RequestMapping("/os")
public class OsController {
	@Autowired
    ClienteRepository cr;

	
	@GetMapping("/nova")
	public String nova() {
		return "os/cadastroOs";
	}
	
}