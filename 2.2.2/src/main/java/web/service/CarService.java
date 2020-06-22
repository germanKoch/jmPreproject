package web.service;

import web.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private List<Car> data = new ArrayList<>();

    private static CarService carService;

    public static CarService getInstance() {
        if (carService == null) {
            return new CarService();
        }
        return carService;
    }

    public List<Car> getAllCars() {
        return data;
    }

    public void addCar(Car car) {
        data.add(car);
    }

}
