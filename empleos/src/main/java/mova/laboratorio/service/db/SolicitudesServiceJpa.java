package mova.laboratorio.service.db;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mova.laboratorio.modelo.Solicitud;
import mova.laboratorio.repository.SolicitudesRepository;
import mova.laboratorio.service.ISolicitudesService;

@Service
public class SolicitudesServiceJpa implements ISolicitudesService {
	
	@Autowired
	private SolicitudesRepository repoSolicitudes;

	@Override
	public void guardar(Solicitud solicitud) {
		repoSolicitudes.save(solicitud);
	}

	@Override
	public void eliminar(Integer idSolicitud) {
		repoSolicitudes.deleteById(idSolicitud);
	}

	@Override
	public List<Solicitud> buscarTodas() {
		return repoSolicitudes.findAll();
	}

	@Override
	public Solicitud buscarPorId(Integer idSolicitud) {
		Optional<Solicitud> opcional = repoSolicitudes.findById(idSolicitud);
		if (opcional.isPresent()) {
			return opcional.get();
		}
		else {
			return null;
		}
	}

	@Override
	public Page<Solicitud> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}	

}
