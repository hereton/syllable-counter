package oosyllablecounter;

/**
 * an interface of State
 * 
 * @author Totsapon Menkul,
 *
 */
public interface State {
	public void handleChar(char c);

	public void enterState();
}
