
public class Koch implements Fractal {
	
	public Curve step0(){
		Line l = new Line (0,.5,1,.5);
		return l;
	}
	
	public Curve transform(Curve curve){
		CompositeCurve allcurve = new CompositeCurve();
		curve.translate(0, -.5);
		curve.scale(1.0/3.0, 1.0/3.0);
		Curve dup1 = curve.copy();
		Curve dup2 = curve.copy();
		Curve dup3 = curve.copy();
		dup1.rotate(60);
		dup1.translate(1.0/3.0, 0);
		dup2.translate(2.0/3.0,0);
		dup3.rotate(-60);
		dup3.translate(.5, Math.sqrt(3.0/36.0));
		allcurve.add(curve);
		allcurve.add(dup1);
		allcurve.add(dup2);
		allcurve.add(dup3);
		allcurve.translate(0, .5);
		return allcurve;
	}

}
