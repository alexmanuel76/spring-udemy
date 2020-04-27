package mova.laboratorio.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mova.laboratorio.modelo.Vacante;
import mova.laboratorio.service.IVacanteService;

@Controller
public class HomeController {
	
	@Autowired
	private IVacanteService servicioVacante;
	
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
		List<Vacante> listaEmpleos = servicioVacante.buscarTodas();
		modelo.addAttribute("empleos", listaEmpleos);
		return "home";
	}
}
