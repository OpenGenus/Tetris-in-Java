package com.tetris.jfx.tetris;

import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Controller {
    public static final int MOVE = Tetris.MOVE ;
    public static final int SIZE = Tetris.SIZE;
    public static  int XMAX = Tetris.XMAX;

    public static int[][] MESH = Tetris.MESH;

    public static void moveLeft(Form form) {
        boolean checkA = form.a.getX() - MOVE >= 0;
        boolean checkB = form.b.getX() - MOVE >= 0;
        boolean checkC = form.c.getX() - MOVE >= 0;
        boolean checkD = form.d.getX() - MOVE >= 0;
        if (checkA && checkB && checkC && checkD) {
            int movea = MESH[((int) form.a.getX() / SIZE) - 1][(int) form.a.getY() / SIZE];
            int moveb = MESH[((int) form.b.getX() / SIZE) - 1][(int) form.b.getY() / SIZE];
            int movec = MESH[((int) form.c.getX() / SIZE) - 1][(int) form.c.getY() / SIZE];
            int moved = MESH[((int) form.d.getX() / SIZE) - 1][(int) form.d.getY() / SIZE];
            if (movea == 0 && moveb == 0 && movec == 0 && moved == 0) {
                form.a.setX(form.a.getX() - MOVE);
                form.b.setX(form.b.getX() - MOVE);
                form.c.setX(form.c.getX() - MOVE);
                form.d.setX(form.d.getX() - MOVE);
            }

        }
    }

    public static void moveRight(Form form) {
        boolean checkA = form.a.getX() + MOVE <= XMAX - SIZE;
        boolean checkB = form.b.getX() + MOVE <= XMAX - SIZE;
        boolean checkC = form.c.getX() + MOVE <= XMAX - SIZE;
        boolean checkD = form.d.getX() + MOVE <= XMAX - SIZE;
        if (checkA && checkB && checkC && checkD) {
            int movea = MESH[((int) form.a.getX() / SIZE) + 1][(int) form.a.getY() / SIZE];
            int moveb = MESH[((int) form.b.getX() / SIZE) + 1][(int) form.b.getY() / SIZE];
            int movec = MESH[((int) form.c.getX() / SIZE) + 1][(int) form.c.getY() / SIZE];
            int moved = MESH[((int) form.d.getX() / SIZE) + 1][(int) form.d.getY() / SIZE];
            if (movea == 0 && moveb == 0 && movec == 0 && moved == 0) {
                form.a.setX(form.a.getX() + MOVE);
                form.b.setX(form.b.getX() + MOVE);
                form.c.setX(form.c.getX() + MOVE);
                form.d.setX(form.d.getX() + MOVE);
            }

        }
    }

    //factory method that returns instance of Form classes with 4 rect
    public static Form generateShape() {

        int block = (int) (Math.random() * 100);

        String name;
        Rectangle a = new Rectangle(SIZE - 1, SIZE - 1), b = new Rectangle(SIZE - 1, SIZE - 1), c = new Rectangle(SIZE - 1, SIZE - 1), d = new Rectangle(SIZE - 1, SIZE - 1);
        if (block < 15) {
            a.setX((double) XMAX /2-SIZE);
            b.setX((double) XMAX /2-SIZE);
            b.setY(SIZE);
            c.setX((double) XMAX /2);
            c.setY(SIZE);
            d.setX((double) XMAX /2+SIZE);
            d.setY(SIZE);
            name = "j";
        } else if (block < 30) {
            a.setX(((double) XMAX /2)+SIZE);
            b.setX(((double) XMAX /2)-SIZE);
            b.setY(SIZE);
            c.setX((double) XMAX /2);
            c.setY(SIZE);
            d.setX(((double) XMAX /2)+SIZE);
            d.setY(SIZE);
            name = "l";
        } else if (block < 45) {        //OK
            a.setX((double) XMAX / 2 - SIZE);
            b.setX((double) XMAX / 2);
            c.setX((double) XMAX / 2 - SIZE);
            c.setY(SIZE);
            d.setX((double) XMAX / 2);
            d.setY(SIZE);
            name = "o";
        } else if (block < 60) {        //OK
            a.setX((double) XMAX / 2 + SIZE);
            b.setX((double) XMAX / 2);
            c.setX((double) XMAX / 2);
            c.setY(SIZE);
            d.setX((double) XMAX / 2 - SIZE);
            d.setY(SIZE);
            name = "s";
        } else if (block < 75) {
            a.setX((double) XMAX / 2 - SIZE);
            b.setX((double) XMAX / 2);
            c.setX((double) XMAX / 2);
            c.setY(SIZE);
            d.setX((double) XMAX / 2 + SIZE);
            name = "t";
        } else if (block < 90) {        //OK
            a.setX((double) XMAX / 2 + SIZE);
            b.setX((double) XMAX / 2);
            c.setX((double) XMAX / 2 + SIZE);
            c.setY(SIZE);
            d.setX((double) XMAX / 2 + SIZE + SIZE);
            d.setY(SIZE);
            name="z";
        } else {
            a.setX(((double) XMAX / 2) - SIZE - SIZE);
            b.setX(((double) XMAX / 2) - SIZE);
            c.setX((double) XMAX / 2);
            d.setX(((double) XMAX / 2) + SIZE);
            name = "i";
        }
        return new Form(a, b, c, d, name);
    }
}
