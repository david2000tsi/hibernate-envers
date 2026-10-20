package com.java.hibernate.envers.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java.hibernate.envers.dto.CategoryDto;
import com.java.hibernate.envers.model.Category;
import com.java.hibernate.envers.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	private CategoryDto map(Category p) {
		return Objects.nonNull(p) ? new CategoryDto(p.getId(), p.getName(), p.getVersion()) : null;
	}

	private Category map(CategoryDto p) {
		return new Category(p.id(), p.name(), null, null);
	}

	public List<CategoryDto> list() {
		return this.categoryRepository.findAll()
			.stream()
			.map(p -> this.map(p))
			.collect(Collectors.toList());
	}

	public CategoryDto find(Integer id) {
		return this.categoryRepository.findById(id)
			.map(p -> this.map(p))
			.orElse(null);
	}

	public Map<Integer, CategoryDto> findRevision(Integer id) {
		HashMap<Integer, CategoryDto> result = new HashMap<>();
		this.categoryRepository.findRevisions(id).forEach(r -> {
			result.put(r.getRequiredRevisionNumber(), this.map(r.getEntity()));
		});
		return result;
	}

	public CategoryDto create(CategoryDto category) {
		Category p = this.map(category);
		p.setId(null);
		p = this.categoryRepository.save(p);
		return this.map(p);
	}

	public CategoryDto update(Integer id, CategoryDto category) {
		Category saved = this.categoryRepository.findById(id).map(c -> {
			c.setName(category.name());
			return this.categoryRepository.save(c);
		}).orElse(null);
		return this.map(saved);
	}

	public void remove(Integer id) {
		this.categoryRepository.deleteById(id);
	}
}
