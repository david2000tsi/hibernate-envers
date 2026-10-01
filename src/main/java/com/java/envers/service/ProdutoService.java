package com.java.envers.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java.envers.dto.ProdutoDto;
import com.java.envers.model.Categoria;
import com.java.envers.model.Produto;
import com.java.envers.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	public List<ProdutoDto> listar() {
		return this.produtoRepository.findAll()
			.stream()
			.map(p -> new ProdutoDto(p.getId(), p.getCategoria().getId(), p.getCategoria().getNome(), p.getQuantidade(), p.getValorUnitario()))
			.collect(Collectors.toList());
	}

	public ProdutoDto buscar(Integer id) {
		return this.produtoRepository.findById(id)
			.map(p -> new ProdutoDto(p.getId(), p.getCategoria().getId(), p.getCategoria().getNome(), p.getQuantidade(), p.getValorUnitario()))
			.orElse(null);
	}

	public ProdutoDto criar(ProdutoDto produto) {
		Produto p = new Produto();
		p.setCategoria(new Categoria(null, produto.categoria().nome(), null));
		p.setQuantidade(produto.quantidade());
		p.setValorUnitario(produto.valorUnitario());
		p = this.produtoRepository.save(p);
		return new ProdutoDto(p.getId(), p.getCategoria().getId(), p.getCategoria().getNome(), p.getQuantidade(), p.getValorUnitario());
	}

	public ProdutoDto atualizar(Integer id, ProdutoDto produto) {
		Produto p = new Produto();
		p.setId(id);
		p.setCategoria(new Categoria(produto.categoria().id(), produto.categoria().nome(), null));
		p.setQuantidade(produto.quantidade());
		p.setValorUnitario(produto.valorUnitario());
		p = this.produtoRepository.save(p);
		return new ProdutoDto(p.getId(), p.getCategoria().getId(), p.getCategoria().getNome(), p.getQuantidade(), p.getValorUnitario());
	}

	public void remover(Integer id) {
		this.produtoRepository.deleteById(id);
	}
}
