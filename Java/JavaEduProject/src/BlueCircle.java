import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
/**
 * This class creates a blue circle
 *
 * This was an educational project carried out by the Java course and the only goal here is to demonstrate my ability to code in Java.
 *
 * --> You are NOT ALLOWED (in any way) to copy or reproduce this code. <--
 *
 * @author Claudinei Junior
 */
public class BlueCircle extends CircleObject{
    private double x, y;
    private Color circleColor, strokeColor;
    /**
     * Constructor
     * @param x (double): X-axis
     * @param y (double): Y-axis
     * @param circleColor (Color): Circle color
     * @param strokeColor (Color): Circle's stroke color
     */
    public BlueCircle(double x, double y, Color circleColor, Color strokeColor) {
        super(x, y, strokeColor, circleColor);
        this.x = x;
        this.y = y;
        this.circleColor = circleColor;
        this.strokeColor = strokeColor;
    }

    /**
     * Draw method. Draws the set of pieces
     * @param gc GraphicContext
     */
    public void draw(GraphicsContext gc) {
        gc.setStroke(strokeColor);
        gc.setFill(circleColor);
        gc.setLineWidth(getLineWidth());
        super.draw(gc);
    }

    /**
     * ToString method
     * @return x, y, strokeCircleColor, circleColor
     */
    @Override
    public String toString() {
        return "RedCircle{" +
                "x=" + x +
                ", y=" + y +
                ", circleColor=" + circleColor +
                ", strokeColor=" + strokeColor +
                '}' + super.toString();
    }
}
