package com.task1.bookstore.repository;

import com.task1.bookstore.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity,Long> {
}
