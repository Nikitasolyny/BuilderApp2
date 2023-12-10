package com.example.myapplication;

public class Project {

    private String name;
    private String type;
    private double length;
    private double width;
    private double height;
    private double square;
    private double d;
    private double c;
    private double s;
    private double materialCalculation;

    public Project(String name, String type, double length, double width, double height, double square, double d, double c, double s) {
        this.name = name;
        this.type = type;
        this.length = length;
        this.width = width;
        this.height = height;
        this.square = square;
        this.d = d;
        this.c = c;
        this.s = s;
        this.materialCalculation = calculateMaterialCalculation();
    }

    private double calculateMaterialCalculation() {
        materialCalculation = Math.ceil((square/1.5)*100)/100;
        return materialCalculation;
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getSquare() {
        return square;
    }

    public double getD() {
        return d;
    }

    public double getC() {
        return c;
    }

    public double getS() {
        return s;
    }
    public double getMaterialCalculation() {
        return materialCalculation;
    }
}
