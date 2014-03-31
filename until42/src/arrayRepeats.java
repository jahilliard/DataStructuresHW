
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class arrayRepeats {
	public static void main(String[] args) {
		ArrayList<Integer> main = new ArrayList<Integer>();
		main.add(2);
		main.add(2);
		main.add(3);
		main.add(4);
		main.add(5);
		main.add(6);
		main.add(7);
		main.add(9);
		main.add(9);
		for (int i=main.size()-1;i>0;i--){
			if (main.get(i)==main.get(i--)){
				main.remove(i);
			}
		}
		System.out.print(main);
	}
}
