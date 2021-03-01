package com.bazaga.javamoney.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
	
	public Lancamento salvar(Lancamento lancamento) {
		validarPessoa(lancamento);
		return lancamentoRepository.save(lancamento);
	}

	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscarLancamentoExistente(codigo);
		if (!lancamento.getPessoa().equals(lancamentoSalvo.getPessoa())) {
			validarPessoa(lancamento);
		}
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");
		return lancamentoRepository.save(lancamentoSalvo);
	}
	
	private void validarPessoa(Lancamento lancamento) {
		Optional <Pessoa> pessoa = null;
		if (lancamento.getPessoa().getCodigo() != null) {
			pessoa = pessoaRepository.findById(lancamento.getPessoa().getCodigo());
		}
		if(pessoa == null || pessoa.isEmpty() || pessoa.get().isInativo()) {
			throw new PessoaInexistenOuInativaException();
		}
		
	}
	
	private Lancamento buscarLancamentoExistente(Long codigo) {
		Optional<Lancamento> lancamentoSalvoOpt = lancamentoRepository.findById(codigo);
		return lancamentoSalvoOpt.orElseThrow(() -> new IllegalArgumentException());
	}

}
