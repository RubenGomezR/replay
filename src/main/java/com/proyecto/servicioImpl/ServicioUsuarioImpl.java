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
import com.proyecto.enums.RolEnum;
import com.proyecto.modelo.AnuncioVO;
import com.proyecto.modelo.RolVO;
import com.proyecto.modelo.UsuarioVO;
import com.proyecto.repositorio.RolRepository;
import com.proyecto.repositorio.UsuarioRepository;
import com.proyecto.servicio.ServicioUsuario;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario {

	@Autowired
	private UsuarioRepository ur;
	
	@Autowired
	private RolRepository rr;
	
	@Autowired
	private Session session;

	
	
	
	@Override
	public <S extends UsuarioVO> S save(S entity) {
		//añadimos dia y rol registrado por defecto al registrarse una cuenta
		entity.setFechaCreacion(LocalDate.now());
		
		RolVO rol =  rr.getById(RolEnum.REGISTRADO.getRol());
		
		entity.setRol(rol);
		return ur.save(entity);
	}

	@Override
	public <S extends UsuarioVO> Optional<S> findOne(Example<S> example) {
		return ur.findOne(example);
	}

	@Override
	public Page<UsuarioVO> findAll(Pageable pageable) {
		return ur.findAll(pageable);
	}

	@Override
	public List<UsuarioVO> findAll() {
		return ur.findAll();
	}

	@Override
	public List<UsuarioVO> findAll(Sort sort) {
		return ur.findAll(sort);
	}

	@Override
	public List<UsuarioVO> findAllById(Iterable<Integer> ids) {
		return ur.findAllById(ids);
	}

	@Override
	public Optional<UsuarioVO> findById(Integer id) {
		return ur.findById(id);
	}

	@Override
	public <S extends UsuarioVO> List<S> saveAll(Iterable<S> entities) {
		return ur.saveAll(entities);
	}

	@Override
	public void flush() {
		ur.flush();
	}

	@Override
	public <S extends UsuarioVO> S saveAndFlush(S entity) {
		return ur.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(Integer id) {
		return ur.existsById(id);
	}

	@Override
	public <S extends UsuarioVO> List<S> saveAllAndFlush(Iterable<S> entities) {
		return ur.saveAllAndFlush(entities);
	}

	@Override
	public <S extends UsuarioVO> Page<S> findAll(Example<S> example, Pageable pageable) {
		return ur.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<UsuarioVO> entities) {
		ur.deleteInBatch(entities);
	}

	@Override
	public <S extends UsuarioVO> long count(Example<S> example) {
		return ur.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<UsuarioVO> entities) {
		ur.deleteAllInBatch(entities);
	}

	@Override
	public <S extends UsuarioVO> boolean exists(Example<S> example) {
		return ur.exists(example);
	}

	@Override
	public long count() {
		return ur.count();
	}

	@Override
	public void deleteById(Integer id) {
		ur.deleteById(id);
	}

	@Override
	public <S extends UsuarioVO, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return ur.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		ur.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(UsuarioVO entity) {
		ur.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		ur.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		ur.deleteAllInBatch();
	}

	@Override
	public UsuarioVO getOne(Integer id) {
		return ur.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends UsuarioVO> entities) {
		ur.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		ur.deleteAll();
	}

	@Override
	public UsuarioVO getById(Integer id) {
		return ur.getById(id);
	}

	@Override
	public <S extends UsuarioVO> List<S> findAll(Example<S> example) {
		return ur.findAll(example);
	}

	@Override
	public <S extends UsuarioVO> List<S> findAll(Example<S> example, Sort sort) {
		return ur.findAll(example, sort);
	}

	@Override
	public List<UsuarioVO> findByFechaCreacionBetween(LocalDate fechaInicio, LocalDate fechaFinal) {
		// TODO Auto-generated method stub
		return ur.findByFechaCreacionBetween(fechaInicio, fechaFinal);
	}

	@Override
	public List<UsuarioVO> findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return ur.findByNombreLike(nombre);
	}	
	
	
	public boolean login(String correo, String contrasena) {
		boolean isLogged = false;
		List<UsuarioVO> usuarios = ur.findByCorreo(correo);
		
		if (usuarios.size() == 1) {
			UsuarioVO usuario = usuarios.get(0);
			isLogged = usuario.getContraseña().equals(contrasena);
			
			if(isLogged) {
				session.add(Session.IS_LOGGED, "true");
				session.add(Session.ID_USER, usuario.getIdUsuarios());
				session.add(Session.USER, usuario.getIdUsuarios());
				session.add(Session.ID_ROL, usuario.getRol().getIdRoles());
				session.add(Session.TIPO_ROL, usuario.getRol().getTipo());
			}
			
		}
		
		return isLogged;
	}
	
	public void logout() {
		session.logout();
	}

	
}
