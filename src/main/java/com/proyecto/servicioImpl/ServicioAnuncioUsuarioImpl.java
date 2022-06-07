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

import com.proyecto.Session;
import com.proyecto.modelo.AnuncioUsuarioVO;
import com.proyecto.modelo.UsuarioVO;
import com.proyecto.repositorio.AnuncioUsuarioRepository;
import com.proyecto.servicio.ServicioAnuncioUsuario;
import com.proyecto.servicio.ServicioAnuncio;
import com.proyecto.servicio.ServicioUsuario;

@Service
public class ServicioAnuncioUsuarioImpl implements ServicioAnuncioUsuario  {
	
	@Autowired
	private AnuncioUsuarioRepository aur;

	@Autowired
	private ServicioAnuncio as;

	@Autowired
	private ServicioUsuario us;

	@Autowired
	private Session sesion;

	public <S extends AnuncioUsuarioVO> S save(S entity) {
		return aur.save(entity);
	}

	public void saveLike(int id) {
		//buscamos el like por el id del anuncio y el id del usuario
		Optional<AnuncioUsuarioVO> anuncioUsuarioVO = aur.findByAnuncio(id, sesion.getUserLoggedId());
		if (anuncioUsuarioVO.isPresent()) {
			delete(anuncioUsuarioVO.get());
		} else {
			AnuncioUsuarioVO auvo = new  AnuncioUsuarioVO();
			auvo.setAnuncio(as.getById(id));
			auvo.setUsuario(us.getById(sesion.getUserLoggedId()));
			aur.save(auvo);
		}
	}

	public <S extends AnuncioUsuarioVO> Optional<S> findOne(Example<S> example) {
		return aur.findOne(example);
	}

	public List<AnuncioUsuarioVO> findAll() {
		return aur.findAll();
	}

	public Page<AnuncioUsuarioVO> findAll(Pageable pageable) {
		return aur.findAll(pageable);
	}

	public List<AnuncioUsuarioVO> findAll(Sort sort) {
		return aur.findAll(sort);
	}

	public List<AnuncioUsuarioVO> findAllById(Iterable<Integer> ids) {
		return aur.findAllById(ids);
	}

	public Optional<AnuncioUsuarioVO> findById(Integer id) {
		return aur.findById(id);
	}

	public <S extends AnuncioUsuarioVO> List<S> saveAll(Iterable<S> entities) {
		return aur.saveAll(entities);
	}

	public void flush() {
		aur.flush();
	}

	public <S extends AnuncioUsuarioVO> S saveAndFlush(S entity) {
		return aur.saveAndFlush(entity);
	}

	public boolean existsById(Integer id) {
		return aur.existsById(id);
	}

	public <S extends AnuncioUsuarioVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return aur.saveAllAndFlush(entities);
	}

	public <S extends AnuncioUsuarioVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return aur.findAll(example, pageable);
	}

	public void deleteInBatch(Iterable<AnuncioUsuarioVO> entities) {
		aur.deleteInBatch(entities);
	}

	public <S extends AnuncioUsuarioVO> long count(Example<S> example) {
		return aur.count(example);
	}

	public void deleteAllInBatch(Iterable<AnuncioUsuarioVO> entities) {
		aur.deleteAllInBatch(entities);
	}

	public long count() {
		return aur.count();
	}

	public <S extends AnuncioUsuarioVO> boolean exists(Example<S> example) {
		return aur.exists(example);
	}

	public void deleteById(Integer id) {
		aur.deleteById(id);
	}

	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		aur.deleteAllByIdInBatch(ids);
	}

	public void delete(AnuncioUsuarioVO entity) {
		aur.delete(entity);
	}

	public <S extends AnuncioUsuarioVO, R> R findBy(Example<S> example,
			Function<FetchableFluentQuery<S>, R> queryFunction) {
		return aur.findBy(example, queryFunction);
	}

	public void deleteAllById(Iterable<? extends Integer> ids) {
		aur.deleteAllById(ids);
	}

	public void deleteAllInBatch() {
		aur.deleteAllInBatch();
	}

	public AnuncioUsuarioVO getOne(Integer id) {
		return aur.getOne(id);
	}

	public void deleteAll(Iterable<? extends AnuncioUsuarioVO> entities) {
		aur.deleteAll(entities);
	}

	public void deleteAll() {
		aur.deleteAll();
	}

	public AnuncioUsuarioVO getById(Integer id) {
		return aur.getById(id);
	}

	public <S extends AnuncioUsuarioVO> List<S> findAll(Example<S> example) {
		return aur.findAll(example);
	}

	public <S extends AnuncioUsuarioVO> List<S> findAll(Example<S> example, Sort sort) {
		return aur.findAll(example, sort);
	}


}
