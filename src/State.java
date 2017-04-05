/**
 * Enum class of State
 * 
 * @author Totsapon Menkul.
 *
 */
public enum State {
	START("Start"), SINGLE_VOWEL("Single_Vowel"), CONSONANT("Consonant"), MULTI_VOWEL("Multi_Vowel"), NONWORD(
			"Nonword"), HYPHEN("Hyphen");
	String name;

	private State(String name) {
		this.name = name;
	}
}
