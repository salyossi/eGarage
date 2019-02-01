import java.util.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class EgarageUI extends MainFrame implements ButtonEventListener {

	private StateHeader stateHeaderPanel;
	private SignPostPanel signPostPanel;
	private ParkingUseMapPanel parkingUseMapPanel;
	private EntranceMachinePanel entranceMachinePanel;
	private ExitMachinePanel exitMachinePanel;
	private PaymentMachinePanel paymentMachinePanel;
	private VirtualButtonsPanel virtualButtonsPanel;
	private String carInEntranceGate;
	private String carEnteredParking;
	private String carExitFromParking;
	private String carInExitGate;
	private String payingCarID;

	public EgarageUI() {

		stateHeader = setStateHeader("כדי להפעיל את החניון יש לבחור מצבי עבודה באמצעות האזור הווירטואלי");
		signPost = setSignPost("תצוגת שילוט");
		parkingUseMap = setParkingUseMap("חישני החנייה");
		entranceMachine = setEntranceMachine("מכונת הכניסה");
		exitMachine = setExitMachine("מכונת היציאה");
		paymentMachine = setPaymentMachine("מכונת התשלום");
		virtualButtons = setVirtualButtons();
		DrawFrame();
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
			// get car ID at entrance gate from virtual Panel via hash table
			carInEntranceGate = argv.get("CarInEntranceGate").toString();

			// Pass car ID at entrance gate to entrance machine Panel
			entranceMachinePanel.setCarIDatEnranceGate(carInEntranceGate);
			
			// Update entrance machine console with relevant text
			GetEntranceConsole().setText(
					"רכב מספר " + carInEntranceGate + " עומד בכניסה לחניה לחץ על כפתור הכניסה לקבלת כרטיס חניה");
			// enable next button in process
			GetEntranceButton().setEnabled(true);
			
			// update UI header text
			UpdateStateHeader("רכב עומד בכניסה יש ללחוץ על כפתור הכניסה במכונה כדי לקבל כרטיס חניה");

			break;
			
		case "לחץ לכניסה לחניון":
			// get car ID at entrance gate from virtual Panel via hash table
			carInEntranceGate = argv.get("CarInEntranceGate").toString();

			// make a date now() variable
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			// Update entrance machine console with relevant text
			GetEntranceConsole().setText("כרטיס חניה הונפק לרכב מספר " + carInEntranceGate + " בתאריך ובשעה "
					+ dateFormat.format(date) + " לפתיחת המחסום נא לקחת את כרטיס החניה מהמכונה");
			
			// update UI header text
			UpdateStateHeader("כרטיס החניה הונפק, לפתיחת המחסום נא לקחת את כרטיס החניה מהמכונה");

			break;
			
		case "כרטיס חניה נלקח":
			// Update entrance machine console with relevant text
			UpdateStateHeader("המחסום נפתח , נא להיכנס לחניון");
			
			// enable next button in process
			virtualButtonsPanel.getB4().setEnabled(true);
			break;
			
		case "הרכב עבר במחסום":
			// enable next button in process and disable not relevant ones
			virtualButtonsPanel.getB4().setEnabled(false);
			virtualButtonsPanel.getT2().setEditable(true);

			// update UI header text
			UpdateStateHeader("רכב חדש נכנס לחניון בדרך לעוד חניה טובה מוצלחת ובטוחה");
			
			// Update entrance machine console with relevant text
			GetEntranceConsole().setText("אין רכב בכניסה");

			// Update DB with the new car in garage (not yet in a parking slot)
			if (!EgarageDB.isExistsCarID(Integer.parseInt(carInEntranceGate))) {
				EgarageDB.AddRegularCarIdToUserList(Integer.parseInt(carInEntranceGate));
			}
			EgarageDB.AddNewCarIDToUsageList(Integer.parseInt(carInEntranceGate));
			
			// Update combo box of cars to enter a parking slot with the car just entered
			virtualButtonsPanel.getVehiclesEnteredGarageModel().addElement(carInEntranceGate);

			break;
			
		case "רכב נכנס לחניה":
			// get car ID just entered a parking slot, from virtual Panel via hash table
			carEnteredParking = argv.get("CarEnteredParking").toString();
			// Update combo box of cars to enter a parking slot with the car just entered a parking slot
			virtualButtonsPanel.getVehiclesEnteredGarageModel().removeElement(carEnteredParking);
			
			// send to Parking map of the new state, to update its LEDs map
			UpdateLeds();

			break;
			
		case "רכב יצא מחניה":
			// get car ID just exit from a parking slot, from virtual Panel via hash table
			carExitFromParking = argv.get("CarExitFromParking").toString();
			// Update combo box of cars to enter a parking slot with the car just exit from a parking slot
			virtualButtonsPanel.getVehiclesEnteredGarageModel().addElement(carExitFromParking);
			
			// send to Parking map of the new state, to update its LEDs map
			UpdateLeds();
			
			break;
			
		case "רכב מול מחסום יציאה":
			// get car ID at exit gate from virtual Panel via hash table
			carInExitGate = argv.get("CarExitingParking").toString();
			
			if(EgarageDB.isAuthorized(carInExitGate)) {
				// Update exit machine console with relevant text
				GetExitConsole().setText("רכב מספר " + carInExitGate + " מאושר ליציאה - המחסום נפתח נא לצאת");
				
				// update UI header text
				UpdateStateHeader("רכב מאושר ליציאה - דרך צלחה ותודה שהשתמשתם בחניון של יוסי סאשה וזאב");
				
			} else {
				// Update exit machine console with relevant text
				GetExitConsole().setText("רכב מספר " + carInExitGate + " אינו מאושר ליציאה - המחסום ישאר סגור, נא לגשת לעמדת התשלום");
				
				// update UI header text
				UpdateStateHeader("רכב לא מאושר ליציאה - יש לגשת למכונת התשלום");
				
				// disable next button in process and force client to go and pay
				virtualButtonsPanel.getB7().setEnabled(false);
			}
			
			break;
		case "רכב יצא מהחניון":
			// Update exit machine console with relevant text
			GetExitConsole().setText("המחסום סגור - אין רכב ביציאה");
								
			// update UI header text
			UpdateStateHeader("כדי להפעיל את החניון יש לבחור מצבי עבודה באמצעות האזור הווירטואלי");

			// Delete from DB the car exiting the garage
			EgarageDB.DeleteCarExitingGarage(carInExitGate);
			
			// Update combo box of cars to exit
			virtualButtonsPanel.getVehiclesEnteredGarageModel().removeElement(carInExitGate);
						
			break;
			
		case "הוכנס כרטיס חניה":
			// get car ID at exit gate from virtual Panel via hash table
			payingCarID = argv.get("PayingCarID").toString();
			
			// update Usage list with time of payment
			EgarageDB.UpdateCarIsPayingUsageList(Integer.parseInt(payingCarID));
			
			
			
			
			break;
		case "סך המטבעות הוכנס":

			break;
		case "בצע תשלום":

			break;
		case "הכרטיס נלקח":

			break;

		}

	}


	public void UpdateLeds() {
		parkingUseMapPanel.updatePanel();
	}

	public JTextArea GetEntranceConsole() {
		return entranceMachinePanel.getTA1();
	}
	
	public JButton GetEntranceButton() {
		return entranceMachinePanel.getB1();
	}
	
	public JTextArea GetExitConsole() {
		return exitMachinePanel.getTA1();
	}

	public void UpdateStateHeader(String newText) {
		stateHeaderPanel.getL1().setText(newText);
	}

}
