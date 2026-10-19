package com.java.hibernate.envers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.java.hibernate.envers.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, RevisionRepository<Category, Integer, Integer> {

}
