package com.java.envers.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.envers.dto.ProdutoDto;
import com.java.envers.service.ProdutoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/produtos")
@RequiredArgsConstructor
public class ProdutoController {

	private final ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<ProdutoDto>> listar() {
		return ResponseEntity.ok(this.produtoService.listar());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProdutoDto> buscar(@PathVariable Integer id) {
		return ResponseEntity.ok(this.produtoService.buscar(id));
	}

	@GetMapping(value = "/historico/{id}")
	public ResponseEntity<Map<Integer, ProdutoDto>> buscarHistorico(@PathVariable Integer id) {
		return ResponseEntity.ok(this.produtoService.buscarHistorico(id));
	}

	@PostMapping
	public ResponseEntity<ProdutoDto> criar(@RequestBody ProdutoDto produto) {
		return ResponseEntity.ok(this.produtoService.criar(produto));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable Integer id, @RequestBody ProdutoDto produto) {
		return ResponseEntity.ok(this.produtoService.atualizar(id, produto));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> remover(@PathVariable Integer id) {
		this.produtoService.remover(id);
		return ResponseEntity.ok().build();
	}
}
