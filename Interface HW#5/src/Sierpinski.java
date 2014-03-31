
public class Sierpinski implements Fractal {
	
	public Sierpinski() {
		
	}
	
	public Curve step0() {
		CompositeCurve triangle = new CompositeCurve();
	    Line a = new Line(0, 0, 1, 0);
	    Line b = new Line(0, 0, .5, 1);
	    Line c = new Line(.5, 1, 1, 0);
	    triangle.add(a);
	    triangle.add(b);
	    triangle.add(c);
	    return triangle;
	}
	
	public Curve transform(Curve curve) {
		CompositeCurve allcurve = new CompositeCurve();
		curve.scale(.5, .5);
		Curve dup1 = curve.copy();
		Curve dup2 = curve.copy();
		dup1.translate(.5,0);
		dup2.translate(.25, .5);
		allcurve.add(curve);
		allcurve.add(dup1);
		allcurve.add(dup2);
		return allcurve;
	}
}
