package mova.laboratorio.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import mova.laboratorio.modelo.Categoria;
import mova.laboratorio.repository.CategoriasRepository;
import mova.laboratorio.service.ICategoriasService;

@Service
@Primary
public class CategoriasServiceJpa implements ICategoriasService {

	@Autowired
	private CategoriasRepository categoriasRepo;
	
	@Override
	public void guardarCategoria(Categoria categoria) {
		categoriasRepo.save(categoria);
	}

	@Override
	public List<Categoria> buscarTodas() {
		return categoriasRepo.findAll();
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> opcional = categoriasRepo.findById(idCategoria);
		if(opcional.isPresent()) {
			return opcional.get();
		}
		else {
			return null;
		}
	}

}
