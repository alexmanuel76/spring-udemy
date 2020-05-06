package mova.laboratorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mova.laboratorio.modelo.Usuario;


public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
