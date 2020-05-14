package mova.laboratorio.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mova.laboratorio.modelo.Vacante;
import mova.laboratorio.repository.VacantesRepository;
import mova.laboratorio.service.IVacanteService;

@Service
@Primary
public class VacantesServiceJpa implements IVacanteService {
	@Autowired
	private VacantesRepository vacantesRepo;
	
	private int DESTACADO = 1;
	private String ESTADO = "Aprobada";

	@Override
	public List<Vacante> buscarTodas() {
		return vacantesRepo.findAll();
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		Optional<Vacante> temporal = vacantesRepo.findById(idVacante);
		if (temporal.isPresent()) {
			return temporal.get();
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		vacantesRepo.save(vacante);
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		return vacantesRepo.findByDestacadoAndEstatusOrderByIdDesc(DESTACADO, ESTADO);
	}

	@Override
	public void eliminarVacante(Integer idVacante) {
		vacantesRepo.deleteById(idVacante);
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		return vacantesRepo.findAll(example);
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable pagina) {
		return vacantesRepo.findAll(pagina);
	}

}
