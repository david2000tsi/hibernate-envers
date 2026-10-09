package com.java.hibernate.envers.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java.hibernate.envers.dto.ProductDto;
import com.java.hibernate.envers.model.Category;
import com.java.hibernate.envers.model.Product;
import com.java.hibernate.envers.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	private ProductDto map(Product p) {
		return new ProductDto(p.getId(), p.getCategory().getId(), p.getCategory().getName(), p.getQuantity(), p.getUnitPrice());
	}

	private Product map(ProductDto p) {
		return new Product(p.id(), new Category(p.category().id(), p.category().name(), null), p.quantity(), p.unitPrice());
	}

	public List<ProductDto> list() {
		return this.productRepository.findAll()
			.stream()
			.map(p -> this.map(p))
			.collect(Collectors.toList());
	}

	public ProductDto find(Integer id) {
		return this.productRepository.findById(id)
			.map(p -> this.map(p))
			.orElse(null);
	}

	public Map<Integer, ProductDto> findRevision(Integer id) {
		HashMap<Integer, ProductDto> result = new HashMap<>();
		this.productRepository.findRevisions(id).forEach(r -> {
			result.put(r.getRequiredRevisionNumber(), this.map(r.getEntity()));
		});
		return result;
	}

	public ProductDto create(ProductDto product) {
		Product p = this.map(product);
		p.setId(null);
		p = this.productRepository.save(p);
		return this.map(p);
	}

	public ProductDto update(Integer id, ProductDto product) {
		Product p = this.map(product);
		p.setId(id);
		p = this.productRepository.save(p);
		return this.map(p);
	}

	public void remove(Integer id) {
		this.productRepository.deleteById(id);
	}
}
