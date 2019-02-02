import java.util.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class EgarageUI extends MainFrame implements ButtonEventListener, AlarmEventListener {

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
	private java.sql.Timestamp parkingStartTime;
	private java.sql.Timestamp parkingEndTime;
	private int parkingTimeInHours;
	private int parkingTimeInDays;
	private String parkingTimeTotal;
	private String paymentConsoleTextWithoutCoins;
	private int coinsEntered = 0;
	int amountToPay;

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
		parkingUseMapPanel.setAlarmEventListener(this);
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
		paymentMachinePanel.setButtonEventListener(this);
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
			
			// send to Parking map of the new state, to update its LEDs map
			UpdateLeds();
	
			break;

		case "רכב יצא מחניה":
			// get car ID just exit from a parking slot, from virtual Panel via hash table
			carExitFromParking = argv.get("CarExitFromParking").toString();
			// Update combo box of cars to enter a parking slot with the car just exit from
			// a parking slot
			virtualButtonsPanel.getVehiclesEnteredGarageModel().addElement(carExitFromParking);

			// send to Parking map of the new state, to update its LEDs map
			UpdateLeds();

			break;

		case "רכב מול מחסום יציאה":
			// get car ID at exit gate from virtual Panel via hash table
			carInExitGate = argv.get("CarExitingParking").toString();

			if (EgarageDB.isAuthorized(carInExitGate)) {
				// Update exit machine console with relevant text
				GetExitConsole().setText("רכב מספר " + carInExitGate + " מאושר ליציאה - המחסום נפתח נא לצאת");

				// update UI header text
				UpdateStateHeader("רכב מאושר ליציאה - דרך צלחה ותודה שהשתמשתם בחניון של יוסי סאשה וזאב");

			} else {
				// Update exit machine console with relevant text
				GetExitConsole().setText(
						"רכב מספר " + carInExitGate + " אינו מאושר ליציאה - המחסום ישאר סגור, נא לגשת לעמדת התשלום");

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

			// Update paying machine console with general instructions and reset virtual
			// buttons
			GetPayingConsole().setText("הכנס כרטיס לתשלום");

			break;

		case "הוכנס כרטיס חניה":
			// get car ID at exit gate from virtual Panel via hash table
			payingCarID = argv.get("PayingCarID").toString();

			// update Usage list with time of payment
			EgarageDB.UpdateCarIsPayingUsageList(Integer.parseInt(payingCarID));

			parkingStartTime = EgarageDB.ParkingStartTime(payingCarID);
			String parkingStartTime_Date = new SimpleDateFormat("dd/MM/yyyy").format(parkingStartTime);
			String parkingStartTime_Time = new SimpleDateFormat("HH:mm").format(parkingStartTime);

			parkingEndTime = EgarageDB.ParkingEndTime(payingCarID);
			String parkingEndTime_Date = new SimpleDateFormat("dd/MM/yyyy").format(parkingEndTime);
			String parkingEndTime_Time = new SimpleDateFormat("HH:mm").format(parkingEndTime);

			parkingTimeInHours = EgarageDB.ParkingTimeInHours(payingCarID);

			if (parkingTimeInHours <= 12) {
				amountToPay = 15 * parkingTimeInHours;
				if (parkingTimeInHours == 1)
					parkingTimeTotal = "סה''כ זמן החנייה - שעה אחת \n";
				else
					parkingTimeTotal = "סה''כ זמן החנייה " + parkingTimeInHours + " שעות\n";
			} else {
				parkingTimeInDays = EgarageDB.ParkingTimeInDays(payingCarID);
				amountToPay = 40 * parkingTimeInDays;
				if (parkingTimeInDays == 1)
					parkingTimeTotal = "סה''כ זמן החנייה - יום אחד \n";
				else
					parkingTimeTotal = "סה''כ זמן החנייה " + parkingTimeInDays + " ימים\n";
			}

			paymentConsoleTextWithoutCoins = "רכב מספר " + payingCarID + "\n" + "התחיל את החניה בתאריך - "
					+ parkingStartTime_Date + " בשעה " + parkingStartTime_Time + "\n" + "סיים את החניה בתאריך - "
					+ parkingEndTime_Date + " בשעה " + parkingEndTime_Time + "\n" + parkingTimeTotal
					+ "הלקוח מתבקש לשלם את הסכום " + amountToPay + " ש''ח\n";

			// Update paying machine console with the amount to pay
			GetPayingConsole().setText(paymentConsoleTextWithoutCoins);

			// update UI header text
			UpdateStateHeader("פרטי התשלום מופיעים על צג מכונת התשלום");

			break;
		case "סך המטבעות הוכנס":

			// get car ID at exit gate from virtual Panel via hash table
			coinsEntered += Integer.parseInt(argv.get("CoinsEntered").toString());

			// Update paying machine console with the coins entered
			GetPayingConsole()
					.setText(paymentConsoleTextWithoutCoins + "סכום המטבעות אשר הוכנסו למכונה " + coinsEntered + "\n");

			// Enable next button in process
			if (coinsEntered >= amountToPay)
				paymentMachinePanel.getB1().setEnabled(true);

			break;
		case "בצע תשלום":
			// update car to be authorized in DB
			EgarageDB.SetAuthorized(Integer.parseInt(payingCarID));

			// Update paying machine console with the coins entered
			GetPayingConsole().setText(paymentConsoleTextWithoutCoins + " התשלום בוצע");

			// update UI header text
			UpdateStateHeader("התשלום בוצע בהצלחה - תודה ויום טוב");

			break;
		case "קבל את הכרטיס":

			if (coinsEntered > amountToPay)
				paymentMachinePanel.getB5().setEnabled(true);

			// Update paying machine console with the coins entered
			GetPayingConsole().setText(paymentConsoleTextWithoutCoins + " הכרטיס נלקח מהמכונה");

			// Update paying machine console with general instructions and reset virtual
			// buttons
			// GetPayingConsole().setText("הכנס כרטיס לתשלום");
			virtualButtonsPanel.getB10().setEnabled(true);
			virtualButtonsPanel.getC9().setEnabled(true);
			virtualButtonsPanel.getVehiclesInPaymentModeModel().removeElement(payingCarID);
			virtualButtonsPanel.getT11().setText("");
			virtualButtonsPanel.getT11().setEditable(false);
			virtualButtonsPanel.getB12().setEnabled(false);

			virtualButtonsPanel.getC6().setEnabled(true);
			virtualButtonsPanel.getB44().setEnabled(true);
			virtualButtonsPanel.getB7().setEnabled(true);
			break;

		case "קבל את העודף":

			// Update paying machine console with the coins entered
			GetPayingConsole().setText(
					paymentConsoleTextWithoutCoins + "עודף בסך " + (coinsEntered - amountToPay) + "ש'''ח נלקח מהמכונה");

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

	public JTextArea GetPayingConsole() {
		return paymentMachinePanel.getTA1();
	}

	public void UpdateStateHeader(String newText) {
		stateHeaderPanel.getL1().setText(newText);
	}

	@Override
	public void raseAlarm(int Level, int Slot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkAlarm(int Level, int Slot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void reportAlarm(String AlarmMessage) {
		// update UI header text
		UpdateStateHeader(AlarmMessage);

		
		
	}

}
