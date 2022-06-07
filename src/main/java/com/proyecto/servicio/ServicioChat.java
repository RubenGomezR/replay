package com.proyecto.servicio;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.proyecto.modelo.ChatVO;

public interface ServicioChat {

	<S extends ChatVO> S save(S entity);

	<S extends ChatVO> Optional<S> findOne(Example<S> example);

	Page<ChatVO> findAll(Pageable pageable);

	List<ChatVO> findAll();

	List<ChatVO> findAll(Sort sort);

	List<ChatVO> findAllById(Iterable<Integer> ids);

	Optional<ChatVO> findById(Integer id);

	<S extends ChatVO> List<S> saveAll(Iterable<S> entities);

	void flush();

	<S extends ChatVO> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	<S extends ChatVO> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends ChatVO> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<ChatVO> entities);

	<S extends ChatVO> long count(Example<S> example);

	void deleteAllInBatch(Iterable<ChatVO> entities);

	<S extends ChatVO> boolean exists(Example<S> example);

	long count();

	void deleteById(Integer id);

	<S extends ChatVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void delete(ChatVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAllInBatch();

	ChatVO getOne(Integer id);

	void deleteAll(Iterable<? extends ChatVO> entities);

	void deleteAll();

	ChatVO getById(Integer id);

	<S extends ChatVO> List<S> findAll(Example<S> example);

	<S extends ChatVO> List<S> findAll(Example<S> example, Sort sort);

}