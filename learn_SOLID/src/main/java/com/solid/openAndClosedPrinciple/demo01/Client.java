package com.solid.openAndClosedPrinciple.demo01;

public class Client {
    public static void main(String[] args) {
        //create a UserInput object
        UserInput userInput = new UserInput();
        //Create a default theme
        AbstractTheme theme = new DefaultTheme();

        userInput.setTheme(theme);
        userInput.display();//output

        //if user create a new theme,no need to change the source code
        theme = new DarkTheme();

        userInput.setTheme(theme);
        userInput.display();//output

    }
}
