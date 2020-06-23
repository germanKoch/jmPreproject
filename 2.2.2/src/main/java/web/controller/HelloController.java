package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.Car;
import web.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping(value = "/cars")
    public String printCars(Model model, @RequestParam String locale) {
        CarService carService = CarService.getInstance();
        carService.addCar(new Car("Audi", "Germany", 12));
        carService.addCar(new Car("Lada", "Russia", 666));
        carService.addCar(new Car("BMW", "Germany", 14));
        if (locale.equals("en")) {
            model.addAttribute("title","CARS");
        } else if (locale.equals("ru")) {
            model.addAttribute("title", "МАШИНЫ");
        } else {
            model.addAttribute("title","____");
        }
        model.addAttribute("cars", carService.getAllCars());
        return "cars";
    }
}