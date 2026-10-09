package com.java.hibernate.envers.controller;

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

import com.java.hibernate.envers.dto.ProductDto;
import com.java.hibernate.envers.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDto>> list() {
		return ResponseEntity.ok(this.productService.list());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDto> find(@PathVariable Integer id) {
		return ResponseEntity.ok(this.productService.find(id));
	}

	@GetMapping(value = "/revision/{id}")
	public ResponseEntity<Map<Integer, ProductDto>> findRevision(@PathVariable Integer id) {
		return ResponseEntity.ok(this.productService.findRevision(id));
	}

	@PostMapping
	public ResponseEntity<ProductDto> create(@RequestBody ProductDto product) {
		return ResponseEntity.ok(this.productService.create(product));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDto> update(@PathVariable Integer id, @RequestBody ProductDto product) {
		return ResponseEntity.ok(this.productService.update(id, product));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> remove(@PathVariable Integer id) {
		this.productService.remove(id);
		return ResponseEntity.ok().build();
	}
}
