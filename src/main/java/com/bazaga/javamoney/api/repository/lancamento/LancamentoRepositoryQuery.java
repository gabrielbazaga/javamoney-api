package com.bazaga.javamoney.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bazaga.javamoney.api.model.Lancamento;
import com.bazaga.javamoney.api.repository.filter.LancamentoFilter;
import com.bazaga.javamoney.api.repository.projection.ResumoLancamento;

//o nome da interface deve ser assim!! para o spring data jpa entender
public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);
}
