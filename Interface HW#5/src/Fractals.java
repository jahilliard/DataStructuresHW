/**
 * 
 * @author Justin Hilliard <jhilliar>
 * @section A
 */

public class Fractals {
	
	public static Curve generateFractal(Fractal fractal, int step) {
		if(step <= 0) return fractal.step0();
		Curve c = generateFractal(fractal, step-1);
		return fractal.transform(c);
	}

	public static void main(String[] args) {
		
	}
}