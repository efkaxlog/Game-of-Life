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
    
    boolean alive = true;
    int xIndex, yIndex;
    
    public Cell(double x, double y, double w, double h, int xIndex, int yIndex) {
        this.setX(x);
        this.setY(y);
        this.setWidth(w);
        this.setHeight(h);
        this.xIndex = xIndex;
        this.yIndex = yIndex;
    }
    
    public Color getColor() {
        if (alive) {
            return Color.RED;
        }
        return Color.BLUE;
    }
    
    public void toggleAlive() {
        if (alive){
            alive = false;
            this.setStroke(Color.WHITE);
        } else {
            alive = true;
            this.setStroke(Color.BLACK);
        }
        
    }
    
    public void printInfo() {
        System.out.println("State: " + alive);
        System.out.println("X: "+this.getX());
        System.out.println("Y: "+this.getY());
    }
    
}
