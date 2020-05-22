package mova.laboratorio.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import mova.laboratorio.modelo.Usuario;
import mova.laboratorio.modelo.Vacante;
import mova.laboratorio.service.ICategoriasService;
import mova.laboratorio.service.IUsuarioService;
import mova.laboratorio.service.IVacanteService;

@Controller
public class HomeController {
	
	@Autowired
	private IVacanteService servicioVacante;
	
	@Autowired
	private ICategoriasService servicioCategoria;
	
	@Autowired
	private IUsuarioService servicioUsuario;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
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
	public String mostrarHome(Model modelo, HttpSession sesion) {
		return "home";
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Authentication auth, HttpSession sesion) {
		String usuarios = auth.getName();
		System.out.println(usuarios);
		
		if (sesion.getAttribute("usuario") == null) {
			Usuario usuario = servicioUsuario.buscarPorUsername(usuarios);
			usuario.setPassword(null);
			sesion.setAttribute("usuario", usuario);
			System.out.println(usuario);
		}
		return "redirect:/";
	}
	
	@GetMapping("/search")
	public String buscar(@ModelAttribute("search") Vacante vacante, Model modelo) {
		
		ExampleMatcher matcher = ExampleMatcher
				                 .matching()
				                 .withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		
		Example<Vacante> example = Example.of(vacante,matcher);
		List<Vacante> lista = servicioVacante.buscarByExample(example);
		modelo.addAttribute("vacantes", lista);
		return "home";	
	}
	
	/**
	 * Para encriptar texto y mostrar la cadena en el browser
	 * @param texto texto a encriptar
	 * @return PWD + cadena encriptada
	 */
	@GetMapping("/bcrypt/{texto}")
	@ResponseBody
	public String Encriptar(@PathVariable("texto") String texto) {
		return "PWD: "+passwordEncoder.encode(texto);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	//// Fragmento asociados a Usuarios
	
	@GetMapping("/singup")
	public String registrarse(Usuario usuario,Model modelo) {
		return "usuarios/formRegistro";
	}
	
	@GetMapping("/login")
	public String ingresar() {
		return "formLogin";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/login";
	}
	
	@ModelAttribute
	public void setGenericos(Model modelo) {
		Vacante vacanteSearch = new Vacante();
		vacanteSearch.reset();
		modelo.addAttribute("search", vacanteSearch);
		modelo.addAttribute("vacantes", servicioVacante.buscarDestacadas());
		modelo.addAttribute("categorias", servicioCategoria.buscarTodas());
	}
}
