
public class Line implements Curve {

	private double x1;
	private double y1;
	private double x2;
	private double y2;
	
	public Line (double p1,double p2, double p3, double p4){
		if (p1>1 || p2>1 || p3>1 || p4>1 || p1<0 || p2<0 || p3<0 || p4<0){
			x1=0;
			y1=0;
			x2=0;
			y2=0;
		}
		else{
			x1=p1;
			y1=p2;
			x2=p3;
			y2=p4;
		}
	}
	
	public void draw(SketchPad pad){
		pad.drawLine(x1, y1, x2, y2);
	}
	
	public void translate(double tx, double ty){
		x1=x1+tx;
		x2=x2+tx;
		y1=y1+ty;
		y2=y2+ty;
	}
	
	public void scale(double sx, double sy){
		x1=x1*sx;
		x2=x2*sx;
		y1=y1*sy;
		y2=y2*sy;
	}
	
	public void rotate(double degrees){
		double radians = Math.toRadians(degrees);
		double tmpx1=x1;
		double tmpx2=x2;
		x1=(x1*Math.cos(radians))-(y1*Math.sin(radians));
		x2=(x2*Math.cos(radians))-(y2*Math.sin(radians));
		y1=(tmpx1*Math.sin(radians))+(y1*Math.cos(radians));
		y2=(tmpx2*Math.sin(radians))+(y2*Math.cos(radians));
	}
	
	public Curve copy(){
		Line l = new Line (this.x1, this.y1, this.x2, this.y2);
		return l;
	}
}
