package com.proyecto.servicio;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.proyecto.modelo.RolVO;

public interface ServicioRol {

	<S extends RolVO> S save(S entity);

	<S extends RolVO> Optional<S> findOne(Example<S> example);

	Page<RolVO> findAll(Pageable pageable);

	List<RolVO> findAll();

	List<RolVO> findAll(Sort sort);

	List<RolVO> findAllById(Iterable<Integer> ids);

	Optional<RolVO> findById(Integer id);

	<S extends RolVO> List<S> saveAll(Iterable<S> entities);

	void flush();

	<S extends RolVO> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	<S extends RolVO> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends RolVO> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<RolVO> entities);

	<S extends RolVO> long count(Example<S> example);

	void deleteAllInBatch(Iterable<RolVO> entities);

	<S extends RolVO> boolean exists(Example<S> example);

	long count();

	void deleteById(Integer id);

	<S extends RolVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void delete(RolVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAllInBatch();

	RolVO getOne(Integer id);

	void deleteAll(Iterable<? extends RolVO> entities);

	void deleteAll();

	RolVO getById(Integer id);

	<S extends RolVO> List<S> findAll(Example<S> example);

	<S extends RolVO> List<S> findAll(Example<S> example, Sort sort);

}