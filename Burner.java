//Julia Kolomiitseva and Will Drescher
// Feb4 LO5
/* Class: Burner - The purpose of this class is to modify the setting and temperature of a burner, and to print the status of a burner.

 * Has a temperature enum that is used in an updateTemperature() method that either raises or lowers the temp when the timer hits 0.
 * Has a constructor for a burner that sets burner to default of temp cold and setting off.
 * Has a getTemperature method so Stove class can access temperature of a burner.
 * Has plusButton() and minusButton() methods to modify the setting of a burner.
 * 
 */

public class Burner {

	public enum Temperature{BLAZING, HOT, WARM, COLD}; //enum for temp
	private Temperature myTemperature; // include a getter
	private Setting mySetting; //figure 1 canvas
	private int timer; // just timer
	public final static int TIME_DURATION = 2; //initialize time duration to 2

	public Burner() { //constructor sets burner to COLD and OFF
		
		this.myTemperature = Temperature.COLD;
		this.mySetting = Setting.OFF;
		
	}

	public Temperature getTemperature() { //getter for Temperature
		return myTemperature;
	}
	public void plusButton() { //raise setting by one
		
		switch(mySetting) {
		
		case HIGH:
			break; //already on high, no effect

		case MEDIUM:
			mySetting = Setting.HIGH;
			break;

		case LOW:
			mySetting = Setting.MEDIUM;
			break;

		case OFF:
			mySetting = Setting.LOW;
			break;	
		}
		
		timer = TIME_DURATION; //reset timer to TIME_DURATION
	}

	public void minusButton() { // lowef temp by one notch
		
		switch(mySetting) {
		case OFF: //already OFF, no effect
			break;

		case LOW:
			mySetting = Setting.OFF;
			break;

		case MEDIUM:
			mySetting = Setting.LOW;
			break;

		case HIGH:
			mySetting = Setting.MEDIUM;
			break;
		}
		
		timer = TIME_DURATION; //reset timer to TIME_DURATION
	}

	public void updateTemperature() { // hypothetically called every minute, update time and tepm

		if (timer > 0) {
			timer--;  //decrement timer if still counting down, do not trigger temp adjustment
		}

		if(timer == 0) { //when timer hits 0, adjust the temp toward the setting
			switch (myTemperature) {

			case COLD:
				if (mySetting != Setting.OFF) {
					myTemperature = Temperature.WARM;
					timer = TIME_DURATION;  //reset timer
				}
				break;

			case WARM:
				if (mySetting == Setting.OFF) {
					myTemperature = Temperature.COLD; //drop temp to cold to get closer to OFF
					timer = TIME_DURATION;
				} 
				else if (mySetting == Setting.MEDIUM || mySetting == Setting.HIGH) {
					myTemperature = Temperature.HOT; //raise temp to hot to get closer to MEDIUM or HIGH
					timer = TIME_DURATION;
				}
				break;

			case HOT:
				if (mySetting == Setting.HIGH) {
					myTemperature = Temperature.BLAZING; //raise temp to match HIGH
					timer = TIME_DURATION;
				} 
				else if (mySetting == Setting.LOW || mySetting == Setting.OFF) {
					myTemperature = Temperature.WARM; //raise temp to warm to get closer to HIGH
					timer = TIME_DURATION; 
				}
				break;

			case BLAZING:
				if (mySetting != Setting.HIGH) {
					myTemperature = Temperature.HOT; //raise temp to hot to get closer to HIGH
					timer = TIME_DURATION; //reset timer
				}
				break;

			} //end switch statement
		}



	}

	public void display() { //show the status of the burner

		String description = "";
		switch(myTemperature) {
		
		case COLD:
			description = "cooool";
			break;
			
		case WARM:
			description = "warm";
			break;
			
		case HOT:
			description = "CAREFUL";
			break;
			
		case BLAZING:
			description = "VERY HOT! DON'T TOUCH";
			break;
			
		} //end switch statement
		
		System.out.println("[" + mySetting.toString() + "]....." + description);
	}

}
