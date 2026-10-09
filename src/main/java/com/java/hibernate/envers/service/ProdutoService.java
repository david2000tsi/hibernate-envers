package com.java.hibernate.envers.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

import com.java.hibernate.envers.dto.ProdutoDto;
import com.java.hibernate.envers.model.Categoria;
import com.java.hibernate.envers.model.Produto;
import com.java.hibernate.envers.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	private ProdutoDto map(Produto p) {
		return new ProdutoDto(p.getId(), p.getCategoria().getId(), p.getCategoria().getNome(), p.getQuantidade(), p.getValorUnitario());
	}

	public List<ProdutoDto> listar() {
		return this.produtoRepository.findAll()
			.stream()
			.map(p -> this.map(p))
			.collect(Collectors.toList());
	}

	public ProdutoDto buscar(Integer id) {
		return this.produtoRepository.findById(id)
			.map(p -> this.map(p))
			.orElse(null);
	}

	public Map<Integer, ProdutoDto> buscarHistorico(Integer id) {
		HashMap<Integer, ProdutoDto> result = new HashMap<>();
		this.produtoRepository.findRevisions(id).forEach(r -> {
			result.put(r.getRequiredRevisionNumber(), this.map(r.getEntity()));
		});
		return result;
	}

	public ProdutoDto criar(ProdutoDto produto) {
		Produto p = new Produto();
		p.setCategoria(new Categoria(null, produto.categoria().nome(), null));
		p.setQuantidade(produto.quantidade());
		p.setValorUnitario(produto.valorUnitario());
		p = this.produtoRepository.save(p);
		return this.map(p);
	}

	public ProdutoDto atualizar(Integer id, ProdutoDto produto) {
		Produto p = new Produto();
		p.setId(id);
		p.setCategoria(new Categoria(produto.categoria().id(), produto.categoria().nome(), null));
		p.setQuantidade(produto.quantidade());
		p.setValorUnitario(produto.valorUnitario());
		p = this.produtoRepository.save(p);
		return this.map(p);
	}

	public void remover(Integer id) {
		this.produtoRepository.deleteById(id);
	}
}
