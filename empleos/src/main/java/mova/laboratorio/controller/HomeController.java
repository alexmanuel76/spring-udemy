package mova.laboratorio.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import mova.laboratorio.modelo.Usuario;
import mova.laboratorio.modelo.Vacante;
import mova.laboratorio.service.ICategoriasService;
import mova.laboratorio.service.IVacanteService;

@Controller
public class HomeController {
	
	@Autowired
	private IVacanteService servicioVacante;
	
	@Autowired
	private ICategoriasService servicioCategoria;
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model modelo) {
		List<Vacante> listaEmpleos = servicioVacante.buscarTodas();
		modelo.addAttribute("empleos", listaEmpleos);
		
		return "tabla";
	}
	
	@GetMapping("/detalle")
	public String mostrarDetalle(Model modelo) {
		Vacante vacante = new Vacante();
		vacante.setNombre("Ingeniero de Informatica");
		vacante.setDescripcion("Puesto encargado de llevar a cabo el diseno y arquitectura de software");
		vacante.setFecha(new Date());
		vacante.setSalario(75000.00);
		
		modelo.addAttribute("vacante", vacante);
		
		return "detalle";
	}
	
	@GetMapping("/listado")
	public String mostrarListado(Model modelo) {
		List<String> listaEmpleos = new LinkedList<>();
		listaEmpleos.add("Ingeniero en Informatica");
		listaEmpleos.add("Auxiliar de Contabilidad");
		listaEmpleos.add("Vendedor");
		listaEmpleos.add("Arquitecto");
		modelo.addAttribute("empleos",listaEmpleos);
		
		return "listado";
	}
	
	@GetMapping("/")
	public String mostrarHome(Model modelo) {
		return "home";
	}
	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacante vacante) {
		System.out.println("Vacante:" +vacante);
		return "home";	
	}
	
	//// Fragmento asociados a Usuarios
	
	@GetMapping("/register")
	public String registrarse(Usuario usuario,Model modelo) {
		return "usuarios/formRegistro";
	}
	
	@ModelAttribute
	public void setGenericos(Model modelo) {
		Vacante vacanteSearch = new Vacante();
		vacanteSearch.reset();
		modelo.addAttribute("search", vacanteSearch);
		modelo.addAttribute("empleos", servicioVacante.buscarDestacadas());
		modelo.addAttribute("categorias", servicioCategoria.buscarTodas());
	}
}
