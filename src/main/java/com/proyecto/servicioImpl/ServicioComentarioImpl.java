package com.proyecto.servicioImpl;

import java.time.LocalDate;
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

import com.proyecto.Session;
import com.proyecto.modelo.ComentarioVO;
import com.proyecto.repositorio.ComentarioRepository;
import com.proyecto.servicio.ServicioAnuncio;
import com.proyecto.servicio.ServicioComentario;
import com.proyecto.servicio.ServicioUsuario;

@Service
public class ServicioComentarioImpl implements ServicioComentario {

	@Autowired
	private ComentarioRepository cr;
	
	@Autowired
	private ServicioUsuario su;
	
	@Autowired
	private ServicioAnuncio sa;

	@Autowired
	private Session sesion;
	
	@Override
	public <S extends ComentarioVO> S save(S entity, Integer idProductos) {
		entity.setAnuncio(sa.findById(idProductos));
		entity.setUsuario(su.findById(sesion.getUserLoggedId()).get());
		entity.setFecha(LocalDate.now());
		return this.save(entity);
	}


	@Override
	public <S extends ComentarioVO> S save(S entity) {
		return cr.save(entity);
	}

	@Override
	public <S extends ComentarioVO> Optional<S> findOne(Example<S> example) {
		return cr.findOne(example);
	}

	@Override
	public Page<ComentarioVO> findAll(Pageable pageable) {
		return cr.findAll(pageable);
	}

	@Override
	public List<ComentarioVO> findAll() {
		return cr.findAll();
	}

	@Override
	public List<ComentarioVO> findAll(Sort sort) {
		return cr.findAll(sort);
	}

	@Override
	public List<ComentarioVO> findAllById(Iterable<Integer> ids) {
		return cr.findAllById(ids);
	}

	@Override
	public Optional<ComentarioVO> findById(Integer id) {
		return cr.findById(id);
	}

	@Override
	public <S extends ComentarioVO> List<S> saveAll(Iterable<S> entities) {
		return cr.saveAll(entities);
	}

	@Override
	public void flush() {
		cr.flush();
	}

	@Override
	public <S extends ComentarioVO> S saveAndFlush(S entity) {
		return cr.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return cr.existsById(id);
	}

	@Override
	public <S extends ComentarioVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return cr.saveAllAndFlush(entities);
	}

	@Override
	public <S extends ComentarioVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return cr.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<ComentarioVO> entities) {
		cr.deleteInBatch(entities);
	}

	@Override
	public <S extends ComentarioVO> long count(Example<S> example) {
		return cr.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<ComentarioVO> entities) {
		cr.deleteAllInBatch(entities);
	}

	@Override
	public <S extends ComentarioVO> boolean exists(Example<S> example) {
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
	public <S extends ComentarioVO, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return cr.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		cr.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(ComentarioVO entity) {
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
	public ComentarioVO getOne(Integer id) {
		return cr.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends ComentarioVO> entities) {
		cr.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		cr.deleteAll();
	}

	@Override
	public ComentarioVO getById(Integer id) {
		return cr.getById(id);
	}	
	
	@Override
	public List<ComentarioVO> getByIdAnuncio(Integer id) {
		return cr.findByAnuncioIdProductos(id);
	}

	@Override
	public <S extends ComentarioVO> List<S> findAll(Example<S> example) {
		return cr.findAll(example);
	}

	@Override
	public <S extends ComentarioVO> List<S> findAll(Example<S> example, Sort sort) {
		return cr.findAll(example, sort);
	}
	
}
