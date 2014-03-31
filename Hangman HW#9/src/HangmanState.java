/**
 * 
 * @author Justin Hilliard <Jhilliar>
 * @section A
 */

// YOU MAY NOT IMPORT ANY ADDITIONAL
// CLASSES OR PACKAGES
import java.util.*;

public class HangmanState {
	/*
	 * Do not change any of these global fields
	 */
	public static final int NORMAL_MODE = 0;
	public static final int HURTFUL_MODE = 1;
	public static final int HELPFUL_MODE = 2;

	public String theAnswer;
	public Set<String> lettersGuessed;
	public String feedbackToUser;
	public Set<String> possibleAnswers;

	/**
	 * Complete the HangmanState constructor as specified in the write up
	 * 
	 * @param knownWords
	 */
	public HangmanState(Set<String> knownWords) {
		theAnswer="";
		possibleAnswers=knownWords;
		int rand = (int)(Math.random()*possibleAnswers.size());
		Iterator<String> tmp = possibleAnswers.iterator();
		for (int i = 0; i<rand; i++) tmp.next();
		theAnswer = theAnswer + tmp.next();
		
		lettersGuessed = new HashSet<String>();
		
		int count = 0;
		String fullblank = "";
		while(theAnswer.length()>count){
			fullblank+="-";
			count++;
		}
		feedbackToUser=fullblank;
				
		updatePossibleAnswers();

	}

	/**
	 * Complete the feedbackFor method, which takes in an answer word, examines
	 * lettersGuessed, and returns a feedback string to the user, consisting of
	 * blanks and the correct letters that have been revealed to the user.
	 * 
	 * For example, if lettersGuessed contains the letters [a, e, n, t], then
	 * feedbackFor("secret") should return the string "-e--et", and
	 * feedbackFor("hangman") should return "-an--an".
	 * 
	 * We are not looking at the theAnswer field in this method. Later on, we
	 * will use this method to determine the feedback for a variety of possible
	 * words Ñ not just theAnswer itself. Also, your code should not modify or
	 * use the feedbackToUser field.
	 * 
	 * In your implementation of feedbackFor, you must use your StringIterator
	 * class appropriately
	 */
	public String feedbackFor(String answer) {
		Iterator<String> it = new StringIterator(answer);
		String x = "";
		while (it.hasNext()){
			String tmp = it.next();
			if (lettersGuessed.contains(tmp)) x+=tmp;
			else x+="-";
		}
		return x;
	}

	/**
	 * Complete the wrongGuesses method, which should return a set of all
	 * guesses the user has made (as found in lettersGuessed) that did not result
	 * in the revealing of a letter in feedbackToUser. For example, if
	 * lettersGuessed contains the letters [a, e, n, t], and feedbackToUser is
	 * "-e--et", then wrongGuesses() should return the set [a, n]
	 * 
	 * Do not use the theAnswer field in this method
	 * 
	 * @return
	 */
	public Set<String> wrongGuesses() {
		Set<String> incorrect = new HashSet<String>();
		incorrect.addAll(lettersGuessed); 
		Iterator<String> it = new StringIterator(feedbackToUser);
		while(it.hasNext()){
			String tmp = it.next();
			if (lettersGuessed.contains(tmp)) 
				incorrect.remove(tmp);
		}
		return incorrect;
		
	}

	/**
	 * Complete the letterGuessedByUser method, which is called whenever the
	 * user guesses a letter. This method should add this letter to
	 * lettersGuessed and update feedbackToUser. For example, if lettersGuessed
	 * originally contained [a, n, t] and theAnswer is "secret", and the letter
	 * "e" is passed to letterGuessedByUser, then lettersGuessed should now
	 * contain [a, e, n, t] and feedbackToUser should now be "-e--et". For now,
	 * your code should ignore the mode parameter
	 * 
	 * You should now be able to call letterGuessedByUser to play through a game
	 * of Hangman, one guess at a time. This is a great way to test your code
	 * 
	 * Even better, the the Tester class will play through a complete game of
	 * Hangman, prompting you to guess letters (and calling letterGuessedByUser
	 * after each guess). Compile and run Tester.java to test that you can now
	 * play a game of hangman against the computer. (For now, ignore the number
	 * printed on the right side of the computer's responses.)
	 */
	public void letterGuessedByUser(String letter, int mode) {
		if (mode==NORMAL_MODE){
			lettersGuessed.add(letter);
			feedbackToUser = feedbackFor(theAnswer);
			updatePossibleAnswers();
		}
		else if (mode==HURTFUL_MODE){
			lettersGuessed.add(letter);
			feedbackToUser = mostCommonFeedback(generateFeedbackMap());
			updatePossibleAnswers();
		}
		else if (mode==HELPFUL_MODE) {
			lettersGuessed.add(letter);
			Map<String, Integer> temp = generateFeedbackMap();
			if (temp.size()>1) temp.remove(feedbackToUser);
			feedbackToUser = mostCommonFeedback(temp);
			updatePossibleAnswers();
		}
	}

	/**
	 * The purpose of possibleAnswers is to keep track of all known words that
	 * could turn out to be the answer (if we don't already know what the answer
	 * is, and we only know what letters have been guessed so far and what
	 * feedback has been revealed)
	 */
	public void updatePossibleAnswers() {
		Set<String> temp = new HashSet<String>();
		Iterator<String> itPosAns = possibleAnswers.iterator();
		while(itPosAns.hasNext()){
			String add = itPosAns.next();
			String compare = feedbackFor(add);
			if (compare.equals(feedbackToUser)){
				temp.add(add);
			}
		}
		possibleAnswers = temp;
	}

	/**
	 * Complete the generateFeedbackMap method. This method should generate
	 * feedback for every possible answer, and return a map where the keys are
	 * these feedback strings and the values are the number of possible answers
	 * that result in that feedback string.
	 */
	public Map<String, Integer> generateFeedbackMap() {
		Map<String, Integer> feedmap = new HashMap<String, Integer>();
		Iterator<String> itPosAns = possibleAnswers.iterator();
		while (itPosAns.hasNext()){
			String comp = feedbackFor(itPosAns.next());
			if (feedmap.containsKey(comp))
				feedmap.put(comp, feedmap.get(comp)+1);
			else feedmap.put(comp, 1);
		}
		return feedmap;
	}

	/**
	 * Complete the mostCommonFeedback method. The output of createFeedbackMap
	 * will eventually be passed as the input to this method. This method should
	 * simply return the feedback string that occurred most often (and therefore
	 * corresponded to the greatest number of possible words). For example, if
	 * the feedback map shown at the end of the previous exercise is passed as
	 * the input to this method, then mostCommonFeedback should return "----",
	 * since this is the feedback string corresponding to the highest value (3)
	 * in this map. (If multiple feedback strings are tied for the highest
	 * value, it does not matter which of those feedback strings you return.)
	 */
	public String mostCommonFeedback(Map<String, Integer> feedbackMap) {
		int highestValue = 0;
		for (int value : feedbackMap.values()){
			if (value >= highestValue)
				highestValue = value;
		}
		String mostCommon = "";
		for (String keys : feedbackMap.keySet()){
			if (feedbackMap.get(keys)==highestValue){
				mostCommon += keys;
				continue;
			}
		}
		return mostCommon;
	}

	public String getFeedbackToUser() {
		return feedbackToUser;
	}

	public String toString() {
		return feedbackToUser + "\t\t" + wrongGuesses() + "\t\t"
				+ possibleAnswers.size();
	}
}
