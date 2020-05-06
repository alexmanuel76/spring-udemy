package mova.laboratorio.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mova.laboratorio.modelo.Vacante;
import mova.laboratorio.service.ICategoriasService;
import mova.laboratorio.service.IVacanteService;
import mova.laboratorio.util.Utileria;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {

	@Value("${empleosapp.ruta.imagenes}")
	private String rutaArchivo;
	
	@Autowired
	private IVacanteService servicioVacantes;
	
	@Autowired
	private ICategoriasService servicioCategoria;
	
	@GetMapping("/index")
	public String mostrarIndex(Model modelo) {
		List<Vacante> listadoVacantes = servicioVacantes.buscarTodas();
		modelo.addAttribute("vacantes", listadoVacantes);
		return "vacantes/listVacantes";
	}

	@GetMapping("/create")
	public String crear(Vacante vacante, Model modelo) {
		modelo.addAttribute("categorias", servicioCategoria.buscarTodas());
		return "vacantes/formVacante";
	}

	@PostMapping("/save")
	public String guardarVacante(Vacante vacante,
			                     BindingResult controlBind,
			                     RedirectAttributes atributos,
			                     @RequestParam("archivoImagen") MultipartFile multiPart) {
		
		if (controlBind.hasErrors()) {
			for (ObjectError error: controlBind.getAllErrors()) {
				System.out.println("Ocurrio un error de conversion: "+error.getDefaultMessage());
			}
			
			return "vacantes/formVacante";
		}
		
		if (!multiPart.isEmpty()) {
			String nombreImagen = Utileria.guardarArchivo(multiPart, rutaArchivo);
			if(nombreImagen != null) { //La imagen si se subio
				vacante.setImagen(nombreImagen);
			}
		}
		
		servicioVacantes.guardar(vacante);
		atributos.addFlashAttribute("msg", "Registro Guardado");
		System.out.println("Vacante: " + vacante.toString());
		return "redirect:/vacantes/index";
	}

	@GetMapping("/delete")
	public String eliminarVacante(@RequestParam("id") int idVacante, Model modelo) {
		System.out.println("Vacante a Eliminar: " + idVacante);
		modelo.addAttribute("idVacante", idVacante);
		return "vacantes/mensaje";
	}

	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idVacante, Model modelo) {
		Vacante vacante = servicioVacantes.buscarPorId(idVacante);
		System.out.println("Vacante:" + vacante.toString());
		modelo.addAttribute("vacante", vacante);

		return "detalle";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-YYYY");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(formatoFecha,false));
	}

}
