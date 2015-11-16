package gameoflife;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author xlog
 */
public class GameOfLife extends Application {
    
    int cellsNumber = 30;
    int cellPadding = 1;
    int windowSize = 500 + cellPadding;
    int cellSize = calculateCellSize();
    Cell[][] cells = populateCells();
    int totalSize = windowSize + cellsNumber * cellPadding;
    Pane root = new Pane();
    Scene scene = new Scene(root, totalSize, totalSize);
    
    EventHandler clickHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            Object obj = event.getSource();
            if (obj instanceof Cell) {
                root.getChildren().remove(obj);
                ((Cell) obj).toggleAlive();
                root.getChildren().add((Cell)obj);
            }
        }
    };
    
    EventHandler h = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getCode() == KeyCode.SPACE) {
                drawNewSet();
        }
    }};
    
    AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long now) {
            drawNewSet();
        }
    };
    
    public void clearScreen() {
        Rectangle r = new Rectangle();
        r.setX(0);
        r.setY(0);
        r.setWidth(totalSize);
        r.setHeight(totalSize);
        r.setStroke(Color.WHITE);
        root.getChildren().add(r);
    }
    
    
    public void drawNewSet() {
        Cell[][] newCells = getNewCellSet(cells);
        root.getChildren().clear();    
        drawCells(newCells);
        cells = newCells;
    }
    
    public Cell[][] getNewCellSet(Cell[][] currentCells) {
        Cell[][] newCells = new Cell[cellsNumber][cellsNumber];
        for(int y=0; y < cellsNumber; y++) {
            for (int x=0; x < cellsNumber; x++) {
                Cell c = currentCells[y][x];
                Cell newC = new Cell(c.getX(), c.getY(), cellSize, cellSize, c.xIndex, c.yIndex);
                newC.alive = getCellFate(c);
                
                newCells[y][x] = newC;
            }
        }
        return newCells;
    }
    
    public Cell getCell(int y, int x) {
        try {
            //System.out.println("ALIVE CELL Y: "+ y+ " X: " +x);
            return cells[y][x];
        } catch (IndexOutOfBoundsException e) {
            // returns Cell that is not alive
            // as the x and y are out of bounds
            return new Cell();
            
        }     
    }
    
    public boolean getCellFate(Cell c) {
        int x = c.xIndex;
        int y = c.yIndex;
        int neighsAlive = 0;
        Cell[] neighbours = new Cell[8]; //   8 number surrounding cells
        neighbours[0] = getCell(y-1, x-1); // top left
        neighbours[1] = getCell(y-1, x); //   top
        neighbours[2] = getCell(y-1, x+1); // top right
        neighbours[3] = getCell(y, x-1); //   left
        neighbours[4] = getCell(y, x+1); //   right
        neighbours[5] = getCell(y+1, x-1); // bottom left
        neighbours[6] = getCell(y+1, x); //   bottom
        neighbours[7] = getCell(y+1, x+1); // bottom right
        
        for (int i=0; i < 8; i++) {
            //System.out.println(neighbours[i].alive + " Y: "+neighbours[i].yIndex + " X: " + neighbours[i].xIndex);
            if (neighbours[i].alive) {
                neighsAlive+= 1;
            }  
        }
        //System.out.println("Current Cell: Y: " + c.yIndex + " X: " + c.xIndex+ " Alive: " +neighsAlive);
        //System.out.println("-----------------------------");
        
        if ((c.alive) && (neighsAlive == 2 || neighsAlive == 3)) {
            return true; // just right to live on
        } else if (c.alive && neighsAlive < 2) {
            return false; // underpopulation
        } else if (c.alive && neighsAlive > 3) {
            return false; // overpopulation
        } else if (!c.alive && neighsAlive == 3) {
            return true; // dead cell comes alive
        } else {
            return false;
        } 
    }
    
    public void drawCells(Cell[][] cells) {
        for (int y=0; y < cellsNumber; y++) {
            for (int x=0; x < cellsNumber; x++) {
                Cell c = cells[y][x];
                c.setStroke(c.getColor());
                c.addEventFilter(MouseEvent.MOUSE_PRESSED, clickHandler);
                root.getChildren().add(c);
//                if (c.alive) {
//                    
//                }
            }
        }
    }
    
    /**
     * populates cells[][] with ready to draw Cells (Rectangles)
     * @return 2d Cell array of Cells
     */
    public Cell[][] populateCells() {
        int xPos = 0 + cellPadding;
        int yPos = 0 + cellPadding;
        int size = cellSize;
        Cell[] row;
        Cell c;
        Cell[][] cellsArray = new Cell[cellsNumber][];
        for (int y=0; y < cellsNumber; y++) {
            row = new Cell[cellsNumber];
            for (int x=0; x < cellsNumber; x++) {
                c = new Cell(xPos, yPos, size, size, x, y);
                row[x] = c;
                xPos += size + cellPadding;
            }
            xPos = 0 + cellPadding;
            cellsArray[y] = row;
            yPos += size + cellPadding;
        }
        
        return cellsArray;
    }
    
    public int calculateCellSize() {
        return (windowSize + cellPadding) / cellsNumber;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(scene);
        stage.setTitle("Game of Life");
        cells[5][4].alive = true;
        cells[5][5].alive = true;
        cells[5][6].alive = true;
        drawCells(cells);
        scene.setOnKeyPressed(h);
        
        stage.show();
       // at.start();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
    }

    
}
