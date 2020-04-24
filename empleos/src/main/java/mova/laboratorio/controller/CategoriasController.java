package mova.laboratorio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {

	//@GetMapping("/index")
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model modelo) {
		return "categorias/listaCategorias";
	}
	
	//@GetMapping("/create")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String crear() {
		
		return "categorias/formCategorias";
		
	}
	
	//PostMapping("/save")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(@RequestParam("nombre") String nombre,@RequestParam("descripcion") String descripcion) {
		System.out.print("Nombre: "+nombre+" , ");
		System.out.println("Descripcion: "+descripcion);
		return "categorias/listaCategorias";
	}
}
