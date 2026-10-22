package com.java.hibernate.envers.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java.hibernate.envers.dto.CategoryDto;
import com.java.hibernate.envers.dto.ProductDto;
import com.java.hibernate.envers.model.Category;
import com.java.hibernate.envers.model.Product;
import com.java.hibernate.envers.repository.CategoryRepository;
import com.java.hibernate.envers.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	private ProductDto map(Product p) {
		if(Objects.isNull(p)) {
			return null;
		}
		Category c = Objects.nonNull(p.getCategory()) ? p.getCategory() : new Category();
		return new ProductDto(p.getId(), c.getId(), c.getName(), c.getVersion(), p.getQuantity(), p.getUnitPrice(), p.getDiscount(), p.getVersion());
	}

	private Product map(ProductDto p) {
		if(Objects.isNull(p)) {
			return null;
		}
		CategoryDto c = Objects.nonNull(p.category()) ? p.category() : new CategoryDto();
		return new Product(p.id(), new Category(c.id(), c.name(), null, null), p.quantity(), p.unitPrice(), p.discount(), null);
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

	public Category getCategory(Integer id) {
		return this.categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Missing category..."));
	}

	public ProductDto create(ProductDto product) {
		Product p = this.map(product);
		p.setId(null);
		p.setCategory(this.getCategory(product.category().id()));
		p = this.productRepository.save(p);
		return this.map(p);
	}

	public ProductDto update(Integer id, ProductDto product) {
		Category c = this.getCategory(product.category().id());
		Product saved = this.productRepository.findById(id).map(p -> {
			p.setQuantity(product.quantity());
			p.setUnitPrice(product.unitPrice());
			p.setDiscount(product.discount());
			p.setCategory(c);
			return this.productRepository.save(p);
		}).orElseThrow(() -> new IllegalArgumentException("Missing product..."));
		return this.map(saved);
	}

	public void remove(Integer id) {
		this.productRepository.deleteById(id);
	}
}
