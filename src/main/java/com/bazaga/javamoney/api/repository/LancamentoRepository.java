package com.bazaga.javamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bazaga.javamoney.api.model.Lancamento;
import com.bazaga.javamoney.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
