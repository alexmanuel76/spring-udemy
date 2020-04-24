package mova.laboratorio.service;

import java.util.List;

import mova.laboratorio.modelo.Vacante;

public interface IVacanteService {
	
	List<Vacante> buscarTodas();
	
	Vacante buscarPorId(Integer idVacante);

}
