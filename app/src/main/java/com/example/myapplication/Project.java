package com.example.myapplication;

public class Project {

    private String name;
    private double width;
    private double length;
    private double square;

    public Project(String name, double width, double length, double square) {
        this.name = name;
        this.width = width;
        this.length = length;
        this.square = square;
    }

    public String getName() {
        return name;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public double getSquare() {
        return square;
    }
}
