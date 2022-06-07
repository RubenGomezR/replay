package com.proyecto.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.proyecto.modelo.ComentarioVO;

public interface ServicioComentario {
	
	<S extends ComentarioVO> S save(S entityy, Integer idProductos);
	
	List<ComentarioVO> getByIdAnuncio(Integer id);
	
	<S extends ComentarioVO> S save(S entity);

	<S extends ComentarioVO> Optional<S> findOne(Example<S> example);

	Page<ComentarioVO> findAll(Pageable pageable);

	List<ComentarioVO> findAll();

	List<ComentarioVO> findAll(Sort sort);

	List<ComentarioVO> findAllById(Iterable<Integer> ids);

	Optional<ComentarioVO> findById(Integer id);

	<S extends ComentarioVO> List<S> saveAll(Iterable<S> entities);

	void flush();

	<S extends ComentarioVO> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	<S extends ComentarioVO> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends ComentarioVO> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<ComentarioVO> entities);

	<S extends ComentarioVO> long count(Example<S> example);

	void deleteAllInBatch(Iterable<ComentarioVO> entities);

	<S extends ComentarioVO> boolean exists(Example<S> example);

	long count();

	void deleteById(Integer id);

	<S extends ComentarioVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void delete(ComentarioVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAllInBatch();

	ComentarioVO getOne(Integer id);

	void deleteAll(Iterable<? extends ComentarioVO> entities);

	void deleteAll();

	ComentarioVO getById(Integer id);

	<S extends ComentarioVO> List<S> findAll(Example<S> example);

	<S extends ComentarioVO> List<S> findAll(Example<S> example, Sort sort);
	
}