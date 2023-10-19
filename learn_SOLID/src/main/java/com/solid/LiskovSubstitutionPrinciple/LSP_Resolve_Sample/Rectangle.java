package com.solid.LiskovSubstitutionPrinciple.LSP_Resolve_Sample;

public class Rectangle implements Quadrilateral {

    private double height;
    private double width;

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getWidth() {
        return width;
    }
}
