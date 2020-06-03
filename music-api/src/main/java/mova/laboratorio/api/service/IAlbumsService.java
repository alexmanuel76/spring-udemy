package mova.laboratorio.api.service;

import java.util.List;

import mova.laboratorio.api.entity.Album;

public interface IAlbumsService {
	
	List<Album> buscarTodos();
	
	void guardar(Album album);
	
	void eliminar(Integer idAlbum);

}
