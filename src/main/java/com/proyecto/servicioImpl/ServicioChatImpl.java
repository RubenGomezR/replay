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

import com.proyecto.modelo.ChatVO;
import com.proyecto.repositorio.ChatRepository;
import com.proyecto.servicio.ServicioChat;

@Service
public class ServicioChatImpl implements ServicioChat {

	@Autowired
	private ChatRepository cr;

	@Override
	public <S extends ChatVO> S save(S entity) {
		return cr.save(entity);
	}

	@Override
	public <S extends ChatVO> Optional<S> findOne(Example<S> example) {
		return cr.findOne(example);
	}

	@Override
	public Page<ChatVO> findAll(Pageable pageable) {
		return cr.findAll(pageable);
	}

	@Override
	public List<ChatVO> findAll() {
		return cr.findAll();
	}

	@Override
	public List<ChatVO> findAll(Sort sort) {
		return cr.findAll(sort);
	}

	@Override
	public List<ChatVO> findAllById(Iterable<Integer> ids) {
		return cr.findAllById(ids);
	}

	@Override
	public Optional<ChatVO> findById(Integer id) {
		return cr.findById(id);
	}

	@Override
	public <S extends ChatVO> List<S> saveAll(Iterable<S> entities) {
		return cr.saveAll(entities);
	}

	@Override
	public void flush() {
		cr.flush();
	}

	@Override
	public <S extends ChatVO> S saveAndFlush(S entity) {
		return cr.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return cr.existsById(id);
	}

	@Override
	public <S extends ChatVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return cr.saveAllAndFlush(entities);
	}

	@Override
	public <S extends ChatVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return cr.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<ChatVO> entities) {
		cr.deleteInBatch(entities);
	}

	@Override
	public <S extends ChatVO> long count(Example<S> example) {
		return cr.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<ChatVO> entities) {
		cr.deleteAllInBatch(entities);
	}

	@Override
	public <S extends ChatVO> boolean exists(Example<S> example) {
		return cr.exists(example);
	}

	@Override
	public long count() {
		return cr.count();
	}

	@Override
	public void deleteById(Integer id) {
		cr.deleteById(id);
	}

	@Override
	public <S extends ChatVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return cr.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		cr.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(ChatVO entity) {
		cr.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		cr.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		cr.deleteAllInBatch();
	}

	@Override
	public ChatVO getOne(Integer id) {
		return cr.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends ChatVO> entities) {
		cr.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		cr.deleteAll();
	}

	@Override
	public ChatVO getById(Integer id) {
		return cr.getById(id);
	}

	@Override
	public <S extends ChatVO> List<S> findAll(Example<S> example) {
		return cr.findAll(example);
	}

	@Override
	public <S extends ChatVO> List<S> findAll(Example<S> example, Sort sort) {
		return cr.findAll(example, sort);
	}
	
	
}
