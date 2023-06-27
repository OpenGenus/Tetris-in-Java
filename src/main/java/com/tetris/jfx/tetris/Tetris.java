package com.tetris.jfx.tetris;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Tetris extends Application{

    public static  int MOVE = 25;      //size of move
    public static  int SIZE = 25;      //size of block
    public static  int XMAX = SIZE* 12;
    private static int YMAX = SIZE *24;      //height of the field/playground

    public static int[][] MESH = new int[XMAX / SIZE][YMAX / SIZE]; //array used as the field
    private static Pane group = new Pane();
    private static Form object ;
    public static int score = 0;

    private static  Scene scene = new Scene(group, XMAX +150, YMAX);

    public static int top = 0;
    public static boolean game = true;
    private static Form nextShape = Controller.generateShape();
    private static int linesNo = 0;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage org){
        for(int[] a: MESH){         //initializing entire array with 0
            Arrays.fill(a,0);
        }


    //divider
    Line line = new Line(XMAX,0, XMAX, YMAX);

    //score text
    Text scoretext = new Text("Score : ");
    scoretext.setY(50);
    scoretext.setX(XMAX +50);
    //line counter
    Text level = new Text("Lines : ");
    level.setX(XMAX +50);
    level.setY(70);
    group.getChildren().addAll(scoretext,level,line);

    Form a = nextShape;
    group.getChildren().addAll(a.a,a.b,a.c,a.d);
    moveOnKeyPress(a);
    object = a;
    nextShape = Controller.generateShape();
    org.setScene(scene);
    org.setTitle("T E T R I S");
    org.show();


    // TIMER
    Timer fall = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            Platform.runLater(() -> {
                if(object.a.getHeight() == 0 || object.b.getHeight() == 0 || object.c.getHeight() == 0 && object.d.getHeight()==0){
                    top++;
                }
                else
                    top = 0;
                if(top == 2){
                    //game over
                    Text over = new Text("Game over");
                    over.setFill(Color.RED);
                    over.setStyle("-fx-font: 70 arial;");
                    over.setY(250);
                    over.setX(10);
                    group.getChildren().add(over);
                    game = false;
                }
                if(top == 15 ){
                    System.exit(0);
                }

                if(game){
                    MoveDown(object);
                    scoretext.setText("Score : "+ score);
                    level.setText("Lines : "+ linesNo);
                }
            });
        }
    };
    fall.schedule(task,0,300);
    }
    // controls shape movement
    private void moveOnKeyPress(Form form){
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT -> Controller.moveRight(form);
                case DOWN -> {
                    MoveDown(form);
                    score++;
                }
                case LEFT -> Controller.moveLeft(form);
                case UP -> MoveTurn(form);
            }
        });
    }

    //This rotates the shapes
    // 4 rotations for each shape except o
    private void MoveTurn(Form form){
        int f = form.form;
        Rectangle a = form.a;
        Rectangle b = form.b;
        Rectangle c = form.c;
        Rectangle d = form.d;

        switch (form.getName()) {
            case "j" -> {
                if (f == 1 && cB(a, 1, -1) && cB(c, -1, -1) && cB(d, -2, -2)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeShape();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, -2, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeShape();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, 1, 1) && cB(d, 2, 2)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeShape();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 2, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeShape();
                }
            }
            case "l" -> {
                if (f == 1 && cB(a, 1, -1) && cB(c, 1, 1) && cB(b, 2, 2)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    MoveUp(form.b);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    form.changeShape();
                    break;
                }
                if (f == 2 && cB(a, -1, -1) && cB(b, 2, -2) && cB(c, 1, -1)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveRight(form.b);
                    MoveRight(form.b);
                    MoveDown(form.b);
                    MoveDown(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    form.changeShape();
                    break;
                }
                if (f == 3 && cB(a, -1, 1) && cB(c, -1, -1) && cB(b, -2, -2)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    MoveDown(form.b);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveLeft(form.b);
                    form.changeShape();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(b, -2, 2) && cB(c, -1, 1)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveLeft(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.b);
                    MoveUp(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    form.changeShape();
                }
            }
            case "o" -> {break;}

            case "s" -> {
                if (f == 1 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeShape();
                    break;
                }
                if (f == 2 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeShape();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(c, -1, 1) && cB(d, 0, 2)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveUp(form.d);
                    MoveUp(form.d);
                    form.changeShape();
                    break;
                }
                if (f == 4 && cB(a, 1, 1) && cB(c, 1, -1) && cB(d, 0, -2)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveDown(form.d);
                    MoveDown(form.d);
                    form.changeShape();
                }
            }
            case "t" -> {
                if (f == 1 && cB(a, 1, 1) && cB(d, -1, -1) && cB(c, -1, 1)) {
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    form.changeShape();
                    break;
                }
                if (f == 2 && cB(a, 1, -1) && cB(d, -1, 1) && cB(c, 1, 1)) {
                    MoveRight(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.d);
                    MoveUp(form.d);
                    MoveUp(form.c);
                    MoveRight(form.c);
                    form.changeShape();
                    break;
                }
                if (f == 3 && cB(a, -1, -1) && cB(d, 1, 1) && cB(c, 1, -1)) {
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    form.changeShape();
                    break;
                }
                if (f == 4 && cB(a, -1, 1) && cB(d, 1, -1) && cB(c, -1, -1)) {
                    MoveLeft(form.a);
                    MoveUp(form.a);
                    MoveRight(form.d);
                    MoveDown(form.d);
                    MoveDown(form.c);
                    MoveLeft(form.c);
                    form.changeShape();
                }
            }
            case "z" -> {
                if (f == 1 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeShape();
                    break;
                }
                if (f == 2 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeShape();
                    break;
                }
                if (f == 3 && cB(b, 1, 1) && cB(c, -1, 1) && cB(d, -2, 0)) {
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveLeft(form.c);
                    MoveUp(form.c);
                    MoveLeft(form.d);
                    MoveLeft(form.d);
                    form.changeShape();
                    break;
                }
                if (f == 4 && cB(b, -1, -1) && cB(c, 1, -1) && cB(d, 2, 0)) {
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveRight(form.c);
                    MoveDown(form.c);
                    MoveRight(form.d);
                    MoveRight(form.d);
                    form.changeShape();
                }
            }
            case "i" -> {
                if (f == 1 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    form.changeShape();
                    break;
                }
                if (f == 2 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.changeShape();
                    break;
                }
                if (f == 3 && cB(a, 2, 2) && cB(b, 1, 1) && cB(d, -1, -1)) {
                    MoveUp(form.a);
                    MoveUp(form.a);
                    MoveRight(form.a);
                    MoveRight(form.a);
                    MoveUp(form.b);
                    MoveRight(form.b);
                    MoveDown(form.d);
                    MoveLeft(form.d);
                    form.changeShape();
                    break;
                }
                if (f == 4 && cB(a, -2, -2) && cB(b, -1, -1) && cB(d, 1, 1)) {
                    MoveDown(form.a);
                    MoveDown(form.a);
                    MoveLeft(form.a);
                    MoveLeft(form.a);
                    MoveDown(form.b);
                    MoveLeft(form.b);
                    MoveUp(form.d);
                    MoveRight(form.d);
                    form.changeShape();
                }
            }
        }
    }

    //clears row if gets full
    private void RemoveRows(){
        ArrayList<Node> rects = new ArrayList<>();
        ArrayList<Integer> lines = new ArrayList<>();
        ArrayList<Node> newrects = new ArrayList<>();
        int full = 0;
        for(int i = 0; i< MESH[0].length ; i++){
            for (int[] mesh : MESH) {
                if (mesh[i] == 1)
                    full++;
            }
            if(full == MESH.length )
                lines.add(i);
            full = 0;
        }
        if(lines.size() > 0)
                do{
                    for(Node node: Tetris.group.getChildren()){
                        if(node instanceof Rectangle)
                            rects.add(node);
                    }
                    score+=10;
                    linesNo++;

                    for(Node node: rects){
                        Rectangle a = (Rectangle) node;
                        if(a.getY() == lines.get(0)* SIZE){
                            MESH[(int)a.getX()/ SIZE][(int)a.getY()/ SIZE] = 0;
                            Tetris.group.getChildren().remove(node);
                        }
                        else
                            newrects.add(node);
                    }
                    for(Node node:newrects) {
                        Rectangle a = (Rectangle) node;
                        if (a.getY() < lines.get(0) * SIZE) {
                            MESH[(int) a.getX() / SIZE][(int) a.getY() / SIZE] = 0;
                            a.setY(a.getY() + SIZE);
                        }
                    }
                        lines.remove(0);
                        rects.clear();
                        newrects.clear();
                        for(Node node: Tetris.group.getChildren()){
                            if(node instanceof Rectangle)
                                rects.add(node);
                        }
                        for(Node node: rects){
                            Rectangle a = (Rectangle)node;
                            try{
                                MESH[(int)a.getX()/ SIZE][(int)a.getY()/ SIZE]=1;
                        }catch(ArrayIndexOutOfBoundsException ignored){

                            }
                    }rects.clear();
                }while(lines.size() > 0);
    }

    //moves a block down
    private void MoveDown(Rectangle rect){
        if(rect.getY()+ MOVE < YMAX)
            rect.setY(rect.getY()+ MOVE);
    }

    //moves a block right
    private void MoveRight(Rectangle rect){
        if(rect.getX()+ MOVE <= XMAX - SIZE)
            rect.setX(rect.getX()+ MOVE);
    }

    //moves a block left
    private void MoveLeft(Rectangle rect){
        if(rect.getX()+ MOVE >= 0)
            rect.setX(rect.getX()- MOVE);
    }

    //moves a block up
    private void MoveUp(Rectangle rect){
        if(rect.getY()- MOVE > 0)
            rect.setY(rect.getY()- MOVE);
    }


    //moves the entire shape down
    private void MoveDown(Form form){
        if(form.a.getY() == YMAX - SIZE || form.b.getY() == YMAX - SIZE || form.c.getY() == YMAX - SIZE ||
                form.d.getY() == YMAX - SIZE || moveA(form) || moveB(form) || moveC(form) || moveD(form)) {
            MESH[(int)form.a.getX()/ SIZE][(int)form.a.getY()/ SIZE] = 1;
            MESH[(int)form.b.getX()/ SIZE][(int)form.b.getY()/ SIZE] = 1;
            MESH[(int)form.c.getX()/ SIZE][(int)form.c.getY()/ SIZE] = 1;
            MESH[(int)form.d.getX()/ SIZE][(int)form.d.getY()/ SIZE] = 1;
            RemoveRows();

            Form a = nextShape;
            nextShape = Controller.generateShape();
            object = a;
            group.getChildren().addAll(a.a,a.b,a.c,a.d);
            moveOnKeyPress(a);
        }

        if(form.a.getY()+ MOVE < YMAX && form.b.getY()+ MOVE < YMAX && form.c.getY()+ MOVE
        < YMAX && form.d.getY()+ MOVE < YMAX){
            int movea = MESH[(int)form.a.getX()/ SIZE][((int)form.a.getY()/ SIZE)+1];
            int moveb = MESH[(int)form.a.getX()/ SIZE][((int)form.a.getY()/ SIZE)+1];
            int movec = MESH[(int)form.a.getX()/ SIZE][((int)form.a.getY()/ SIZE)+1];
            int moved = MESH[(int)form.a.getX()/ SIZE][((int)form.a.getY()/ SIZE)+1];

            if(movea == 0 && movea == moveb && moveb == movec && movec == moved){
                form.a.setY(form.a.getY()+ MOVE);
                form.b.setY(form.b.getY()+ MOVE);
                form.c.setY(form.c.getY()+ MOVE);
                form.d.setY(form.d.getY()+ MOVE);
            }
        }
    }


    private boolean moveA(Form form){
        return (MESH[(int)form.a.getX()/ SIZE][((int)form.a.getY()/ SIZE)+1]==1);
    }

    private boolean moveB(Form form){
        return (MESH[(int)form.b.getX()/ SIZE][((int)form.b.getY()/ SIZE)+1]==1);
    }
    private boolean moveC(Form form){
        return (MESH[(int)form.c.getX()/ SIZE][((int)form.c.getY()/ SIZE)+1]==1);
    }
    private boolean moveD(Form form){
        return (MESH[(int)form.d.getX()/ SIZE][((int)form.d.getY()/ SIZE)+1]==1);
    }



    private boolean cB(Rectangle rect, int x, int y){
        boolean yb = false;
        boolean xb = false;
        if(x>=0)
            xb = rect.getX()+x* MOVE <= XMAX - SIZE;
        if(x<0)
            xb = rect.getX()+x* MOVE >= 0;
        if(y>=0)
            yb = rect.getY()-y* MOVE >0;
        if(y<0)
            yb = rect.getY()+y* MOVE < YMAX;
        return xb && yb && MESH[((int)rect.getX()/ SIZE) +x][((int)rect.getY()/ SIZE)-y]==0;
    }
}
