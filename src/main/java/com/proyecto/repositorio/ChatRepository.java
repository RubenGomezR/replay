package com.proyecto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.modelo.ChatVO;

@Repository
public interface ChatRepository extends JpaRepository<ChatVO, Integer> {

}
