package mova.laboratorio.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mova.laboratorio.modelo.Perfil;
import mova.laboratorio.modelo.Usuario;
import mova.laboratorio.service.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private IUsuarioService usuariosRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/index")
	public String inicio(Model modelo) {
		List<Usuario> usuarios = usuariosRepo.buscarTodos();
		modelo.addAttribute("usuarios", usuarios);
		return "usuarios/listUsuarios";
	}
	
	@PostMapping("/save")
	public String guardar(Usuario usuario,
			              BindingResult controlBind,
                          RedirectAttributes atributos) {
		
		Perfil perfil = new Perfil();
		String pwdPlano = usuario.getPassword();
		String pwdEncriptado = passwordEncoder.encode(pwdPlano);
		usuario.setPassword(pwdEncriptado);
		
		perfil.setId(3);
		if (controlBind.hasErrors()) {
			for (ObjectError error: controlBind.getAllErrors()) {
				System.out.println("Ocurrio un error de conversion: "+error.getDefaultMessage());
			}
			
			return "usuarios/formRegistro";
		}
		usuario.setFechaRegistro(new Date());
		usuario.agregarPerfil(perfil);
		usuario.setEstatus(1);
		usuariosRepo.guardarUsuario(usuario);
		atributos.addFlashAttribute("msg", "Usuario Guardado Exitosamente");
		return "redirect:/usuarios/index";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer idUsuario, Model modelo) {
		usuariosRepo.eliminarUsuario(idUsuario);
		return "redirect:/usuarios/index";
	}
}
