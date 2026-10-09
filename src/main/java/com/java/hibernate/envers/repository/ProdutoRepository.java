package com.java.hibernate.envers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.java.hibernate.envers.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>, RevisionRepository<Produto, Integer, Integer> {

}
