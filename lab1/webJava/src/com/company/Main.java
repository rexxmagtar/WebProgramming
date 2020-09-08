package com.company;

import com.company.controller.Controller;
import com.company.view.FigureView;

/**
 * Example class to test MVC pattern implementation.
 */
public class Main {

    public static void main(String[] args) {
        // write your code here
        Controller controller = new Controller(new FigureView());
        controller.Start();
    }
}
