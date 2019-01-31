
import javax.swing.*;


public interface State {
	
	// The 6 different states of KinderGarten application
	public void GoToCarInEnteranceGate();
	public void GoToCarInExitGate();
	public void GoToCarExitFromParking();
	public void GoToDriverPaying();

	public void SetVisible(); // Sets the state frame to be visible
	public void UpdateLeds(); // Tells the parking map to refresh it self;
	public void AddCarEnteredParkingCB();
	public void RemoveCarEnteredParkingCB();
	public void RefreshEnteredParkingCB();
	public void UpdateStateHeader(String newText);
	public JTextArea GetEntranceConsole(); // to set entrance machine console
	public JButton GetEntranceButton(); // to get entrance machine enter button
	public void PassCarIDAtEntranceGateToPanel(String CarIDatEnranceGate); // to get entrance machine enter button
	
	
	
}
