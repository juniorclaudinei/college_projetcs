import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class implements a abstract CircleObject
 *
 * This was an educational project carried out by the Java course and the only goal here is to demonstrate my ability to code in Java.
 *
 * --> You are NOT ALLOWED (in any way) to copy or reproduce this code. <--
 *
 * @author Claudinei Junior
 */
public abstract class CircleObject {

    /**
     * Variables:
     * x (double): X-axis
     * y (double): Y-axis
     * radius (double): Radius
     * strokeCircleColor (Color): Stroke line color
     * circleColor (Color): Circle color
     * lineWidth (int): Line width
     */
    private double x, y, radius =25;
    private Color strokeCircleColor, circleColor;
    private int lineWidth = 3;

    /**
     * Constructor
     * @param x (double): X-axis
     * @param y (double): Y-axis
     * @param strokeCircleColor (Color): Stroke line color
     * @param circleColor (Color): Circle color
     */
    public CircleObject(double x, double y, Color strokeCircleColor, Color circleColor ) {
        this.x = x;
        this.y = y;
        this.strokeCircleColor = strokeCircleColor;
        this.circleColor = circleColor;
    }

    /**
     * Returns X value
     * @return x (double): X value
     */
    public double getX() {
        return x;
    }

    /**
     * Returns Y value
     * @return y (double): Y value
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the circle's line color
     * @return strokeCircleColor (Color): the circle's line color
     */
    public Color getStrokeCircleColor() {
        return strokeCircleColor;
    }

    /**
     * Returns the circle's color
     * @return circleColor (Color): the circle's color
     */
    public Color getCircleColor() {
        return circleColor;
    }

    /**
     * Returns the line width used in the circle
     * @return lineWidth (int): line width used in the circle
     */
    public int getLineWidth() {
        return lineWidth;
    }

    /**
     * Draw method. Draws the set of pieces
     * @param gc GraphicContext
     */
    public void draw(GraphicsContext gc) {
        //gc.setStroke(getStrokeCircleColor());
        //gc.setFill(getCircleColor());
        //gc.setLineWidth(getLineWidth());
        gc.fillOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
        gc.strokeOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
    }

    /**
     * ToString method
     * @return lineWidth
     */
    @Override
    public String toString() {
        return "CircleObject{" +
                //"x=" + x +
                //", y=" + y +
                //", strokeCircleColor=" + strokeCircleColor +
                //", circleColor=" + circleColor +
                ", lineWidth=" + lineWidth +
                '}';
    }
}
