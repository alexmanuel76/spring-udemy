package mova.laboratorio.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import mova.laboratorio.modelo.Vacante;

@Controller
public class HomeController {
	
	@GetMapping("/tabla")
	public String mostrarTabla(Model modelo) {
		List<Vacante> listaEmpleos= devolverLista();
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
		/*
		modelo.addAttribute("mensaje", "Bienvenido a Empleos App");
		modelo.addAttribute("fecha",new Date());
		*/
		
		String nombre = "Auxiliar de Contabilidad";
		Date fechaPublicacion = new Date();
		double salario = 9000.00;
		boolean vigente = true;
		
		modelo.addAttribute("nombre", nombre);
		modelo.addAttribute("fecha", fechaPublicacion);
		modelo.addAttribute("salario", salario);
		modelo.addAttribute("vigente", vigente);
		
		return "home";
	}
	
	private List<Vacante> devolverLista(){
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		List<Vacante> listaEmpleos = new LinkedList<>();
		
		
		try {
			Vacante vacante1 = new Vacante();
			vacante1.setId(1);
			vacante1.setNombre("Ingeniero de Informatica");
			vacante1.setDescripcion("Puesto encargado de llevar a cabo el diseno y arquitectura de software");
			vacante1.setFecha(formatoFecha.parse("10-11-2019"));
			vacante1.setSalario(75000.00);
			
			Vacante vacante2 = new Vacante();
			vacante2.setId(2);
			vacante2.setNombre("Ayudante Contable");
			vacante2.setDescripcion("Encargado de llevar los libros contables en apoyo a las areas contables");
			vacante2.setFecha(formatoFecha.parse("09-02-2017"));
			vacante2.setSalario(38300.25);
			
			Vacante vacante3 = new Vacante();
			vacante3.setId(2);
			vacante3.setNombre("Panadero");
			vacante3.setDescripcion("Se requiere Panadero de estilo frances para hacer pancitos");
			vacante3.setFecha(formatoFecha.parse("30-03-2017"));
			vacante3.setSalario(46465.00);
			
			listaEmpleos.add(vacante1);
			listaEmpleos.add(vacante2);
			listaEmpleos.add(vacante3);
			
		}
		catch(ParseException ex) {
			System.out.println("Error de Coversion de Formato de fecha: "+ex.getMessage());
		}
		
		
		
		
		return listaEmpleos;
	}

}
