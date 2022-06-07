package com.proyecto.servicio;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.proyecto.modelo.AnuncioUsuarioVO;

public interface ServicioAnuncioUsuario {

	<S extends AnuncioUsuarioVO> S save(S entity);
	
	//añadir a like
	void saveLike(int id);
	
	<S extends AnuncioUsuarioVO> Optional<S> findOne(Example<S> example);

	Page<AnuncioUsuarioVO> findAll(Pageable pageable);

	List<AnuncioUsuarioVO> findAll();

	List<AnuncioUsuarioVO> findAll(Sort sort);

	List<AnuncioUsuarioVO> findAllById(Iterable<Integer> ids);

	Optional<AnuncioUsuarioVO> findById(Integer id);
	
	<S extends AnuncioUsuarioVO> List<S> saveAll(Iterable<S> entities);

	void flush();

	<S extends AnuncioUsuarioVO> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	<S extends AnuncioUsuarioVO> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends AnuncioUsuarioVO> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<AnuncioUsuarioVO> entities);

	<S extends AnuncioUsuarioVO> long count(Example<S> example);

	void deleteAllInBatch(Iterable<AnuncioUsuarioVO> entities);

	<S extends AnuncioUsuarioVO> boolean exists(Example<S> example);

	long count();

	void deleteById(Integer id);

	<S extends AnuncioUsuarioVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void delete(AnuncioUsuarioVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAllInBatch();

	AnuncioUsuarioVO getOne(Integer id);

	void deleteAll(Iterable<? extends AnuncioUsuarioVO> entities);

	void deleteAll();

	AnuncioUsuarioVO getById(Integer id);

	<S extends AnuncioUsuarioVO> List<S> findAll(Example<S> example);

	<S extends AnuncioUsuarioVO> List<S> findAll(Example<S> example, Sort sort);
		
	

}