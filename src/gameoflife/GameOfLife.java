package gameoflife;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author xlog
 */
public class GameOfLife extends Application {
    
    
    
    
    int cellsNumber = 50;
    int cellPadding = 3;
    int windowSize = 500 + cellPadding;
    int cellSize = calculateCellSize();
    Cell[][] cells = populateCells();
    
    
    Pane root = new Pane();
    Scene scene = new Scene(root, 
            windowSize + cellsNumber * cellPadding,
            windowSize + cellsNumber * cellPadding);
    
    
    
    public Cell[][] getNewCellSet(Cell[][] currentCells) {
        Cell[][] newCells = new Cell[cellsNumber][cellsNumber];
        for(int y=0; y < cellsNumber; y++) {
            for (int x=0; x < cellsNumber; x++) {
                Cell c = currentCells[y][x];
                c.alive = getCellFate(c);
                newCells[y][x] = c;
            }
        }
        return newCells;
    }
    
    public Cell getCell(int y, int x) {
        try {
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
        Cell[] neighbours = new Cell[8]; // 8 number surrounding cells
        neighbours[0] = getCell(y-1, x-1); // top left
        neighbours[1] = getCell(y-1, x); //   top
        neighbours[2] = getCell(y-1, x-1); // top right
        neighbours[3] = getCell(y, x-1); //   left
        neighbours[4] = getCell(y, x+1); //   right
        neighbours[5] = getCell(y+1, x-1); // bottom left
        neighbours[6] = getCell(y+1, x); //   bottom
        neighbours[7] = getCell(y+1, x+1); // bottom right
        
        for (int i=0; i < 8; i++) {
            if (neighbours[i].alive) {
                neighsAlive++;
            }
        }
        
        if (neighsAlive == 2 || neighsAlive == 3) {
            return true;
        }
        return false;
    }
    
    public void drawCells(Cell[][] cells) {
        for (int y=0; y < cellsNumber; y++) {
            for (int x=0; x < cellsNumber; x++) {
                Cell c = cells[y][x];
                c.setStroke(c.getColor());
                root.getChildren().add(c);
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
        return (windowSize + (cellPadding * cellsNumber)) / cellsNumber;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(scene);
        stage.setTitle("Game of Life");
        drawCells(cells);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
    }

    
}
