/**
 * Simple syllable counter without using state pattern.
 * 
 * @author Totsapon Menkul.
 *
 */
public class SimpleSyllableCounter {
	State state;
	int counter = 0;

	/**
	 * count syllables by using switch case
	 * 
	 * @param word
	 *            word that wants to count syllables.
	 * @return number of syllables.
	 */
	public int countSyllableCounter(String word) {
		state = State.START;
		counter = 0;
		word = word.toLowerCase();
		for (int index = 0; index < word.length(); index++) {
			char c = word.charAt(index);
			switch (state) {
			case START:
				if (isVowelOrY(c)) {
					if (index == word.length() - 1) {
						counter++;
					}
					state = State.SINGLE_VOWEL;
				} else if (Character.isLetter(c)) {
					state = State.CONSONANT;
				} else if (c == '-') {
					state = State.NONWORD;
				} else if (c != '\'') {
					return 0;
				}
				break;
			case SINGLE_VOWEL:
				if (c == 'e') {
					if (counter == 0 && index == word.length() - 1) {
						counter++;
					} else if (index == word.length() - 1) {
						counter++;
					} else {
						state = State.MULTI_VOWEL;
					}
				} else if (isVowels(c)) {
					if (index == word.length() - 1) {
						counter++;
					}
					state = State.MULTI_VOWEL;
				} else if (Character.isLetter(c)) {
					state = State.CONSONANT;
					counter++;
				} else if (c == '-') {
					state = State.HYPHEN;
					counter++;
				}
				break;
			case MULTI_VOWEL:
				if (c == 'e') {
					if (counter == 0 && index == word.length() - 1) {
						counter++;
					} else if (index == word.length() - 1) {
						counter++;
					} else {
						state = State.MULTI_VOWEL;
					}
				} else if (isVowels(c)) {
					if (index == word.length() - 1) {
						counter++;
					}
					state = State.MULTI_VOWEL;
				} else if (c == 'y') {
					state = State.CONSONANT;
					counter++;
				} else if (Character.isLetter(c)) {
					state = State.CONSONANT;
					counter++;
				} else if (c == '-') {
					state = State.HYPHEN;
					counter++;
				}
				break;
			case CONSONANT:
				if (c == 'e') {
					if (counter == 0 && index == word.length() - 1) {
						counter++;
					} else {
						state = State.SINGLE_VOWEL;
					}
				} else if (isVowelOrY(c)) {
					if (index == word.length() - 1) {
						counter++;
					}
					state = State.SINGLE_VOWEL;
				} else if (Character.isLetter(c)) {
					state = State.CONSONANT;
				} else if (c == '-') {
					state = State.HYPHEN;
				} else if (c == '\'') {
				} else {
					state = State.NONWORD;
				}
				break;
			case HYPHEN:
				if (isVowelOrY(c)) {
					state = State.SINGLE_VOWEL;
				} else if (Character.isLetter(c)) {
					state = State.CONSONANT;
				} else if (c == '\'') {

				} else {
					state = State.NONWORD;
				}
				break;
			default:
				break;
			}

		}
		return counter;

	}

	/**
	 * return true if a char is vowels and y.
	 * 
	 * @param c
	 *            char in word
	 * @return true if a char is vowels and y. False otherwise.
	 */
	public boolean isVowelOrY(char c) {
		return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y');
	}

	/**
	 * return true if a char is vowels.
	 * 
	 * @param c
	 *            char in a word.
	 * @return true if a char is vowels.
	 */
	public boolean isVowels(char c) {
		return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
	}
}
