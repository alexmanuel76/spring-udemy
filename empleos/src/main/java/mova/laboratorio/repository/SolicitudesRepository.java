package mova.laboratorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mova.laboratorio.modelo.Solicitud;

public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {

}
