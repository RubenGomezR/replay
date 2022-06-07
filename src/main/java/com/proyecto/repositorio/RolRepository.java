package com.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.modelo.RolVO;

@Repository
public interface RolRepository extends JpaRepository<RolVO, Integer> {

}
