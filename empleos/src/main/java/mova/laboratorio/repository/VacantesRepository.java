package mova.laboratorio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mova.laboratorio.modelo.Vacante;

public interface VacantesRepository extends JpaRepository<Vacante, Integer> {
	
	List<Vacante> findByEstatus(String estatus);
	
	List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado,String estatus);
	
	List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double minimo,double maximo);
	
	List<Vacante> findByEstatusIn(String[] estatus);
	
}
