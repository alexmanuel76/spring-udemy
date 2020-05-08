package mova.laboratorio.service;

import java.util.List;

import mova.laboratorio.modelo.Usuario;

public interface IUsuarioService {
	
	void guardarUsuario(Usuario usuario);
	
	void eliminarUsuario(Integer idUsuario);
	
	List<Usuario> buscarTodos();

}
