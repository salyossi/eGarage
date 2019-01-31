import java.util.*;
import java.awt.*;
import javax.swing.*;

public class CarExitFromParking extends MainFrame implements State, ButtonEventListener {

	private EgarageUI egarageUI;
	private StateHeader stateHeaderPanel;
	private SignPostPanel signPostPanel;
	private ParkingUseMapPanel parkingUseMapPanel;
	private EntranceMachinePanel entranceMachinePanel;
	private ExitMachinePanel exitMachinePanel;
	private PaymentMachinePanel paymentMachinePanel;
	private VirtualButtonsPanel virtualButtonsPanel;

	public CarExitFromParking(EgarageUI egarageUI) {

		this.egarageUI = egarageUI;
		stateHeader = setStateHeader("רכב יצא מחניה");
		signPost = setSignPost("תצוגת שילוט");
		parkingUseMap = setParkingUseMap("חישני החנייה");
		entranceMachine = setEntranceMachine("מכונת הכניסה");
		exitMachine = setExitMachine("מכונת היציאה");
		paymentMachine = setPaymentMachine("מכונת התשלום");
		virtualButtons = setVirtualButtons();
		DrawFrame();

	}

	@Override
	public void GoToCarInEnteranceGate() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getCarInEnteranceGate());
	}

	@Override
	public void GoToCarInExitGate() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getCarInExitGate());
	}


	@Override
	public void GoToCarExitFromParking() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getCarExitFromParking());
	}

	@Override
	public void GoToDriverPaying() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getDriverPaying());
	}

	@Override
	public void SetVisible() {
		f.setVisible(true);
	}

	@Override
	public JPanel setStateHeader(String l1Text) {
		stateHeaderPanel = new StateHeader(l1Text);
		return stateHeaderPanel.getP();
	}

	@Override
	public JPanel setSignPost(String l1Text) {
		signPostPanel = new SignPostPanel(l1Text);
		return signPostPanel.getP();
	}

	@Override
	public JPanel setParkingUseMap(String l1Text) {
		parkingUseMapPanel = new ParkingUseMapPanel();
		parkingUseMapPanel.getL1().setText(l1Text);
		return parkingUseMapPanel.getP();
	}

	@Override
	public JPanel setEntranceMachine(String l1Text) {
		entranceMachinePanel = new EntranceMachinePanel(l1Text);
		return entranceMachinePanel.getP();
	}

	@Override
	public JPanel setExitMachine(String l1Text) {
		exitMachinePanel = new ExitMachinePanel(l1Text);
		return exitMachinePanel.getP();
	}

	@Override
	public JPanel setPaymentMachine(String l1Text) {
		paymentMachinePanel = new PaymentMachinePanel(l1Text);
		return paymentMachinePanel.getP();
	}

	@Override
	public JPanel setVirtualButtons() {
		virtualButtonsPanel = new VirtualButtonsPanel();
		virtualButtonsPanel.setButtonEventListener(this);
		return virtualButtonsPanel.getP();
	}

	@Override
	public void onPressedEvent(JButton btn, Hashtable argv) {
		String arg = btn.getActionCommand();

		switch (arg) {
		case "רכב זוהה ע''י המצלמה":
			GoToCarInEnteranceGate();
			break;
		case "כרטיס חניה נלקח":

			break;
		case "הרכב עבר במחסום":

			break;
		case "רכב נכנס לחניה":
			// send to Parking map of the new state, to update its LEDs map
			egarageUI.getState().UpdateLeds();
			break;
		case "רכב יצא מחניה":
			GoToCarExitFromParking();
			// send to Parking map of the new state, to update its LEDs map
			egarageUI.getState().UpdateLeds();
			break;
		case "רכב מול מחסום יציאה":
			GoToCarInExitGate();
			break;
		case "רכב יצא מהחניון":

			break;
		case "הוכנס כרטיס חניה":
			GoToDriverPaying();
			break;
		case "סך המטבעות הוכנס":

			break;
		case "בצע תשלום":

			break;
		case "הכרטיס נלקח":

			break;

		}

		egarageUI.getState().SetVisible();

	}

	@Override
	public void UpdateLeds() {
		parkingUseMapPanel.updatePanel();
	}

	@Override
	public JTextArea GetEntranceConsole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JButton GetEntranceButton() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void UpdateStateHeader(String newText) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PassCarIDAtEntranceGateToPanel(String CarIDatEnranceGate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AddCarEnteredParkingCB() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RemoveCarEnteredParkingCB() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void RefreshEnteredParkingCB() {
		// TODO Auto-generated method stub
		
	}

}
