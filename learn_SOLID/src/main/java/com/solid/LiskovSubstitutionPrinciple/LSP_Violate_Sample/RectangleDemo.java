package com.solid.LiskovSubstitutionPrinciple.LSP_Violate_Sample;

public class RectangleDemo {

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(20);
        rectangle.setWidth(10);
        resize(rectangle);
        printInfo(rectangle);

        System.out.println("=============");

        Square square = new Square();
        square.setHeight(20);
        resize(square);
        printInfo(square);

        System.out.println("DONE!");
    }

    public static void resize(Rectangle rectangle){
        //If width less than height,increase width
        while (rectangle.getWidth() <= rectangle.getHeight()){
            rectangle.setWidth(rectangle.getWidth()+1);
            //if square called the setWidth method, will set the width and height at the same time
            //so this while loop will become a infinite loop
        }
    }

    public static void printInfo(Rectangle rectangle){
        System.out.println("Width:" + rectangle.getWidth());
        System.out.println("Height:" + rectangle.getHeight());
    }

}
