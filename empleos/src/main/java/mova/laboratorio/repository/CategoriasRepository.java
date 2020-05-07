package mova.laboratorio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mova.laboratorio.modelo.Categoria;

//public interface CategoriasRepository extends CrudRepository  <Categoria,Integer> {
public interface CategoriasRepository extends JpaRepository  <Categoria,Integer> {
	
}
