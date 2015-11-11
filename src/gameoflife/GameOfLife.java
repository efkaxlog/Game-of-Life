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
    int windowSize = 500;
    Cell[][] cells = populateCells(windowSize);
    
    
    Pane root = new Pane();
    Scene scene = new Scene(root, 
            windowSize + cellsNumber * cellPadding,
            windowSize + cellsNumber * cellPadding);
    
    
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
    public Cell[][] populateCells(int windowSize) {
        int xPos = 0 + cellPadding;
        int yPos = 0 + cellPadding;
        int size = calculateCellSize();
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
        System.out.println(windowSize);
        
        return windowSize / cellsNumber;
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
