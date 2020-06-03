package mova.laboratorio.api.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mova.laboratorio.api.entity.Album;
import mova.laboratorio.api.repository.AlbumsRepository;
import mova.laboratorio.api.service.IAlbumsService;

@Service
public class AlbumsService implements IAlbumsService {
	
	@Autowired
	private AlbumsRepository repoAlbums;

	@Override
	public List<Album> buscarTodos() {
		return repoAlbums.findAll();
	}

	@Override
	public void guardar(Album album) {
		repoAlbums.save(album);
	}

	@Override
	public void eliminar(Integer idAlbum) {
		repoAlbums.deleteById(idAlbum);
	}

}
