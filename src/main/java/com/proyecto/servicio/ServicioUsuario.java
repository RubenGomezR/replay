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

import com.proyecto.modelo.AnuncioVO;
import com.proyecto.modelo.UsuarioVO;

public interface ServicioUsuario {
	
	<S extends UsuarioVO> S save(S entity);

	<S extends UsuarioVO> Optional<S> findOne(Example<S> example);

	Page<UsuarioVO> findAll(Pageable pageable);

	List<UsuarioVO> findAll();

	List<UsuarioVO> findAll(Sort sort);

	List<UsuarioVO> findAllById(Iterable<Integer> ids);

	Optional<UsuarioVO> findById(Integer id);

	<S extends UsuarioVO> List<S> saveAll(Iterable<S> entities);

	void flush();

	<S extends UsuarioVO> S saveAndFlush(S entity);

	boolean existsById(Integer id);

	<S extends UsuarioVO> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends UsuarioVO> Page<S> findAll(Example<S> example, Pageable pageable);

	void deleteInBatch(Iterable<UsuarioVO> entities);

	<S extends UsuarioVO> long count(Example<S> example);

	void deleteAllInBatch(Iterable<UsuarioVO> entities);

	<S extends UsuarioVO> boolean exists(Example<S> example);

	long count();

	void deleteById(Integer id);

	<S extends UsuarioVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllByIdInBatch(Iterable<Integer> ids);

	void delete(UsuarioVO entity);

	void deleteAllById(Iterable<? extends Integer> ids);

	void deleteAllInBatch();

	UsuarioVO getOne(Integer id);

	void deleteAll(Iterable<? extends UsuarioVO> entities);

	void deleteAll();

	UsuarioVO getById(Integer id);

	<S extends UsuarioVO> List<S> findAll(Example<S> example);

	<S extends UsuarioVO> List<S> findAll(Example<S> example, Sort sort);
	
	List<UsuarioVO>findByFechaCreacionBetween(LocalDate fechaInicio, LocalDate fechaFinal);
	
	List<UsuarioVO> findByNombre (String nombre);
	
	boolean login(String nombre, String contrasena);
	
	void logout();

}