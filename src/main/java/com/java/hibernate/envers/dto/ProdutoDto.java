package com.java.hibernate.envers.dto;

import java.math.BigDecimal;

public record ProdutoDto(Integer id, CategoriaoDto categoria, Integer quantidade, BigDecimal valorUnitario) {
	public ProdutoDto(Integer id, Integer idCategoria, String nomeCategoria, Integer quantidade, BigDecimal valorUnitario) {
		this(id, new CategoriaoDto(idCategoria, nomeCategoria), quantidade, valorUnitario);
	}
}
