package mova.laboratorio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mova.laboratorio.modelo.Categoria;
import mova.laboratorio.service.ICategoriasService;

@Controller
@RequestMapping("/categorias")
public class CategoriasController {

	@Autowired
	private ICategoriasService categoriasService;

	/**
	 * Metodo para listar las categorias
	 * @param modelo parametro usado para enviar el listado a la vista
	 * @return vista listCategorias con todas las categorias
	 */
	@GetMapping("/index")
	public String mostrarIndex(Model modelo) {
		List<Categoria> listadoCategorias = categoriasService.buscarTodas();
		modelo.addAttribute("categorias", listadoCategorias);
		return "categorias/listCategorias";
	}

	@GetMapping("/create")
	public String crear(Categoria categoria) {
		return "categorias/formCategorias";
	}

	@PostMapping("/save")
	public String guardar(Categoria categoria, BindingResult controlBind, RedirectAttributes atributos) {
		if (controlBind.hasErrors()) {
			for (ObjectError error: controlBind.getAllErrors()) {
				System.out.println("Ocurrio un error de conversion: "+error.getDefaultMessage());
			}
			
			return "categorias/formCategorias";
		}
		atributos.addFlashAttribute("msg", "Categoria Guardada");
		categoriasService.guardarCategoria(categoria);
		return "redirect:/categorias/index";
	}
}
