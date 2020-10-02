import javafx.scene.canvas.GraphicsContext;

/**
 * This class implements a table board game.
 *
 * This was an educational project carried out by the Java course and the only goal here is to demonstrate my ability to code in Java.
 *
 * --> You are NOT ALLOWED (in any way) to copy or reproduce this code. <--
 *
 * @author Claudinei Junior
 */
public class Table {
    /**
     * Variables:
     * boardLine (int): Sets the width of the columns
     * boardWidth (int): Sets the board's width
     * boardHeight (int): Sets the board's height
     */
    private int boardLine, boardWidth, boardHeight;

    /**
     * Constructor without parameters
     */
    public Table() {
        this(80,960, 720);
    }

    /**
     * Constructor
     * @param boardLine (int): Sets the width of the columns
     * @param boardWidth (int): Sets the board's width
     * @param boardHeight (int): Sets the board's height
     */
    public Table(int boardLine, int boardWidth, int boardHeight) {
        this.boardLine = 80;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    /**
     * Method that returns the board's width
     * @return (int): board's width
     */
    public int getBoardWidth() {
        return boardWidth;
    }

    /**
     * Method that returns the board's height
     * @return (int): board's height
     */
    public int getBoardHeight() {
        return boardHeight;
    }

    /**
     * Draw method. Draws the board table
     * @param gc GraphicContext
     */
    public void draw(GraphicsContext gc) {
        // Draw the lines
        for (int i = 0; i <= 8; ) {
            gc.strokeLine(boardLine, 80, boardLine, boardHeight);
            gc.strokeLine(80, boardLine, boardHeight, boardLine);
            boardLine += 80;
            i++;
        }

        // Draw the L and C label
        gc.strokeText("L", 40, 400);
        gc.strokeText("C", 400, 40);

        // Draw the L and C number labels
        int labelControl = 120;
        for (int x = 0; x <= 7; x++) {
            gc.strokeText(String.valueOf(x + 1), 60, labelControl);
            gc.strokeText(String.valueOf(x + 1), labelControl, 60);
            labelControl += 80;
        }
    }
}