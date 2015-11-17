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
    
    boolean alive;
    int xIndex, yIndex;
    Color aliveColor = Color.BLACK;
    Color deadColor = Color.WHITE;
    Color hoverBorderColor = Color.CHARTREUSE;
    
    public Cell(double x, double y, double w, double h, int xIndex, int yIndex) {
        setX(x);
        setY(y);
        setWidth(w);
        setHeight(h);
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        alive = Math.random() < 0.5;
    }
    
    /**
     * for Cells with out of bounds coordinates
     * for example if x == -1 or x > array.size
     * the Cell is assumed to be dead therefore
     * no extra alive neighbours are given for
     * a Cell for which alive neighbours are 
     * being counted up
     */
    public Cell() {
        alive = false;
    }
    
    /**
     * draw border around Cell
     */
    public void drawBorder() {
        setStroke(hoverBorderColor);
    }
    
    /**
     * deletes border around Cell
     */
    public void deleteBorder() {
        setStroke(null);
    }
    
    /**
     * 
     * @return defined colours for dead and alive state
     */
    public Color getColor() {
        if (alive) {
            return aliveColor;
        }
        return deadColor;
    }
    
    public void toggleAlive() {
        if (alive){
            makeDead();
        } else {
            makeAlive();
        }
    }
    
    public void makeAlive() {
        alive = true;
        setFill(aliveColor);
    }
    
    public void makeDead() {
        alive = false;
        setFill(deadColor);
    }
    
    /**
     * for debugging purposes
     */
    public void printInfo() {
        System.out.println("State: " + alive);
        System.out.println("Y: "+yIndex);
        System.out.println("X: "+xIndex);
    }
}
