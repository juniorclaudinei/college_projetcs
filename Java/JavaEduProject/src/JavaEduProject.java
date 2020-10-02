import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This app creates a board game.
 *
 * This was an educational project carried out by the Java course and the only goal here is to demonstrate my ability to code in Java.
 *
 * --> You are NOT ALLOWED (in any way) to copy or reproduce this code. <--
 *
 * @author Claudinei Junior
 */
public class JavaEduProject extends Application {

    // Instance Variables for View Components and Model
    /**
     * Sets a new board table game and two sets of pieces (blue and red)
     */
    private final Table board = new Table();
    private final SetPieces bluePieces = new SetPieces("blue");
    private final SetPieces redPieces = new SetPieces("red");

    private GraphicsContext gc, gc2, gc3;
    private TextField cTextField, lTextField;
    /**
     * CanvasBoard: This canvas holds all the basic stuff for the game, like the board and the labels
     * CanvasPieces: This canvas holds the pieces (blue and red)
     * canvasActions: This canvas holds the X and Y Axis of the mouse and is where the Mouse Event happens
     */

    // This variable holds the select object
    private CircleObject objSelect;

    // Private Event Handlers and Helper Methods
    /**
     * Method that set the game drawing the basic info and resetting the canvasActions and select piece
     */
    private void setGame(){
        gc2.clearRect(0,0, board.getBoardWidth(), board.getBoardHeight());
        bluePieces.draw(gc2);
        redPieces.draw(gc2);
        gc3.clearRect(0,0,720,720);
        objSelect = null;
    }

    /**
     * Method to check if the number passed to insert a new piece is out of range
     * @param range (int): the L and C axis of the board that the user wants to add a new piece
     * @return Returns true or false in case of the number (int) is out of range or not
     */
    private boolean checkRange(int range){
        return range < 1 || range > 8;
    }

