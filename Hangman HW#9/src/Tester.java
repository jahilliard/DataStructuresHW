import java.io.*;
import java.util.*;

public class Tester {
	private static BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		play(HangmanState.NORMAL_MODE);
	}

	public static void play(int mode) {
		HangmanState hangmanState = new HangmanState(load("words.txt"));
		while (hangmanState.getFeedbackToUser().contains("-")) {
			System.out.println(hangmanState);
			String letter = input();
			if (letter.length() == 1)
				hangmanState.letterGuessedByUser(letter, mode);
		}
		System.out.println(hangmanState);
	}

	public static Set<String> load(String file) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			Set<String> set = new HashSet<String>();
			while (line != null) {
				set.add(line);
				line = in.readLine();
			}
			in.close();
			return set;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String input() {
		try {
			return userInputReader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}