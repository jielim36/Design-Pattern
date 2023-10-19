package com.solid.openAndClosedPrinciple.demo01;

public class UserInput {

    private AbstractTheme theme;

    public void setTheme(AbstractTheme abstractTheme){
        this.theme = abstractTheme;
    }

    public void display(){
        theme.display();
    }
}
