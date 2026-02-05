/* Class: Setting - The purpose of this class is to create a setting enum to contain the setting of the burner and the visual representation for said settings.
 * Has a constant that stores the string representation of the display.
 * Has a constructor that gives a setting object an associated string representation of the display.
 * Has a toString() method that shows display when printed.
 *
 * Sources: Oracle Java Documentation
 */

public enum Setting {

	OFF("---"), LOW("--+"), MEDIUM("-++"), HIGH("+++"); //setting names and string representations

	private final String display;

	Setting(String display){ //constructor for Setting
		this.display = display;
	}

	@Override
	public String toString() {
		return display;
	}

}

