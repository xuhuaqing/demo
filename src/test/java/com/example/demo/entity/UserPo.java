package com.example.demo.entity;


import java.util.LinkedHashMap;

public class UserPo {

    LinkedHashMap attribute = new LinkedHashMap();

    public void setAttribute(LinkedHashMap attribute) {
        this.attribute = attribute;
    }

    public UserPo(String make, int numberOfSeats, CarType type) {
        this.make = make;
        this.numberOfSeats = numberOfSeats;
        this.type = type;
    }

    private String make;

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    private int numberOfSeats;
    private CarType type;

   public static enum CarType {
        SEDAN
    }
}
