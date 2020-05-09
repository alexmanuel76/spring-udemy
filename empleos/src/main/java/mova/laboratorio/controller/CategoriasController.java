package mova.laboratorio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@Autowired
	private LogAppController log;

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
	public String guardar(Categoria categoria,
			              BindingResult controlBind,
			              RedirectAttributes atributos) {
		if (controlBind.hasErrors()) {
			for (ObjectError error: controlBind.getAllErrors()) {
				log.Mensaje("Error para guardar" + error.getDefaultMessage(), "ERR");
			}
			
			return "categorias/formCategorias";
		}
		log.Mensaje(categoria.toString(), "WARN");
		categoriasService.guardarCategoria(categoria);
		atributos.addFlashAttribute("msg", "Categoria Guardada");
		return "redirect:/categorias/index";
	}
	
	@GetMapping("/delete/{id}")
	public String borrar(@PathVariable("id") Integer idCategoria, RedirectAttributes atributos) {
		categoriasService.eliminarCategoria(idCategoria);
		atributos.addFlashAttribute("msg", "Categoria eliminada con Exito");
		return "redirect:/categorias/index";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer idCategoria, Model modelo, RedirectAttributes atributos) {
		Categoria categoria = categoriasService.buscarPorId(idCategoria);
		modelo.addAttribute("categoria", categoria);
		return "categorias/formCategorias";
	}
}
