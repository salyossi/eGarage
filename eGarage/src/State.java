
import javax.swing.JPanel;

public interface State {
	
	// The 6 different states of KinderGarten application
	public void goToCarInEnteranceGate();
	public void goToCarInExitGate();
	public void goToCarEnteredParking();
	public void goToCarExitFromParking();
	public void goToDriverPaying();

	public void setVisible(); // Sets the state frame to be visible

}
