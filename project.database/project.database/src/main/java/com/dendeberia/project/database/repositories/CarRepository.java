package com.dendeberia.project.database.repositories;

import com.dendeberia.project.database.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
