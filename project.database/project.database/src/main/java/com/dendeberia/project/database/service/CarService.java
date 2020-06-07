package com.dendeberia.project.database.service;

import com.dendeberia.project.database.entity.Car;
import com.dendeberia.project.database.entity.User;
import com.dendeberia.project.database.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAll() {
        return carRepository.findAll();
    }

    public void save(Car car) {
        if (car != null) {
            carRepository.save(car);
        }
    }

    public Car findCarById(Long id) {
        Optional<Car> carFromDatabase = carRepository.findById(id);
        return carFromDatabase.orElse(new Car());
    }

    public boolean deleteCarById(Long id) {
        if (carRepository.findById(id).isPresent()) {
            carRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public void editById(Long id, Car car){
            Car carToEdit = carRepository.getOne(id);
            carToEdit.setModel(car.getModel());
            carToEdit.setMake(car.getMake());
            carToEdit.setType(car.getType());
            carToEdit.setImgPath(car.getImgPath());

        System.out.println(carToEdit.getModel());
    }

    public Car getById(Long id){
        if(carRepository.findById(id).isPresent()){
            return carRepository.getOne(id);
        }
        return null;
    }
}