    /**
     * Method to check if there is a piece where the user wants to add a new one.
     * @param x (int): X-Axis
     * @param y (int): Y-Axis
     */
    private void checkPieces(int x, int y){
        try{
            redPieces.checkPiece(x, y);
            bluePieces.checkPiece(x,y);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Method to check if the user made a mistake inserting anything difference than integer
     * @return Returns true or false in case of the value is not a integer
     */
    private boolean checkUserInputError() {
        String msg = "You must provide a number between 1 and 8 for C and/or L.";
        int xC, lC;

        try{
            xC = Integer.parseInt(cTextField.getText());
            lC = Integer.parseInt(lTextField.getText());

            if (checkRange(xC) || checkRange(lC)) {
                throw new IllegalArgumentException(msg);
            }
        }
        catch (NumberFormatException a){
            System.err.println(msg);
            new Alert(Alert.AlertType.ERROR, msg).showAndWait();
            resetTextField();
            return false;
        } catch (IllegalArgumentException a){
            System.err.println(a.getMessage());
            new Alert(Alert.AlertType.ERROR, msg).showAndWait();
            resetTextField();
            return false;
        }
        return true;
    }

    /**
     * Method to reset the previous information inserted in the text fields
     */
    private void resetTextField() {
        cTextField.setText("");
        lTextField.setText("");
    }

    /**
     * Method that handles the blue insert button event
     * @param e ActionEvent
     */
    private void insertHandlerBlue(ActionEvent e){
        // Check if the number is out of range
        if(checkUserInputError()){
            int xInit = 40+(Integer.parseInt(cTextField.getText())*80);
            int yInit = 40+(Integer.parseInt(lTextField.getText())*80);
            try{
                // Checks if there is any piece in the same place
                checkPieces(xInit, yInit);
                // Add a new piece in the game
                bluePieces.addPiece(xInit,yInit);
                setGame();
                resetTextField();
            } catch (IllegalArgumentException a){
                System.err.println(a.getMessage());
                new Alert(Alert.AlertType.ERROR, a.getMessage()).showAndWait();
                resetTextField();
            }
        }
    }

    /**
     * Method that handles the red insert button event
     * @param e ActionEvent
     */
    private void insertHandlerRed(ActionEvent e){
        // Check if the number is out of range
        if(checkUserInputError()){
            int xInit = 40+(Integer.parseInt(cTextField.getText())*80);
            int yInit = 40+(Integer.parseInt(lTextField.getText())*80);
            try{
                // Checks if there is any piece in the same place
                checkPieces(xInit, yInit);
                // Add a new piece in the game
                redPieces.addPiece(xInit,yInit);
                setGame();
                resetTextField();
            } catch (IllegalArgumentException a){
                System.err.println(a.getMessage());
                new Alert(Alert.AlertType.ERROR, a.getMessage()).showAndWait();
                resetTextField();
            }
        }
    }

    /**
     * Method that handles the remove button event
     * @param e ActionEvent
     */
    private void removeHandler(ActionEvent e){
        // Checks if the object is a red, blue circle, or wasn't selected at all
        if(objSelect instanceof RedCircle){
            try{
                // Remove piece
                redPieces.removePiece((int)objSelect.getX(),(int)objSelect.getY());
                setGame();
                resetTextField();
            } catch (IllegalArgumentException a){
                System.err.println(a.getMessage() + " No red pieces in this position.");
                new Alert(Alert.AlertType.ERROR, a.getMessage() + " No red pieces in this position.").showAndWait();
                resetTextField();
            }
        }
        else if (objSelect instanceof BlueCircle) {
            try {
                // Remove piece
                bluePieces.removePiece((int) objSelect.getX(), (int) objSelect.getY());
                setGame();
                resetTextField();
            } catch (IllegalArgumentException a) {
                System.err.println(a.getMessage() + " No blue pieces in this position.");
                new Alert(Alert.AlertType.ERROR, a.getMessage() + " No blue pieces in this position.").showAndWait();
                resetTextField();
            }
        }
        else{
            System.err.println("REMOVE ERROR!!! You must select a piece to remote from the game.");
            new Alert(Alert.AlertType.ERROR, "You must select a piece to remote from the game.").showAndWait();
            resetTextField();
        }
    }

    /**
     * Method that handles the click mouse event
     * @param me MouseEvent
     */
    private void clickHandler(MouseEvent me){
        /*
         * Creates two CircleObject with the nearest piece for each set of pieces
         */
        CircleObject nearestBlue, nearestRed = null;

        try{
            nearestBlue = bluePieces.closest(me.getX(), me.getY());
        } catch (IndexOutOfBoundsException e){
            nearestBlue = null;
        }

        try{
            nearestRed = redPieces.closest(me.getX(), me.getY());
        } catch (IndexOutOfBoundsException e){
            nearestRed = null;
        }

        /*
         * Calculates the distance between the nearest piece in each set of pieces compared with the mouse X and Y Axis
         */
        double blueDistance, redDistance;
        if (nearestBlue != null) {
            blueDistance = Math.sqrt(((nearestBlue.getX() - me.getX()) * (nearestBlue.getX() - me.getX())) + ((nearestBlue.getY() - me.getY()) * (nearestBlue.getY() - me.getY())));
        } else{
            blueDistance = 100000000.0;
        }

        if(nearestRed != null){
            redDistance = Math.sqrt(((nearestRed.getX() - me.getX()) * (nearestRed.getX() - me.getX())) + ((nearestRed.getY() - me.getY()) * (nearestRed.getY() - me.getY())));
        } else{
            redDistance = 100000000.0;
        }

        // Clear the canvas
        gc3.clearRect(0,0,720,720);
        // Sets the same LineWidth from the circle
        gc3.setLineWidth(3);

        /*
         * If statement to check which is the nearest (between the nearest blue and red) from the X and Y mouse axis
         */
        if(blueDistance < redDistance){
            gc3.setFill(Color.BLUE);
            gc3.setStroke(Color.DARKBLUE);
            gc3.fillOval(nearestBlue.getX()-25, nearestBlue.getY()-25, 50, 50);
            gc3.strokeOval(nearestBlue.getX()-25, nearestBlue.getY()-25, 50, 50 );
            // Sets the nearest blue object as the objSelect
            objSelect = nearestBlue;
        }
        else if (blueDistance > redDistance) {
            gc3.setFill(Color.RED);
            gc3.setStroke(Color.DARKRED);
            gc3.fillOval(nearestRed.getX()-25, nearestRed.getY()-25, 50, 50);
            gc3.strokeOval(nearestRed.getX()-25, nearestRed.getY()-25, 50, 50 );
            // Sets the nearest red object as the objSelect
            objSelect = nearestRed;
        }
        else{
            System.out.println("There is no piece on the board right now. That's OK.... don't worry");
        }
    }
    /**
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * @param stage The main stage
     * @throws Exception Error exceptions
     */
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, board.getBoardWidth(), board.getBoardHeight()); // set the size here
        stage.setTitle("Boring JavaFX Game Ever"); // set the window title here
        stage.setScene(scene);

        Canvas canvasBoard, canvasPieces, canvasActions;
        Button insertButtonBlue, insertButtonRed, removeButton;
        Label cLabel, lLabel, bluePieceLabel, redPieceLabel, removeInfo;

        // 1. Create the model
        // 2. Create the GUI components

        /*
         * Canvas configuration
         */
        canvasBoard = new Canvas(board.getBoardWidth(), board.getBoardHeight());
        canvasPieces = new Canvas(board.getBoardWidth(), board.getBoardHeight());
        canvasActions = new Canvas(720, 720); //Ainda sem uso

        gc = canvasBoard.getGraphicsContext2D();
        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0, 0, board.getBoardWidth(), board.getBoardHeight());

