package com.proyecto.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.modelo.ComentarioVO;

@Repository
public interface ComentarioRepository extends JpaRepository<ComentarioVO, Integer> {
	
	List<ComentarioVO> findByAnuncioIdProductos(Integer idProductos);
		

}
