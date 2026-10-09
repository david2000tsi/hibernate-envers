package com.java.hibernate.envers.dto;

import java.math.BigDecimal;

public record ProductDto(Integer id, CategoryDto category, Integer quantity, BigDecimal unitPrice) {
	public ProductDto(Integer id, Integer idCategory, String nameCategory, Integer quantity, BigDecimal unitPrice) {
		this(id, new CategoryDto(idCategory, nameCategory), quantity, unitPrice);
	}
}
