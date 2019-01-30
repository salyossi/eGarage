
import javax.swing.*;


public interface State {
	
	// The 6 different states of KinderGarten application
	public void goToCarInEnteranceGate();
	public void goToCarInExitGate();
	public void goToCarEnteredParking();
	public void goToCarExitFromParking();
	public void goToDriverPaying();

	public void setVisible(); // Sets the state frame to be visible
	public void updateLeds(); // Tells the parking map to refresh it self;
	public void updateStateHeader(String newText);
	public JTextArea getEntranceConsole(); // to set entrance machine console
	public JButton getEntranceButton(); // to get entrance machine enter button
	public void passCarIDAtEntranceGateToPanel(String CarIDatEnranceGate); // to get entrance machine enter button
	
	
	
}
