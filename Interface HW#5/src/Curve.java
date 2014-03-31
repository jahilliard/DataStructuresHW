/**
 * 
 * @author Justin Hilliard <jhilliar>
 * @section A
 */

public interface Curve {
	public void draw(SketchPad pad);
	public void translate(double tx, double ty);
	public void scale(double sx, double sy); 
	public void rotate(double degrees);
	public Curve copy();
}