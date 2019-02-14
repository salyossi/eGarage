import java.util.*;
import java.util.Timer;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class EgarageUI extends MainFrame implements ButtonEventListener {

	private UIHeader uiHeaderPanel; // Panel object to host general instructions as big header if UI
	private SignPostPanel signPostPanel; // Panel object illustrating all SignPost installed in the Garage
	private ParkingUseMapPanel parkingUseMapPanel; // Panel object illustrating all parking indicators and LEDs
													// installed in every parking slot
	private EntranceMachinePanel entranceMachinePanel; // Panel object illustrating the entrance machine installed in
														// every entrance gate of the Garage
	private ExitMachinePanel exitMachinePanel; // Panel object illustrating the exit machine installed in every exit
												// gate of the Garage
	private PaymentMachinePanel paymentMachinePanel; // Panel object illustrating the payment machine
	private VirtualButtonsPanel virtualButtonsPanel; // Panel object illustrating the interaction between the driver and
														// the system like
														// taking the parking ticket from the entrance machine and also
														// illustrating interactions
														// between deferment hardwares and the software system like
														// camera identifying the car number
														// and sending the number to the system or car passing the
														// barrier and then the barrier is closing.
	private String carInEntranceGate; // A private member to hold the carID reported by the entrance gate camera -
										// here it is done by the virtual panel
	private String carEnteredParking; // A private member to hold the carID reported entering by the parking slot
										// camera - here it is done by the virtual panel
	private String carExitFromParking; // A private member to hold the carID reported exiting by the parking slot
										// camera - here it is done by the virtual panel

	private String parkingListCarType; // A private member to hold the car type of a specific parking slot (1= General,
										// 2=VIP, 3=handicaps)
	private String parkingLevelUsed; // A private member to hold the level of a specific parking slot - in this demo
										// there are 4 levels (0 - 3)
	private String parkingSlotUsed; // A private member to hold the level of a specific parking slot - in this demo
	// there are 10 slots in every levels (1 - 10)

	private String carInExitGate; // A private member to hold the carID reported by the exit gate camera - here it
									// is done by the virtual panel
	private String payingCarID; // A private member to hold the carID reported by the parking ticket when
								// entered to the paying machine - here it is done by the virtual panel
	private java.sql.Timestamp parkingStartTime; // A private member to hold the start time of parking when car enters a
													// parking slot
	private java.sql.Timestamp parkingEndTime; // A private member to hold the end time of parking when paying a parking
												// ticket
	private int parkingTimeInHours; // A private member to hold the time of parking in hours if <= 12 hours celled
									// i.e. 1.5 hours = 2 hours for payment
	private int parkingTimeInDays; // A private member to hold the time of parking in days if > 12 hours celled
									// i.e. 1.5 days = 2 days for payment
	private String parkingTimeTotal; // A private String member to hold the time for displaying in payment machine
	private String paymentConsoleTextWithoutCoins; // A private String member to hold the start time, end time, duration
													// and cost for displaying in payment machine
	private int coinsEntered = 0; // A private member to hold the amount of coins entered to the payment machine
	int amountToPay; // A private member to hold the amount to pay

	// variables for alarm
	private Timer timer;
	private int alarmColumn = 0;
	private int alarmRow = 0;
	private boolean isAlarmOn;
	AlarmClass ac;

	public EgarageUI() {

		stateHeader = setUIHeader("כדי להפעיל את החניון יש לבחור מצבי עבודה באמצעות האזור הווירטואלי"); // A supper
																										// JPanel member
																										// variable
																										// created via
																										// local Panel
																										// object
		signPost = setSignPost("תצוגת שילוט"); // A supper JPanel member variable created via local Panel object
		parkingUseMap = setParkingUseMap("חישני החנייה"); // A supper JPanel member variable created via local Panel
															// object
		entranceMachine = setEntranceMachine("מכונת הכניסה"); // A supper JPanel member variable created via local Panel
																// object
		exitMachine = setExitMachine("מכונת היציאה"); // A supper JPanel member variable created via local Panel object
		paymentMachine = setPaymentMachine("מכונת התשלום"); // A supper JPanel member variable created via local Panel
															// object
		virtualButtons = setVirtualButtons(); // A supper JPanel member variable created via local Panel object
		
		ac = new AlarmClass();
		
		DrawFrame(); // A Super method that draws the UI
		f.setVisible(true);
	}

	@Override
	public JPanel setUIHeader(String l1Text) {
		uiHeaderPanel = new UIHeader(l1Text);
		return uiHeaderPanel.getP();
	}

	@Override
	public JPanel setSignPost(String l1Text) {
		signPostPanel = new SignPostPanel(l1Text);
		return signPostPanel.getP();
	}

	@Override
	public JPanel setParkingUseMap(String l1Text) {
		parkingUseMapPanel = new ParkingUseMapPanel(l1Text);
		return parkingUseMapPanel.getP();
	}

	@Override
	public JPanel setEntranceMachine(String l1Text) {
		entranceMachinePanel = new EntranceMachinePanel(l1Text);
		// register this object as the button event listener so when button is pressed
		// this object will know
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
		// register this object as the button event listener so when button is pressed
		// this object will know
		paymentMachinePanel.setButtonEventListener(this);
		return paymentMachinePanel.getP();
	}

	@Override
	public JPanel setVirtualButtons() {
		virtualButtonsPanel = new VirtualButtonsPanel();
		// register this object as the button event listener so when button is pressed
		// this object will know
		virtualButtonsPanel.setButtonEventListener(this);
		return virtualButtonsPanel.getP();
	}

	// when buttons are activated in registered pannels this method is fired
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
			UpdateUIHeader("רכב עומד בכניסה יש ללחוץ על כפתור הכניסה במכונה כדי לקבל כרטיס חניה");

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
			UpdateUIHeader("כרטיס החניה הונפק, לפתיחת המחסום נא לקחת את כרטיס החניה מהמכונה");

			break;

		case "כרטיס חניה נלקח":
			// Update entrance machine console with relevant text
			UpdateUIHeader("המחסום נפתח , נא להיכנס לחניון");

			// enable next button in process
			virtualButtonsPanel.getCarPassedBarier().setEnabled(true);
			break;

		case "הרכב עבר במחסום":
			// enable next button in process and disable not relevant ones
			virtualButtonsPanel.getCarPassedBarier().setEnabled(false);
			virtualButtonsPanel.getCarIDtextField().setEditable(true);

			// update UI header text
			UpdateUIHeader("רכב חדש נכנס לחניון בדרך לעוד חניה טובה מוצלחת ובטוחה");

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

			// update signPost
			// get car Type just entered a parking slot, from virtual Panel via hash table
			parkingListCarType = argv.get("ParkingListCarType").toString();
			// get Parking level just used by a car entering a parking slot, from virtual
			// Panel via hash table
			parkingLevelUsed = argv.get("ParkingLevelUsed").toString();
			// get Parking Slot just used by a car entering a parking from virtual
			// Panel via hash table
			parkingSlotUsed = argv.get("parkingSlotUsed").toString();

			signPostPanel.updateSignPost(parkingLevelUsed, parkingListCarType);
			// check if needs to raise an alarm
			
			uiHeaderPanel.getL1().setText("רכב  נכנס לחניה בקומה " + parkingLevelUsed + " בחניה מספר " + parkingSlotUsed);

			// if needed raise an alarm
			raseAlarm(Integer.parseInt(parkingLevelUsed), Integer.parseInt(parkingSlotUsed));

			break;

		case "רכב יצא מחניה":
			// get car ID just exit from a parking slot, from virtual Panel via hash table
			carExitFromParking = argv.get("CarExitFromParking").toString();
			// Update combo box of cars to enter a parking slot with the car just exit from
			// a parking slot
			virtualButtonsPanel.getVehiclesEnteredGarageModel().addElement(carExitFromParking);

			// send to Parking map of the new state, to update its LEDs map
			UpdateLeds();

			// update signPost
			// get car Type just entered a parking slot, from virtual Panel via hash table
			parkingListCarType = argv.get("ParkingListCarType").toString();
			// get Parking level just used by a car exiting a parking slot, from virtual
			// Panel via hash table
			parkingLevelUsed = argv.get("ParkingLevelUsed").toString();
			// get Parking Slot just used by a car exiting a parking from virtual
			// Panel via hash table
			parkingSlotUsed = argv.get("parkingSlotUsed").toString();

			signPostPanel.updateSignPost(parkingLevelUsed, parkingListCarType);
			
			uiHeaderPanel.getL1().setText("רכב  יצא מהחניה בקומה " + parkingLevelUsed + " בחניה מספר " + parkingSlotUsed);

			// check if alarm is working
			checkAlarm(Integer.parseInt(parkingLevelUsed), Integer.parseInt(parkingSlotUsed));

			break;

		case "רכב מול מחסום יציאה":
			// get car ID at exit gate from virtual Panel via hash table
			carInExitGate = argv.get("CarExitingParking").toString();

			if (EgarageDB.isAuthorized(carInExitGate)) {
				// Update exit machine console with relevant text
				GetExitConsole().setText("רכב מספר " + carInExitGate + " מאושר ליציאה - המחסום נפתח נא לצאת");

				// update UI header text
				UpdateUIHeader("רכב מאושר ליציאה - דרך צלחה ותודה שהשתמשתם בחניון של יוסי סאשה וזאב");

			} else {
				// Update exit machine console with relevant text
				GetExitConsole().setText(
						"רכב מספר " + carInExitGate + " אינו מאושר ליציאה - המחסום ישאר סגור, נא לגשת לעמדת התשלום");

				// update UI header text
				UpdateUIHeader("רכב לא מאושר ליציאה - יש לגשת למכונת התשלום");

				// disable next button in process and force client to go and pay
				virtualButtonsPanel.getB7().setEnabled(false);
			}

			break;
		case "רכב יצא מהחניון":
			// Update exit machine console with relevant text
			GetExitConsole().setText("המחסום סגור - אין רכב ביציאה");

			// update UI header text
			UpdateUIHeader("כדי להפעיל את החניון יש לבחור מצבי עבודה באמצעות האזור הווירטואלי");

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
			UpdateUIHeader("פרטי התשלום מופיעים על צג מכונת התשלום");

			break;
		case "סך המטבעות הוכנס":

			// get car ID at exit gate from virtual Panel via hash table
			coinsEntered += Integer.parseInt(argv.get("CoinsEntered").toString());

			// Update paying machine console with the coins entered
			GetPayingConsole()
					.setText(paymentConsoleTextWithoutCoins + "סכום המטבעות אשר הוכנסו למכונה " + coinsEntered + "\n");

			// Enable next button in process
			if (coinsEntered >= amountToPay)
				paymentMachinePanel.getMakePayment().setEnabled(true);

			break;
		case "בצע תשלום":
			// update car to be authorized in DB
			EgarageDB.SetAuthorized(Integer.parseInt(payingCarID));

			// Update paying machine console with the coins entered
			GetPayingConsole().setText(paymentConsoleTextWithoutCoins + " התשלום בוצע");

			// update UI header text
			UpdateUIHeader("התשלום בוצע בהצלחה - תודה ויום טוב");

			// and reset virtual buttons and disable coins field and button
			virtualButtonsPanel.getAmountOfCoinsField().setText("");
			virtualButtonsPanel.getAmountOfCoinsField().setEditable(false);
			virtualButtonsPanel.getAmountOfCoinsEntered().setEnabled(false);

			break;
		case "קבל את הכרטיס":

			if (coinsEntered > amountToPay) {
				paymentMachinePanel.getTakeChange().setEnabled(true);
			} else {
				coinsEntered = 0; // reset coins entered for next car if no change else reset in change area

				// Update paying machine console with general instructions
				virtualButtonsPanel.getParkingCardEntered().setEnabled(true);
				virtualButtonsPanel.getCarInPaymentModeComboBox().setEnabled(true);
				virtualButtonsPanel.getVehiclesInPaymentModeModel().removeElement(payingCarID);

				virtualButtonsPanel.getCarIDInExitModeComboBox().setEnabled(true);
				virtualButtonsPanel.getCarInFrontOfExitBarier().setEnabled(true);
				virtualButtonsPanel.getB7().setEnabled(true);
			}

			// Update paying machine console that card was taken
			GetPayingConsole().setText(paymentConsoleTextWithoutCoins + " הכרטיס נלקח מהמכונה");

			break;

		case "קבל את העודף":

			// Update paying machine console with the coins entered
			GetPayingConsole().setText(
					paymentConsoleTextWithoutCoins + "עודף בסך " + (coinsEntered - amountToPay) + "ש'''ח נלקח מהמכונה");

			// Update paying machine console with general instructions and reset virtual
			// buttons
			// GetPayingConsole().setText("הכנס כרטיס לתשלום");
			virtualButtonsPanel.getParkingCardEntered().setEnabled(true);
			virtualButtonsPanel.getCarInPaymentModeComboBox().setEnabled(true);
			virtualButtonsPanel.getVehiclesInPaymentModeModel().removeElement(payingCarID);

			virtualButtonsPanel.getCarIDInExitModeComboBox().setEnabled(true);
			virtualButtonsPanel.getCarInFrontOfExitBarier().setEnabled(true);
			virtualButtonsPanel.getB7().setEnabled(true);

			coinsEntered = 0; // reset coins entered for next car
		}

	}

	public void UpdateLeds() {
		parkingUseMapPanel.updatePanel();
	}

	public JTextArea GetEntranceConsole() {
		return entranceMachinePanel.getEntranceMachineConsole();
	}

	public JButton GetEntranceButton() {
		return entranceMachinePanel.getEntranceButon();
	}

	public JTextArea GetExitConsole() {
		return exitMachinePanel.getExitMachineConsole();
	}

	public JTextArea GetPayingConsole() {
		return paymentMachinePanel.getPaymentMachineConsole();
	}

	public void UpdateUIHeader(String newText) {
		uiHeaderPanel.getL1().setText(newText);
	}

	public void raseAlarm(int Level, int Slot) {

		int tmpCarIdInLevelAndSlot = EgarageDB.getCarIdInLevelAndSlot(Slot, Level);
		int tmpCarTypeInParkingList = EgarageDB.getCarTypeInParkingList(Slot, Level);
		int tmpCarTypeInUserList = EgarageDB.getCarTypeInUserList(tmpCarIdInLevelAndSlot);

		if ((tmpCarTypeInParkingList == 3 && tmpCarTypeInUserList != 3)
				|| (tmpCarTypeInParkingList == 2 && tmpCarTypeInUserList == 1)) {

			uiHeaderPanel.getL1().setText("רכב לא מאושר נכנס לחניה בקומה " + Level + " בחניה מספר " + Slot + " הזמזם הופעל");
			
			ac.start();

			isAlarmOn = true;
			alarmColumn = Slot;
			alarmRow = Level;
		}

	}

	public void checkAlarm(int Level, int Slot) {

		if (alarmColumn == Slot && alarmRow == Level && isAlarmOn) {

			uiHeaderPanel.getL1().setText("רכב לא מאושר יצא מהחניה בקומה " + Level + " בחניה מספר " + Slot + " הזמזם הופסק");
			
			ac.stop();
			
			alarmColumn = 0;
			alarmRow = 0;
			isAlarmOn = false;

		}
	}

}
