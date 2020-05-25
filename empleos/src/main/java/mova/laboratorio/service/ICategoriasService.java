package mova.laboratorio.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mova.laboratorio.modelo.Categoria;

public interface ICategoriasService {

	void guardarCategoria(Categoria categoria);
	
	List<Categoria> buscarTodas();
	
	Categoria buscarPorId(Integer idCategoria);
	
	void eliminarCategoria(Integer idCategoria);
	
	Page<Categoria> buscarTodas(Pageable pagina);
}
