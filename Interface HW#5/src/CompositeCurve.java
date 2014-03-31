import java.util.ArrayList;

public class CompositeCurve implements Curve {
	private ArrayList<Curve> lineSaver;
	
	public CompositeCurve() {
		lineSaver = new ArrayList<Curve>();
	}
	
	public void add(Curve c){
		lineSaver.add(c);
	}
	
	public void draw(SketchPad pad){
		for(Curve c : lineSaver)
			c.draw(pad);
	}
	
	public void translate(double tx, double ty) {
		for(Curve c : lineSaver)
			c.translate(tx, ty);
	}
	
	public void scale(double sx, double sy){
		for(Curve c : lineSaver)
			c.scale(sx, sy);
	}
	
	public void rotate(double degrees){
		for(Curve c : lineSaver)
			c.rotate(degrees);
	}
	
	public Curve copy(){
		CompositeCurve copy = new CompositeCurve();
		for(Curve c : lineSaver){
			copy.add(c.copy());
		}
		return copy;
	}
}