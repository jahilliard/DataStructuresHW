import java.util.ArrayList;
import java.util.Scanner;

public class until42 {
	public static void main(String[] args) {
		ArrayList out= new ArrayList();
		int num = 0;
		while (num != 42){
			Scanner in = new Scanner(System.in);
			num = in.nextInt();
			out.add(num);
		}
		while(!out.isEmpty()) {
			if (out.size()!=1){
				System.out.println(out.remove(0));
			}
		}
	}

}
