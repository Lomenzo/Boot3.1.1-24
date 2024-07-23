package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Car;
import web.service.CarService;
import web.service.CarServiceImp;
import web.util.Colors;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    //cars?count=3
    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    public String getBarBySimplePathWithRequestParam(@RequestParam(value = "count", required = false) Integer count, ModelMap cars) {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Honda", 883, Colors.red));
        carList.add(new Car("Mercedes", 600, Colors.grey));
        carList.add(new Car("Hyundai", 300, Colors.black));
        carList.add(new Car("KIA", 625, Colors.white));
        carList.add(new Car("Toyota", 100, Colors.blue));
        CarService carService = new CarServiceImp(carList);
        if (count != null) {
                cars.addAttribute("cars", carService.getCarList(count));
            } else {
                cars.addAttribute("cars", carList);
        }
        return "car";
    }
}
