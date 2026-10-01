package com.java.envers.model;

import java.util.List;

import org.hibernate.annotations.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Audited
@Entity
@Table(name = "Categoria")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nome", nullable = false)
	private String nome;

	@OneToMany(mappedBy = "categoria")
	private List<Produto> produtos;
}
