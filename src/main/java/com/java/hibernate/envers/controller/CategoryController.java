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

import com.java.hibernate.envers.dto.CategoryDto;
import com.java.hibernate.envers.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryDto>> list() {
		return ResponseEntity.ok(this.categoryService.list());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDto> find(@PathVariable Integer id) {
		return ResponseEntity.ok(this.categoryService.find(id));
	}

	@GetMapping(value = "/revision/{id}")
	public ResponseEntity<Map<Integer, CategoryDto>> findRevision(@PathVariable Integer id) {
		return ResponseEntity.ok(this.categoryService.findRevision(id));
	}

	@PostMapping
	public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto category) {
		return ResponseEntity.ok(this.categoryService.create(category));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoryDto> update(@PathVariable Integer id, @RequestBody CategoryDto category) {
		return ResponseEntity.ok(this.categoryService.update(id, category));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> remove(@PathVariable Integer id) {
		this.categoryService.remove(id);
		return ResponseEntity.ok().build();
	}
}
