package com.solid.LiskovSubstitutionPrinciple.LSP_Violate_Sample;

public class Square extends Rectangle{

    @Override
    public void setHeight(double height) {
        //set the height and width at the same time
        super.setHeight(height);
        super.setWidth(height);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        super.setHeight(width);
    }
}
