package web.model;

import web.util.Colors;

public class Car {
    String mark;
    int model;
    Colors color;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getModel() {
        return model;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public Car() {
    }

    public Car(String mark, int model, Colors color) {
        this.mark = mark;
        this.model = model;
        this.color = color;
    }
}
