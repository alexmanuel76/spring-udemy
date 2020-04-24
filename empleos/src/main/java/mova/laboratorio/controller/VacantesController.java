package mova.laboratorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mova.laboratorio.modelo.Vacante;
import mova.laboratorio.service.IVacanteService;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	private IVacanteService servicioVacantes;
	
	@GetMapping("/delete")
	public String eliminarVacante(@RequestParam("id") int idVacante, Model modelo) {
		System.out.println("Vacante a Eliminar: "+idVacante);
		modelo.addAttribute("idVacante", idVacante);
		return "vacantes/mensaje";
	}
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model modelo) {
		Vacante vacante = servicioVacantes.buscarPorId(idVacante);
		System.out.println("Vacante:"+vacante.toString());
		modelo.addAttribute("vacante", vacante);
		
		return "detalle";
	}
	
}
