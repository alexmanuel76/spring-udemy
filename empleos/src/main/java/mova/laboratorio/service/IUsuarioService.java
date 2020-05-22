package mova.laboratorio.service;

import java.util.List;

import mova.laboratorio.modelo.Usuario;

public interface IUsuarioService {
	/**
	 * Metodo para guardar un Usuario en la BD.
	 * El proceso setea automaticamente la fecha del dia, estatus activo y el perfil tipo USUARIO en la APP
	 * @param usuario Objeto tipo Usuario
	 */
	void guardarUsuario(Usuario usuario);
	
	/**
	 * Rutina utilizada para eliminar un usuario
	 * @param idUsuario ID del usuario a Eliminar de tipo Integer
	 */
	void eliminarUsuario(Integer idUsuario);
	
	/**
	 * Obtiene todos los Usuarios registrados
	 * @return Listado tipo Usuario List<Usuario>
	 */
	List<Usuario> buscarTodos();
	
	/**
	 * Rutina que devolvera un usuario de la BD dado el username registrado
	 * @param username Login del usuario en la aplicacion registrado en la BD
	 * @return Objeto tipo Usuario
	 */
	Usuario buscarPorUsername(String username);

}
