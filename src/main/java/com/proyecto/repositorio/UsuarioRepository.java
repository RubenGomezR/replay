package com.proyecto.repositorio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.modelo.AnuncioVO;
import com.proyecto.modelo.UsuarioVO;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioVO, Integer> {

	//consulta cuantas cuentas fueron creadas entre dos fechas dadas
	List<UsuarioVO>findByFechaCreacionBetween(LocalDate fechaInicio, LocalDate fechaFinal);
		
	//consulta buscar por nombre
	List<UsuarioVO> findByNombre (String nombre);
	
	@Query(value="SELECT * FROM usuarios WHERE usuarios.nombre like %:q%", nativeQuery=true)
	List<UsuarioVO> findByNombreLike(@Param("q")String q);
	
	//consulta buscar por correo
	List<UsuarioVO> findByCorreo(String correo);
	
	
}
