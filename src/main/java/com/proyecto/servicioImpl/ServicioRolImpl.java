package com.proyecto.servicioImpl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.proyecto.modelo.RolVO;
import com.proyecto.repositorio.RolRepository;
import com.proyecto.servicio.ServicioRol;

@Service
public class ServicioRolImpl implements ServicioRol {

	@Autowired
	private RolRepository rr;

	@Override
	public <S extends RolVO> S save(S entity) {
		return rr.save(entity);
	}

	@Override
	public <S extends RolVO> Optional<S> findOne(Example<S> example) {
		return rr.findOne(example);
	}

	@Override
	public Page<RolVO> findAll(Pageable pageable) {
		return rr.findAll(pageable);
	}

	@Override
	public List<RolVO> findAll() {
		return rr.findAll();
	}

	@Override
	public List<RolVO> findAll(Sort sort) {
		return rr.findAll(sort);
	}

	@Override
	public List<RolVO> findAllById(Iterable<Integer> ids) {
		return rr.findAllById(ids);
	}

	@Override
	public Optional<RolVO> findById(Integer id) {
		return rr.findById(id);
	}

	@Override
	public <S extends RolVO> List<S> saveAll(Iterable<S> entities) {
		return rr.saveAll(entities);
	}

	@Override
	public void flush() {
		rr.flush();
	}

	@Override
	public <S extends RolVO> S saveAndFlush(S entity) {
		return rr.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return rr.existsById(id);
	}

	@Override
	public <S extends RolVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return rr.saveAllAndFlush(entities);
	}

	@Override
	public <S extends RolVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return rr.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<RolVO> entities) {
		rr.deleteInBatch(entities);
	}

	@Override
	public <S extends RolVO> long count(Example<S> example) {
		return rr.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<RolVO> entities) {
		rr.deleteAllInBatch(entities);
	}

	@Override
	public <S extends RolVO> boolean exists(Example<S> example) {
		return rr.exists(example);
	}

	@Override
	public long count() {
		return rr.count();
	}

	@Override
	public void deleteById(Integer id) {
		rr.deleteById(id);
	}

	@Override
	public <S extends RolVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return rr.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		rr.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(RolVO entity) {
		rr.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		rr.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		rr.deleteAllInBatch();
	}

	@Override
	public RolVO getOne(Integer id) {
		return rr.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends RolVO> entities) {
		rr.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		rr.deleteAll();
	}

	@Override
	public RolVO getById(Integer id) {
		return rr.getById(id);
	}

	@Override
	public <S extends RolVO> List<S> findAll(Example<S> example) {
		return rr.findAll(example);
	}

	@Override
	public <S extends RolVO> List<S> findAll(Example<S> example, Sort sort) {
		return rr.findAll(example, sort);
	}
	
	
}
