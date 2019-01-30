import java.util.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class CarInEnteranceGate extends MainFrame implements State, ButtonEventListener {

	private EgarageUI egarageUI;
	private StateHeader stateHeaderPanel;
	private SignPostPanel signPostPanel;
	private ParkingUseMapPanel parkingUseMapPanel;
	private EntranceMachinePanel entranceMachinePanel;
	private ExitMachinePanel exitMachinePanel;
	private PaymentMachinePanel paymentMachinePanel;
	private VirtualButtonsPanel virtualButtonsPanel;
	private String carInEntranceGate;
	
	
	public CarInEnteranceGate(EgarageUI egarageUI) {
		
		this.egarageUI = egarageUI;
		stateHeader = setStateHeader("כדי להפעיל את החניון יש לבחור מצבי עבודה באמצעות האזור הווירטואלי");
		signPost = setSignPost("תצוגת שילוט");
		parkingUseMap = setParkingUseMap("חישני החנייה");
		entranceMachine = setEntranceMachine("מכונת הכניסה");
		exitMachine = setExitMachine("מכונת היציאה");
		paymentMachine = setPaymentMachine("מכונת התשלום");
		virtualButtons = setVirtualButtons();
		DrawFrame();
	}
	
	
	@Override
	public void goToCarInEnteranceGate() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getCarInEnteranceGate());
	}

	@Override
	public void goToCarInExitGate() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getCarInExitGate());
	}

	@Override
	public void goToCarEnteredParking() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getCarEnteredParking());
	}

	@Override
	public void goToCarExitFromParking() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getCarExitFromParking());		
	}

	@Override
	public void goToDriverPaying() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getDriverPaying());
	}

	@Override
	public void setVisible() {
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
		entranceMachinePanel.setButtonEventListener(this);
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
			goToCarInEnteranceGate();
			
			carInEntranceGate = argv.get("CarInEntranceGate").toString();
			
			egarageUI.getState().passCarIDAtEntranceGateToPanel(carInEntranceGate);
			egarageUI.getState().getEntranceConsole().setText("רכב מספר " + carInEntranceGate + " עומד בכניסה לחניה לחץ על כפתור הכניסה לקבלת כרטיס חניה");
			egarageUI.getState().getEntranceButton().setEnabled(true);
			egarageUI.getState().updateStateHeader("רכב עומד בכניסה יש ללחוץ על כפתור הכניסה במכונה כדי לקבל כרטיס חניה");

			break;
		case "לחץ לכניסה לחניון":		
			carInEntranceGate = argv.get("CarInEntranceGate").toString();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			
			egarageUI.getState().getEntranceConsole().setText("כרטיס חניה הונפק לרכב מספר " + carInEntranceGate + " בתאריך ובשעה " + dateFormat.format(date) + " לפתיחת המחסום נא לקחת את כרטיס החניה מהמכונה");
			egarageUI.getState().updateStateHeader("כרטיס החניה הונפק, לפתיחת המחסום נא לקחת את כרטיס החניה מהמכונה");

			break;
		case "כרטיס חניה נלקח":
			egarageUI.getState().updateStateHeader("המחסום נפתח , נא להיכנס לחניון");
			virtualButtonsPanel.getB4().setEnabled(true);
			break;
		case "הרכב עבר במחסום":			
			virtualButtonsPanel.getB4().setEnabled(false);
			virtualButtonsPanel.getT2().setEditable(true);
			
			egarageUI.getState().updateStateHeader("רכב חדש נכנס לחניון בדרך לעוד חניה טובה מוצלחת ובטוחה");
			egarageUI.getState().getEntranceConsole().setText("אין רכב בכניסה");
			
			if(!EgarageDB.isExistsCarID(Integer.parseInt(carInEntranceGate))) {
				EgarageDB.AddRegularCarIdToUserList(Integer.parseInt(carInEntranceGate));			
			}
			
			EgarageDB.AddNewCarIDToUsageList(Integer.parseInt(carInEntranceGate));
			

			break;
		case "רכב נכנס לחניה":
			goToCarEnteredParking();
			// send to Parking map of the new state, to update its LEDs map
			egarageUI.getState().updateLeds();
			break;
		case "רכב יצא מחניה":
			goToCarExitFromParking();
			// send to Parking map of the new state, to update its LEDs map
			egarageUI.getState().updateLeds();
			break;
		case "רכב מול מחסום יציאה":
			goToCarInExitGate();
			break;
		case "רכב יצא מהחניון":

			break;
		case "הוכנס כרטיס חניה":
			goToDriverPaying();
			break;
		case "סך המטבעות הוכנס":

			break;
		case "בצע תשלום":

			break;
		case "הכרטיס נלקח":

			break;

		}
		
		egarageUI.getState().setVisible();
	}


	@Override
	public void updateLeds() {
		parkingUseMapPanel.updatePanel();	
	}


	@Override
	public JTextArea getEntranceConsole() {
		return entranceMachinePanel.getTA1();
	}


	@Override
	public JButton getEntranceButton() {
		return entranceMachinePanel.getB1();
	}
	
	@Override
	public void updateStateHeader(String newText) {
		stateHeaderPanel.getL1().setText(newText);
	}


	@Override
	public void passCarIDAtEntranceGateToPanel(String CarIDatEnranceGate) {
		entranceMachinePanel.setCarIDatEnranceGate(CarIDatEnranceGate);		
	}

}
