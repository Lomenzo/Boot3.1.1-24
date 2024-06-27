package web.model;

import web.util.Colors;

public class Car {
    String mark;
    int model;
    Colors color;

    public Car() {
    }

    public Car(String mark, int model, Colors color) {
        this.mark = mark;
        this.model = model;
        this.color = color;
    }
}
