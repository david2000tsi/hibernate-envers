package com.java.hibernate.envers.dto;

public record CategoryDto(Integer id, String name, Integer version) {
	public CategoryDto() {
		this(null, null, null);
	}
}
