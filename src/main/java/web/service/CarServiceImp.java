package web.service;

import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImp implements CarService{

    List<Car> carServiceList;

    public CarServiceImp() {
    }

    public CarServiceImp(List<Car> carServiceList) {
        this.carServiceList = carServiceList;
    }
    @Override
    public List<Car> getCarList(int carCount){
        return carServiceList.stream().limit(carCount).toList();
    }

    public List<Car> getCarServiceList() {
        return carServiceList;
    }

    public void setCarServiceList(List<Car> carServiceList) {
        this.carServiceList = carServiceList;
    }
}
