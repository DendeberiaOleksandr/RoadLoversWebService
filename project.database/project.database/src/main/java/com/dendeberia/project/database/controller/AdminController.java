package com.dendeberia.project.database.controller;

import com.dendeberia.project.database.entity.Car;
import com.dendeberia.project.database.service.CarService;
import com.dendeberia.project.database.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    CarService carService;

    @GetMapping("/userEditor")
    public String getUsersList(Model model){
        model.addAttribute("usersList", userService.getUsers());
        return "html/userEditor";
    }

    @GetMapping("/admin")
    public String getAdminPage(){
        return "html/admin";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") Long id){
        if(userService.findUserById(id) == null){
            return "html/admin";
        }

        userService.deleteUserById(id);
        return "html/admin";
    }

    @GetMapping("/deleteCar")
    public String deleteCar(@RequestParam("id") Long id){
        if(carService.findCarById(id) == null){
            return "html/admin";
        }

        carService.deleteCarById(id);
        return "html/admin";
    }

    @GetMapping("/editCar")
    public String editCar(
            @RequestParam("id") Long id,
            Model model
    ){
        if(carService.getById(id) != null){
            model.addAttribute("carEditable", carService.getById(id));
        }

        return "html/editCar";
    }

    @GetMapping("/carsEditor")
    public String getCarsEditor(Model model){
        model.addAttribute("cars", carService.getAll());
        return "html/carsEditor";
    }

    @PostMapping("/addChanges")
    public String addChanges(Car car, @ModelAttribute("imagePath") String imgPath, Model model){
        String fullImagePath = "img\\cars\\"+imgPath;
        car.setImgPath(fullImagePath);
        carService.save(car);
        model.addAttribute("cars", carService.getAll());
        return "html/carsEditor";
    }

    @PostMapping("/addCar")
    public String addCarIntoDatabase(@RequestParam("make") String make, @RequestParam("model") String carModel, @RequestParam("type") String type, @RequestParam("imagePath") String imagePath, Model model){
        String fullImgPath = "img\\cars\\"+imagePath;
        System.out.println(fullImgPath);
        carService.save(new Car(make, carModel, type, fullImgPath));
        return "html/carsAdd";
    }

    @GetMapping("/addCar")
    public String addCar(){
        return "html/carsAdd";
    }
}
