package com.java.hibernate.envers.dto;

import java.math.BigDecimal;

public record ProductDto(Integer id, CategoryDto category, Integer quantity, BigDecimal unitPrice, Integer version) {
	public ProductDto(Integer id, Integer idCategory, String nameCategory, Integer versionCategory, Integer quantity, BigDecimal unitPrice, Integer version) {
		this(id, new CategoryDto(idCategory, nameCategory, versionCategory), quantity, unitPrice, version);
	}
}
