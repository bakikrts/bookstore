package com.task1.bookstore.repository;

import com.task1.bookstore.entity.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<MusicEntity,Long> {
}
