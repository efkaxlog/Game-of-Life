/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author xlog
 */
public class Cell extends Rectangle {
    
    boolean alive;// = Math.random() < 0.5;
    int xIndex, yIndex;
    Color aliveColor = Color.BLACK;
    Color deadColor = Color.WHITE;
    
    public Cell(double x, double y, double w, double h, int xIndex, int yIndex) {
        this.setX(x);
        this.setY(y);
        this.setWidth(w);
        this.setHeight(h);
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.alive = Math.random() < 0.5;
    }
    
    // for cell out of bounds
    public Cell() {
        
        this.alive = false;
    }

    public Color getColor() {
        if (alive) {
            return aliveColor;
        }
        return deadColor;
    }
    
    public void toggleAlive() {
        if (alive){
            alive = false;
            this.setStroke(deadColor);
        } else {
            alive = true;
            this.setStroke(aliveColor);
        }
    }
    
    public void makeAlive() {
        this.alive = true;
        this.setStroke(aliveColor);
    }
    
    public void makeDead() {
        this.alive = false;
        this.setStroke(deadColor);
    }
    
    public void printInfo() {
        System.out.println("State: " + alive);
        System.out.println("Y: "+yIndex);
        System.out.println("X: "+xIndex);
    }
    
}
