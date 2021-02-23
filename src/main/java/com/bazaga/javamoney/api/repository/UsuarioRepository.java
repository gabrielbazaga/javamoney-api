package com.bazaga.javamoney.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazaga.javamoney.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmail (String email); // se nao encontrar, nao precisa ficar verificando se é diferente de null
}
