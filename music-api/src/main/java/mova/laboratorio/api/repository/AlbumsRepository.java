package mova.laboratorio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mova.laboratorio.api.entity.Album;

public interface AlbumsRepository extends JpaRepository<Album,Integer> {

}
