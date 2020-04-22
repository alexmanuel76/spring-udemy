package mova.laboratorio.holaMundo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping("/")
	public String inicio() {
		return "Hola Mundo <br><h1>CABRONES!!!</h1>";
	}

}
