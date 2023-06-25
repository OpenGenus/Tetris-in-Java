package com.tetris.jfx.tetris;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class Form {

     Rectangle a ;
     Rectangle b ;
     Rectangle c ;
     Rectangle d ;


    Color color;
    private  String name;
    public  int form = 1;
    public Form(Rectangle a,Rectangle b,Rectangle c,Rectangle d, String name) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.name = name;

        switch (name) {
            case "j" -> color = Color.BLACK;
            case "l" -> color = Color.BLUE;
            case "o" -> color = Color.GREEN;
            case "s" -> color = Color.RED;
            case "t" -> color = Color.YELLOW;
            case "z" -> color = Color.PINK;
            case "i" -> color = Color.BROWN;    //broken
        }
        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    }

    public String getName(){
        return name;
    }
    public void changeShape() {
        if (form != 4)
            form++;
        else
            form = 1;
    }
}
