import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class cashMaschine {
	public static void main(String[] args) {
		double balance = 120;
		double toWithdraw = 190;
		double fee = .5;
		if (toWithdraw<balance && toWithdraw%5==0){
			System.out.print(balance-toWithdraw-fee);
		}
		else {
			System.out.print(balance);
		}
	}
}
