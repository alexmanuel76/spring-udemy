package mova.laboratorio.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import mova.laboratorio.modelo.Usuario;
import mova.laboratorio.repository.UsuariosRepository;
import mova.laboratorio.service.IUsuarioService;

@Service
@Primary
public class UsuariosServiceJpa implements IUsuarioService {
	
	@Autowired
	private UsuariosRepository usuariosRepo;

	@Override
	public void guardarUsuario(Usuario usuario) {
		usuariosRepo.save(usuario);
	}

	@Override
	public void eliminarUsuario(Integer idUsuario) {
		usuariosRepo.deleteById(idUsuario);
	}

	@Override
	public List<Usuario> buscarTodos() {
		return usuariosRepo.findAll();
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		return usuariosRepo.findByUsername(username);
	}

}
