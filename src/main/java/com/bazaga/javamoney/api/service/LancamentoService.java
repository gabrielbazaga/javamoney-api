package com.bazaga.javamoney.api.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bazaga.javamoney.api.model.Lancamento;
import com.bazaga.javamoney.api.model.Pessoa;
import com.bazaga.javamoney.api.repository.LancamentoRepository;
import com.bazaga.javamoney.api.repository.PessoaRepository;
import com.bazaga.javamoney.api.service.exception.PessoaInexistenOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	 private LancamentoRepository lancamentoRepository;
	
	public Lancamento salvar(@Valid Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElse(null);
		if(pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenOuInativaException();
		} 
		return lancamentoRepository.save(lancamento);
	}

}
