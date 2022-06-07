package com.proyecto.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.modelo.AnuncioUsuarioVO;
import com.proyecto.modelo.AnuncioVO;

@Repository
public interface AnuncioUsuarioRepository extends JpaRepository<AnuncioUsuarioVO, Integer> {
	
	@Query(value="SELECT * FROM anunciosusuarios WHERE anunciosusuarios.anuncio_id_productos = :idAnuncio and anunciosusuarios.usuario_id_usuarios = :idUsuario", nativeQuery=true)
	Optional<AnuncioUsuarioVO> findByAnuncio(Integer idAnuncio, Integer idUsuario);

	
	@Query(value="SELECT * FROM anunciosusuarios WHERE anunciosusuarios.usuario_id_usuarios = :idUsuario", nativeQuery=true)
	List<AnuncioUsuarioVO> findByUsuario(Integer idUsuario);
}
