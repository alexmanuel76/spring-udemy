package mova.laboratorio.controller;


import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mova.laboratorio.modelo.Solicitud;
import mova.laboratorio.modelo.Usuario;
import mova.laboratorio.modelo.Vacante;
import mova.laboratorio.service.ICategoriasService;
import mova.laboratorio.service.ISolicitudesService;
import mova.laboratorio.service.IUsuarioService;
import mova.laboratorio.service.IVacanteService;
import mova.laboratorio.util.Utileria;

@Controller
@RequestMapping("/solicitudes")
public class SolicitudesController {
	
	@Autowired
	private ICategoriasService servicioCategoria;
	
	@Autowired
	private ISolicitudesService servicioSolicitudes;
	
	@Autowired
	private IUsuarioService servicioUsuarios;
	
	@Autowired
	private IVacanteService servicioVacantes;
	
	/**
	 * EJERCICIO: Declarar esta propiedad en el archivo application.properties. El valor sera el directorio
	 * en donde se guardarán los archivos de los Curriculums Vitaes de los usuarios.
	 */
	@Value("${empleosapp.ruta.cv}")
	private String ruta;
		
    /**
	 * Metodo que muestra la lista de solicitudes sin paginacion
	 * Seguridad: Solo disponible para un usuarios con perfil ADMINISTRADOR/SUPERVISOR.
	 * @return
	 */
    @GetMapping("/index") 
	public String mostrarIndex(Model modelo) {
    	List<Solicitud> listadoSolicitudes= servicioSolicitudes.buscarTodas();
    	modelo.addAttribute("solicitudes", listadoSolicitudes);
		return "solicitudes/listSolicitudes";
		
	}
    
    /**
	 * Metodo que muestra la lista de solicitudes con paginacion
	 * Seguridad: Solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR.
	 * @return
	 */
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado() {
		
		// EJERCICIO
		return "solicitudes/listSolicitudes";
		
	}
    
	/**
	 * Método para renderizar el formulario para aplicar para una Vacante
	 * Seguridad: Solo disponible para un usuario con perfil USUARIO.
	 * @return
	 */
	@GetMapping("/create/{idVacante}/{username}")
	public String crear(@PathVariable("idVacante") Integer idVacante,
			            @PathVariable("username") String username,
			            Model modelo,
			            Solicitud solicitud) {
		Vacante vacante = servicioVacantes.buscarPorId(idVacante);
		Usuario usuario = servicioUsuarios.buscarPorUsername(username);
		solicitud.setVacante(vacante);
		solicitud.setUsuario(usuario);
		modelo.addAttribute("solicitud", solicitud);
		return "solicitudes/formSolicitud";
	}
	
	/**
	 * Método que guarda la solicitud enviada por el usuario en la base de datos
	 * Seguridad: Solo disponible para un usuario con perfil USUARIO.
	 * @return
	 */
	@PostMapping("/save")
	public String guardar(Solicitud solicitud,
			              BindingResult controlBind,
                          RedirectAttributes atributos,
                           @RequestParam("archivoCV") MultipartFile multiPart) {
		
		if (controlBind.hasErrors()) {
			for (ObjectError error: controlBind.getAllErrors()) {
				System.out.println("Ocurrio un error de conversion: "+error.getDefaultMessage());
			}
			
			return "solicitudes/formSolicitud";
		}
		
		if(!multiPart.isEmpty()) {
			String nombreArchivo = Utileria.guardarArchivo(multiPart, ruta);
			if(nombreArchivo != null) {
				solicitud.setArchivo(nombreArchivo);
			}
		}
		solicitud.setFecha(new Date());
		servicioSolicitudes.guardar(solicitud);
		atributos.addFlashAttribute("msg", "Registro Guardado");
		return "redirect:/solicitudes/index";	
		
	}
	
	/**
	 * Método para eliminar una solicitud
	 * Seguridad: Solo disponible para usuarios con perfil ADMINISTRADOR/SUPERVISOR. 
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") Integer idSolicitud,
			               RedirectAttributes atributos) {
		servicioSolicitudes.eliminar(idSolicitud);
		atributos.addFlashAttribute("msg","Registro Eliminado con Exito!");
		return "redirect:/solicitudes/index";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") Integer idSolicitud,Model modelo) {
		Solicitud solicitud = servicioSolicitudes.buscarPorId(idSolicitud);
		modelo.addAttribute("solicitud", solicitud);
		return "/solicitudes/formSolicitud";
	}
	
	@GetMapping("/cv/{id}")
	public void obtenerCV(@PathVariable("id") Integer idSolicitud,HttpServletResponse response) {
		Solicitud solicitud = servicioSolicitudes.buscarPorId(idSolicitud);
		try {
			
			DefaultResourceLoader loader = new DefaultResourceLoader();
			InputStream inputStream = loader.getResource(ruta+solicitud.getArchivo()).getInputStream();
			
			IOUtils.copy(inputStream, response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment; filename="+solicitud.getArchivo());
            response.flushBuffer();
		}
		catch(IOException ex) {
			System.out.println("problemas al cargar: "+ex.getMessage()+" \n por: "+ex.toString());
		}
	}
	
	@ModelAttribute
	public void setGenericos(Model modelo) {
		modelo.addAttribute("categorias", servicioCategoria.buscarTodas());
		modelo.addAttribute("usuarios", servicioUsuarios.buscarTodos());
	}
			
	/**
	 * Personalizamos el Data Binding para todas las propiedades de tipo Date
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
}
