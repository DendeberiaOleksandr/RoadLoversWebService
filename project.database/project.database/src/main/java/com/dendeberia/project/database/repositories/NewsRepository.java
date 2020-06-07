package com.dendeberia.project.database.repositories;

import com.dendeberia.project.database.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
