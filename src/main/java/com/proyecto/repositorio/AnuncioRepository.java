package com.proyecto.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.modelo.AnuncioVO;

@Repository
public interface AnuncioRepository extends JpaRepository<AnuncioVO, Integer> {

	//consulta buscar por nombre
	List<AnuncioVO> findByNombre (String nombre);
	
	//consulta buscar por nombre categoria
	List<AnuncioVO> findByCategoria (String nombre);
	
	//consulta buscar por nombre
	@Query(value="SELECT * FROM anuncios WHERE anuncios.nombre like %:q%", nativeQuery=true)
	List<AnuncioVO> findByAnuncio (@Param("q")String q);
	
	//consulta buscar anuncio por id de usuario
	@Query(value="SELECT * FROM anuncios WHERE anuncios.usuario_id_usuarios = :idUsuario", nativeQuery=true)
	List<AnuncioVO> findByUsuario(Integer idUsuario);
	
	//consulta buscar anuncio que no pertenezca al usuario con id usuario
	@Query(value="SELECT * FROM anuncios WHERE anuncios.usuario_id_usuarios != :idUsuario", nativeQuery=true)
	List<AnuncioVO> findAllAnunciosAjenos(Integer idUsuario);
	
	@Query(value="select * from anuncios where id_productos in (SELECT anuncio_id_productos FROM anunciosusuarios WHERE anunciosusuarios.usuario_id_usuarios = :idUsuario)", nativeQuery=true)
	List<AnuncioVO> findAllLikesR(Integer idUsuario);
	
}
