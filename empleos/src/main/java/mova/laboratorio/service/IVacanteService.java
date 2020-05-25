package mova.laboratorio.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mova.laboratorio.modelo.Vacante;

public interface IVacanteService {
	
	List<Vacante> buscarTodas();
	
	Vacante buscarPorId(Integer idVacante);
	
	void guardar(Vacante vacante);
	
	List<Vacante> buscarDestacadas();
	
	void eliminarVacante(Integer idVacante);
	
	List<Vacante> buscarByExample(Example<Vacante> example);
	
	Page<Vacante> buscarTodas(Pageable pagina);

}
