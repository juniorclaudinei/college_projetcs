import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * This class implements a new set of pieces (blue or red)
 *
 * This was an educational project carried out by the Java course and the only goal here is to demonstrate my ability to code in Java.
 *
 * --> You are NOT ALLOWED (in any way) to copy or reproduce this code. <--
 *
 * @author Claudinei Junior
 */
public class SetPieces {
    /**
     * Variables:
     * pieces (ArrayList): sets of board pieces
     * initialPiece (int): sets the X-axis to create the default set of board pieces
     * color (String): sets the set of board pieces color
     */
    private ArrayList<CircleObject> pieces = new ArrayList<>();
    private int initialPiece;
    private String color;
    private double distance, shortest;

    /**
     * Constructor
     * @param color (String): Set the board's piece color
     */
    public SetPieces(String color) {
        this.color = color;
        createSet(color);
    }

    /**
     * Method that returns the color of the board's piece
     * @return (String): the color of the piece on the set
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns the size of the set of board's piece
     * @return (int): the size of the array
     */
    public int getSize(){
        return pieces.size();
    }

    /**
     * Method that check if there is a piece in place
     * @param x (int): X-axis
     * @param y (int): Y-axis
     */
    public void checkPiece(int x, int y){
        for(CircleObject r: pieces){
            if(r.getX() == x && r.getY() == y){
                throw new IllegalArgumentException("You can't add a piece in this position. Try Again!!");
            }
        }
    }

    /**
     * Method that adds a piece in the board table
     * @param x (int): X-axis
     * @param y (int): Y-axis
     */
    public void addPiece(int x, int y){
        if(this.color.equalsIgnoreCase("blue")){
            pieces.add(new BlueCircle(x, y, Color.rgb(70,130,180), Color.rgb(100,149,237)));
        } else{
            pieces.add(new RedCircle(x, y, Color.rgb(178,34,34), Color.rgb(205,92,92)));
        }
    }

    /**
     * Method that removes a piece of the board table
     * @param x (int): X-axis
     * @param y (int): Y-axis
     */
    public void removePiece(int x, int y) {
        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i).getX() == x && pieces.get(i).getY() == y) {
                pieces.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("There is nothing here to remove. Try Again!!!");
    }

    public CircleObject closest(double xMouse, double yMouse){
        shortest = 500.0;
        CircleObject closest = pieces.get(0);

        for(CircleObject c: pieces){
            distance = Math.sqrt((xMouse - c.getX()) * (xMouse - c.getX()) + (yMouse - c.getY()) * (yMouse - c.getY()));
            if(distance < shortest){
                shortest = distance;
                closest = c;
            }
        }
        return closest;
    }

    /**
     * Method that create the set of pieces
     * @param color (String): The color of the pieces.
     */
    public void createSet(String color){
        /**
         * if statement to check the color and create the correct set of pieces
         */
        if(color.equalsIgnoreCase("blue")){
            //Sets the initial X-Coordinate
            int initialPiece = 200;
            /**
             * Creates the default set of pieces
             */
            for(int i = 0; i <= 2; i++){
                pieces.add(new BlueCircle(initialPiece,520, Color.rgb(70,130,180), Color.rgb(100,149,237)));
                initialPiece += 160;
            }

            //Sets the initial X-Coordinate
            initialPiece = 280;
            /**
             * Creates the default set of pieces
             */
            for(int x = 0; x <= 2; x++){
                pieces.add(new BlueCircle(initialPiece, 600, Color.rgb(70,130,180), Color.rgb(100,149,237)));
                initialPiece += 160;
            }
        } else {
            //Sets the initial X-Coordinate
            initialPiece = 200;
            /**
             * Creates the default set of pieces
             */
            for(int i = 0; i <= 2; i++){
                pieces.add(new RedCircle(initialPiece,200, Color.rgb(178,34,34), Color.rgb(205,92,92)));
                initialPiece += 160;
            }
            //Sets the initial X-Coordinate
            initialPiece = 280;
            /**
             * Creates the default set of pieces
             */
            for(int x = 0; x <= 2; x++){
                pieces.add(new RedCircle(initialPiece, 280, Color.rgb(178,34,34), Color.rgb(205,92,92)));
                initialPiece += 160;
            }
        }
    }

    /**
     * Draw method. Draws the set of pieces
     * @param gc GraphicContext
     */
    public void draw(GraphicsContext gc) {
        for(CircleObject r: pieces){
            r.draw(gc);
        }
    }
}