        gc2 = canvasPieces.getGraphicsContext2D();
        gc2.clearRect(0,0, board.getBoardWidth(), board.getBoardHeight());

        gc3 = canvasActions.getGraphicsContext2D();

        // MouseEventHandler
        canvasActions.addEventHandler(MouseEvent.MOUSE_CLICKED, this::clickHandler);

        // Buttons
        insertButtonBlue = new Button("Insert");
        insertButtonRed = new Button("Insert");
        removeButton = new Button("Remove");

        // Labels
        cLabel = new Label("C");
        lLabel = new Label("L");
        bluePieceLabel = new Label("Blue Pieces");
        redPieceLabel = new Label("Red Pieces");
        removeInfo = new Label("Select a piece to remove.");

        // TextFields
        cTextField = new TextField();
        lTextField = new TextField();


        // 3. Add components to the root
        root.getChildren().addAll(canvasBoard, canvasPieces, canvasActions, insertButtonBlue, insertButtonRed, cLabel, lLabel,cTextField, lTextField, removeButton, bluePieceLabel, redPieceLabel, removeInfo);

        // 4. Configure the components (colors, fonts, size, location)

        insertButtonBlue.relocate(840, 175);
        insertButtonBlue.setPrefWidth(65);
        insertButtonBlue.setPrefHeight(25);

        removeButton.relocate(750, 250);
        removeButton.setPrefWidth(65);
        removeButton.setPrefHeight(25);

        removeInfo.relocate(750,280);

        insertButtonRed.relocate(750, 175);
        insertButtonRed.setPrefWidth(65);
        insertButtonRed.setPrefHeight(25);

        cLabel.relocate(795, 85);
        lLabel.relocate(850, 85);

        gc.strokeRect(735,80, 185, 140);

        gc.strokeRect(835, 145, 75, 65);
        gc.setFill(Color.rgb(100,149,237));
        gc.fillRect(835, 145, 75, 65);
        bluePieceLabel.setStyle("-fx-text-fill: white");
        bluePieceLabel.relocate(840, 150);

        gc.strokeRect(745, 145, 75, 65);
        gc.setFill(Color.rgb(205,92,92));
        gc.fillRect(745, 145, 75, 65);
        redPieceLabel.setStyle("-fx-text-fill: white");
        redPieceLabel.relocate(750, 150);

        cTextField.relocate(780, 105);
        cTextField.setPrefWidth(40);
        cTextField.setPrefHeight(25);
        cTextField.setStyle("-fx-background-color: white; -fx-text-fill: black");

        lTextField.relocate(835, 105);
        lTextField.setPrefWidth(40);
        lTextField.setPrefHeight(25);
        lTextField.setStyle("-fx-background-color: white; -fx-text-fill: black");

        //5. Add Event Handlers and do final setup
        board.draw(gc);
        setGame();

        // Button Handlers
        insertButtonBlue.setOnAction(this::insertHandlerBlue);
        insertButtonRed.setOnAction(this::insertHandlerRed);
        removeButton.setOnAction(this::removeHandler);

        // 6. Show the stage
        stage.show();
    }
}