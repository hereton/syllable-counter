package oosyllablecounter;

/**
 * Syllable counter using state machine
 * 
 * @author Totsapon Menkul.
 *
 */
public class OOSyllableCounter {
	private final State START = new StartState();
	private final State SINGLE_VOWEL = new SingleVowelState();
	private final State MULTI_VOWEL = new MultiVowelState();
	private final State CONSONANT = new ConsonantState();
	private final State HYPHEN = new HyphenState();
	private final State NONWORD = new NonWordState();
	private State state;
	private int syllableCount = 0;
	private int wordLength = 0;
	private int index;

	/**
	 * Count syllables by using state machine
	 * 
	 * @param word
	 *            word that wants to count syllables
	 * @return number of counted syllables
	 */
	public int countSyllables(String word) {
		state = START;
		wordLength = word.length() - 1;
		word = word.toLowerCase();
		syllableCount = 0;
		for (index = 0; index <= wordLength; index++) {
			char c = word.charAt(index);
			state.handleChar(c);
		}
		return syllableCount;
	}

	/**
	 * Set state in each char in word
	 * 
	 * @param newstate
	 *            new state that matched.
	 */
	public void setState(State newstate) {
		this.state = newstate;
		this.state.enterState();
	}

	/**
	 * return true if a char is vowels and y.
	 * 
	 * @param c
	 *            char in word
	 * @return true if a char is vowels and y. False otherwise.
	 */
	public boolean isVowelOrY(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y';
	}

	/**
	 * return true if a char is vowels.
	 * 
	 * @param c
	 *            char in a word.
	 * @return true if a char is vowels.
	 */
	public boolean isVowels(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	/**
	 * Start state
	 * 
	 * @author Totsapon Menkul.
	 *
	 */
	class StartState implements State {

		@Override
		public void handleChar(char c) {
			if (isVowelOrY(c)) {
				if (index == wordLength) {
					syllableCount++;
				}
				setState(SINGLE_VOWEL);
			} else if (Character.isLetter(c)) {
				setState(CONSONANT);
			} else if (c == '-' || c != '\'') {
				setState(NONWORD);
			}
		}

		@Override
		public void enterState() {
		}
	}

	/**
	 * Single vowel state
	 * 
	 * @author Totsapon Menkul.
	 *
	 */
	class SingleVowelState implements State {

		@Override
		public void handleChar(char c) {
			if (c == 'e') {
				if (syllableCount == 0 && index == wordLength) {
					syllableCount++;
				} else if (index == wordLength) {
					syllableCount++;
				} else {
					setState(MULTI_VOWEL);
				}
			} else if (isVowels(c)) {
				if (index == wordLength) {
					syllableCount++;
				}
				setState(MULTI_VOWEL);
			} else if (Character.isLetter(c)) {
				setState(CONSONANT);
				syllableCount++;
			} else if (c == '-') {
				setState(HYPHEN);
				syllableCount++;
			}
		}

		@Override
		public void enterState() {
		}
	}

	/**
	 * Multiple vowel state
	 * 
	 * @author Totsapon Menkul.
	 *
	 */
	class MultiVowelState implements State {

		@Override
		public void handleChar(char c) {
			if (c == 'e') {
				if (syllableCount == 0 && index == wordLength) {
					syllableCount++;
				} else if (index == wordLength) {
					syllableCount++;
				} else {
					setState(MULTI_VOWEL);
				}
			} else if (isVowels(c)) {
				if (index == wordLength) {
					syllableCount++;
				}
				setState(MULTI_VOWEL);
			} else if (c == 'y') {
				setState(CONSONANT);
				syllableCount++;
			} else if (Character.isLetter(c)) {
				syllableCount++;
				setState(CONSONANT);
			} else if (c == '-') {
				setState(HYPHEN);
				syllableCount++;
			}
		}

		@Override
		public void enterState() {
		}

	}

	/**
	 * Consonant state
	 * 
	 * @author Totsapon Menkul.
	 *
	 */
	class ConsonantState implements State {

		@Override
		public void handleChar(char c) {
			if (c == 'e') {
				if (syllableCount == 0 && index == wordLength) {
					syllableCount++;
				} else {
					setState(SINGLE_VOWEL);
				}
			} else if (isVowelOrY(c)) {
				if (index == wordLength) {
					syllableCount++;
				}
				setState(SINGLE_VOWEL);
			} else if (Character.isLetter(c)) {
				setState(CONSONANT);
			} else if (c == '-') {
				setState(HYPHEN);
			} else if (c != '\'') {
				setState(NONWORD);
			}
		}

		@Override
		public void enterState() {
		}

	}

	/**
	 * Hyphen state ('-')
	 * 
	 * @author Totsapon Menkul.
	 *
	 */
	class HyphenState implements State {

		@Override
		public void handleChar(char c) {
			if (isVowelOrY(c)) {
				setState(SINGLE_VOWEL);
			} else if (Character.isLetter(c)) {
				setState(CONSONANT);
			} else if (c != '\'') {
				setState(NONWORD);
			}
		}

		@Override
		public void enterState() {
		}

	}

	/**
	 * Non word state (not a letters)
	 * 
	 * @author Totsapon Menkul.
	 *
	 */
	class NonWordState implements State {

		@Override
		public void handleChar(char c) {
		}

		@Override
		public void enterState() {
		}

	}

}
