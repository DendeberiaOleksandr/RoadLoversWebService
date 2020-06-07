package com.dendeberia.project.database.controller;

import com.dendeberia.project.database.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarsContentController {
    @Autowired
    private CarService carService;

    @GetMapping("/getCarsList")
    public String getCars(Model model){
        model.addAttribute("cars", carService.getAll());
        return "html/cars";
    }
}
