package com.proyecto.servicio;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto.modelo.AnuncioVO;

public interface ServicioAnuncio {

	<S extends AnuncioVO> S save(S entity, MultipartFile file);

	<S extends AnuncioVO> Optional<S> findOne(Example<S> example);

	Page<AnuncioVO> findAll(Pageable pageable);

	List<AnuncioVO> findAll();
	
	 List<AnuncioVO> findByAnuncio(String nombre);

	List<AnuncioVO> findAll(Sort sort);

	List<AnuncioVO> findAllById(Iterable<Integer> ids);

	AnuncioVO findById(Integer id);
	
	List<AnuncioVO> findByUsuario();

	<S extends AnuncioVO> List<S> saveAll(Iterable<S> entities);

	void flush();

	<S extends AnuncioVO> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	<S extends AnuncioVO> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends AnuncioVO> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<AnuncioVO> entities);

	<S extends AnuncioVO> long count(Example<S> example);

	void deleteAllInBatch(Iterable<AnuncioVO> entities);

	<S extends AnuncioVO> boolean exists(Example<S> example);

	long count();

	void deleteById(Integer id);

	<S extends AnuncioVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void delete(AnuncioVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAllInBatch();

	AnuncioVO getOne(Integer id);

	void deleteAll(Iterable<? extends AnuncioVO> entities);

	void deleteAll();

	AnuncioVO getById(Integer id);

	<S extends AnuncioVO> List<S> findAll(Example<S> example);

	<S extends AnuncioVO> List<S> findAll(Example<S> example, Sort sort);
	
	List<AnuncioVO> findByNombre (String nombre);
	
	List<AnuncioVO> findByCategoria (String nombre);
		
	List<AnuncioVO> findAllAnunciosAjenos();
	
	List<AnuncioVO> findAllLikesR();
	
	List<AnuncioVO> findByIdUsuario(Integer idUsuario);

}