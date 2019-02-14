import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class VirtualButtonsPanel extends Exception implements ActionListener {

	private JPanel p, p1p1, pp1, p1p11, pp11, pp12, pp13, pp2, pp21, pp3, pp4;
	private JLabel l1, l1a, l11, l12, l13, l14, l15, l16, l5, l8, l81;
	private JButton carIDRecognizedByCameraButton;
	private JButton carPassedBarier;
	private JButton carEnteredParking;
	private JButton carExitParking;
	private JButton carInFrontOfExitBarier;
	private JButton b7, parkingCardEntered;
	private JButton amountOfCoinsEntered, b13, b14;
	private JTextField carIDtextField;
	private JTextField enterParkingLevelTextField;
	private JTextField enterParkingSlotTextField;
	private JTextField exitParkingLevelTextField;
	private JTextField exitParkingSlotTextField;
	private JTextField amountOfCoinsField;
	private JComboBox vehiclesEnteredGarageComboBox;
	private JComboBox carIDInExitModeComboBox;
	private JComboBox carInPaymentModeComboBox;
	private JSeparator sep1, sep11, sep2;
	private Vector<String> vehiclesEnteredGarageV;
	private DefaultComboBoxModel<String> vehiclesEnteredGarageModel;
	private Vector<String> vehiclesInPaymentModeV;
	private DefaultComboBoxModel<String> vehiclesInPaymentModeModel;

	private ButtonEventListener myListener;
	private Hashtable<String, String> eventArgsHash = new Hashtable<String, String>();

	public VirtualButtonsPanel() {

		setP(new JPanel());
		getP().setLayout(new BoxLayout(getP(), BoxLayout.PAGE_AXIS));
		getP().setBorder(BorderFactory.createLineBorder(Color.black));

		setP1P1(new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2)));
		setL1(new JLabel("<HTML><U>אזור ווירטואלי למצב כניסת רכב אל החניון</U></HTML>"));
		getL1().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getL1().setFont(new Font("Ariel", Font.BOLD, 16));
		getP1P1().add(getL1());

		setPP1(new JPanel(new FlowLayout()));
		setCarIDtextField(new JTextField(8));
		getCarIDtextField().addActionListener(this);
		getCarIDtextField().addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// check if number for car entering the garage is entered the number
				// needs to be 8 digits long
				if (getCarIDtextField().getText().length() >= 8) {
					getCarIDRecognizedByCameraButton().setEnabled(true);
				} else {
					getCarIDRecognizedByCameraButton().setEnabled(false);
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});
		setL1A(new JLabel("מספר הרכב הנכנס לזיהוי מצלמת הכניסה"));
		getPP1().add(getCarIDtextField());
		getPP1().add(getL1A());

		setCarIDRecognizedByCameraButton(new JButton("רכב זוהה ע''י המצלמה"));
		getCarIDRecognizedByCameraButton().addActionListener(this);
		getCarIDRecognizedByCameraButton().setMaximumSize(new Dimension(180, 20));
		getCarIDRecognizedByCameraButton().setAlignmentX(Component.CENTER_ALIGNMENT);
		getCarIDRecognizedByCameraButton().setEnabled(false);

		setCarPassedBarier(new JButton("הרכב עבר במחסום"));
		getCarPassedBarier().addActionListener(this);
		getCarPassedBarier().setMaximumSize(new Dimension(180, 20));
		getCarPassedBarier().setAlignmentX(Component.CENTER_ALIGNMENT);
		getCarPassedBarier().setEnabled(false);

		setSep1(new JSeparator());
		getSep1().setMaximumSize(new Dimension((int) getSep1().getMaximumSize().getWidth(), 25));

		setP1P11(new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2)));
		setL11(new JLabel("<HTML><U>אזור ווירטואלי לכניסת ויציאת רכבים מחנייה</U></HTML>"));
		getL11().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getL11().setFont(new Font("Ariel", Font.BOLD, 16));
		getP1P11().add(getL11());

		setPP11(new JPanel(new FlowLayout()));
		setL12(new JLabel("רכבים בחניון"));

		vehiclesEnteredGarageV = new Vector<String>();
		vehiclesEnteredGarageV = EgarageDB.getCarsEnteredGarage();
		vehiclesEnteredGarageModel = new DefaultComboBoxModel<String>(vehiclesEnteredGarageV);

		setVehiclesEnteredGarageComboBox(new JComboBox<String>(vehiclesEnteredGarageModel));

		getVehiclesEnteredGarageComboBox().setName("VehiclesGoingOut");
		getVehiclesEnteredGarageComboBox().setMaximumSize(new Dimension(180, 20));
		setL13(new JLabel("קומה מספר"));
		setEnterParkingLevelTextField(new JTextField(3));
		getEnterParkingLevelTextField().addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// check if number for car entering the garage is entered the number
				// needs to be 8 digits long
				if (getEnterParkingLevelTextField().getText().length() >= 1 && getEnterParkingSlotTextField().getText().length() >= 1
						&& getVehiclesEnteredGarageComboBox().getItemCount() != 0) {
					getCarEnteredParking().setEnabled(true);
				} else {
					getCarEnteredParking().setEnabled(false);
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});
		setL14(new JLabel("חניה מספר"));
		setEnterParkingSlotTextField(new JTextField(3));
		getEnterParkingSlotTextField().addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// check if number for car entering the garage is entered the number
				// needs to be 8 digits long
				if (getEnterParkingLevelTextField().getText().length() >= 1 && getEnterParkingSlotTextField().getText().length() >= 1
						&& getVehiclesEnteredGarageComboBox().getItemCount() != 0) {
					getCarEnteredParking().setEnabled(true);
				} else {
					getCarEnteredParking().setEnabled(false);
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});
		getPP11().add(getEnterParkingSlotTextField());
		getPP11().add(getL14());
		getPP11().add(getEnterParkingLevelTextField());
		getPP11().add(getL13());
		getPP11().add(getVehiclesEnteredGarageComboBox());
		getPP11().add(getL12());

		setCarEnteredParking(new JButton("רכב נכנס לחניה"));
		getCarEnteredParking().setEnabled(false);
		getCarEnteredParking().addActionListener(this);
		getCarEnteredParking().setMaximumSize(new Dimension(180, 20));
		getCarEnteredParking().setAlignmentX(Component.CENTER_ALIGNMENT);

		setPP12(new JPanel(new FlowLayout()));
		setL15(new JLabel("קומה מספר"));
		setExitParkingLevelTextField(new JTextField(3));
		getExitParkingLevelTextField().addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// check if number for car entering the garage is entered the number
				// needs to be 8 digits long
				if (getExitParkingLevelTextField().getText().length() >= 1 && getExitParkingSlotTextField().getText().length() >= 1) {
					getCarExitParking().setEnabled(true);
				} else {
					getCarExitParking().setEnabled(false);
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});
		setL16(new JLabel("חניה מספר"));
		setExitParkingSlotTextField(new JTextField(3));
		getExitParkingSlotTextField().addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// check if number for car entering the garage is entered the number
				// needs to be 8 digits long
				if (getExitParkingLevelTextField().getText().length() >= 1 && getExitParkingSlotTextField().getText().length() >= 1) {
					getCarExitParking().setEnabled(true);
				} else {
					getCarExitParking().setEnabled(false);
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});
		getPP12().add(getExitParkingSlotTextField());
		getPP12().add(getL16());		
		getPP12().add(getExitParkingLevelTextField());
		getPP12().add(getL15());
		setCarExitParking(new JButton("רכב יצא מחניה"));
		getCarExitParking().setEnabled(false);
		getCarExitParking().addActionListener(this);
		getCarExitParking().setMaximumSize(new Dimension(180, 20));
		getCarExitParking().setAlignmentX(Component.CENTER_ALIGNMENT);

		setSep11(new JSeparator());
		getSep11().setMaximumSize(new Dimension((int) getSep11().getMaximumSize().getWidth(), 25));

		setPP13(new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2)));
		setL5(new JLabel("<HTML><U>אזור ווירטואלי למצב יציאת רכב מהחניון</U></HTML>"));
		getL5().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getL5().setFont(new Font("Ariel", Font.BOLD, 16));
		getPP13().add(getL5());

		setPP2(new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)));
		setCarIDInExitModeComboBox(new JComboBox<String>(vehiclesEnteredGarageModel));
		getCarIDInExitModeComboBox().setName("VehiclesGoingOut");
		getCarIDInExitModeComboBox().setMaximumSize(new Dimension(180, 20));
		getPP2().add(getCarIDInExitModeComboBox());

		setCarInFrontOfExitBarier(new JButton("רכב מול מחסום יציאה"));
		getCarInFrontOfExitBarier().addActionListener(this);
		getCarInFrontOfExitBarier().setMaximumSize(new Dimension(180, 20));
		getCarInFrontOfExitBarier().setAlignmentX(Component.CENTER_ALIGNMENT);

		setB7(new JButton("רכב יצא מהחניון"));
		getB7().addActionListener(this);
		getB7().setMaximumSize(new Dimension(180, 20));
		getB7().setAlignmentX(Component.CENTER_ALIGNMENT);

		setSep2(new JSeparator());
		getSep2().setMaximumSize(new Dimension((int) getSep2().getMaximumSize().getWidth(), 25));

		setPP21(new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2)));
		setL8(new JLabel("<HTML><U>אזור ווירטואלי למצב תשלום</U></HTML>"));
		getL8().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getL8().setFont(new Font("Ariel", Font.BOLD, 16));
		getPP21().add(getL8());

		setPP3(new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)));
		vehiclesInPaymentModeV = new Vector<String>();
		vehiclesInPaymentModeV = EgarageDB.getCarsInPaymentMode();
		vehiclesInPaymentModeModel = new DefaultComboBoxModel<String>(vehiclesInPaymentModeV);
		setCarInPaymentModeComboBox(new JComboBox<String>(vehiclesInPaymentModeModel));
		getCarInPaymentModeComboBox().setName("VehiclesToPay");
		getCarInPaymentModeComboBox().setMaximumSize(new Dimension(180, 20));
		getPP3().add(getCarInPaymentModeComboBox());

		setParkingCardEntered(new JButton("הוכנס כרטיס חניה"));
		getParkingCardEntered().addActionListener(this);
		getParkingCardEntered().setMaximumSize(new Dimension(180, 20));
		getParkingCardEntered().setAlignmentX(Component.CENTER_ALIGNMENT);

		setPP4(new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2)));
		setL81(new JLabel("סך המטבעות"));
		setAmountOfCoinsField(new JTextField(10));
		getAmountOfCoinsField().setEditable(false);
		getPP4().add(getAmountOfCoinsField());
		getPP4().add(getL81());

		setAmountOfCoinsEntered(new JButton("סך המטבעות הוכנס"));
		getAmountOfCoinsEntered().addActionListener(this);
		getAmountOfCoinsEntered().setMaximumSize(new Dimension(180, 20));
		getAmountOfCoinsEntered().setAlignmentX(Component.CENTER_ALIGNMENT);
		getAmountOfCoinsEntered().setEnabled(false);

		getP().add(getP1P1());
		getP().add(getPP1());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getCarIDRecognizedByCameraButton());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getCarPassedBarier());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getSep1());
		getP().add(getP1P11());
		getP().add(getPP11());
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getCarEnteredParking());
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getPP12());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getCarExitParking());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getSep11());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getPP13());
		getP().add(getPP2());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getCarInFrontOfExitBarier());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getB7());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getSep2());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getPP21());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getPP3());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getParkingCardEntered());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getPP4());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getAmountOfCoinsEntered());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));

	}

	public JPanel getP() {
		return p;
	}

	public void setP(JPanel p) {
		this.p = p;
	}

	public void setP1P1(JPanel P) {
		this.p1p1 = P;
	}

	public JPanel getP1P1() {
		return p1p1;
	}

	public void setPP1(JPanel P) {
		this.pp1 = P;
	}

	public JPanel getPP1() {
		return pp1;
	}

	public void setP1P11(JPanel P) {
		this.p1p11 = P;
	}

	public JPanel getP1P11() {
		return p1p11;
	}

	public void setPP11(JPanel P) {
		this.pp11 = P;
	}

	public JPanel getPP11() {
		return pp11;
	}

	public void setPP12(JPanel P) {
		this.pp12 = P;
	}

	public JPanel getPP12() {
		return pp12;
	}

	public void setPP13(JPanel P) {
		this.pp13 = P;
	}

	public JPanel getPP13() {
		return pp13;
	}

	public void setPP2(JPanel P) {
		this.pp2 = P;
	}

	public JPanel getPP2() {
		return pp2;
	}

	public void setPP21(JPanel P) {
		this.pp21 = P;
	}

	public JPanel getPP21() {
		return pp21;
	}

	public void setPP3(JPanel P) {
		this.pp3 = P;
	}

	public JPanel getPP3() {
		return pp3;
	}

	public void setPP4(JPanel P) {
		this.pp4 = P;
	}

	public JPanel getPP4() {
		return pp4;
	}

	public JLabel getL1() {
		return l1;
	}

	public void setL1(JLabel L) {
		this.l1 = L;
	}

	public JLabel getL1A() {
		return l1a;
	}

	public void setL1A(JLabel L) {
		this.l1a = L;
	}

	public JLabel getL11() {
		return l11;
	}

	public void setL11(JLabel L) {
		this.l11 = L;
	}

	public JLabel getL12() {
		return l12;
	}

	public void setL12(JLabel L) {
		this.l12 = L;
	}

	public JLabel getL13() {
		return l13;
	}

	public void setL13(JLabel L) {
		this.l13 = L;
	}

	public JLabel getL14() {
		return l14;
	}

	public void setL14(JLabel L) {
		this.l14 = L;
	}

	public JLabel getL15() {
		return l15;
	}

	public void setL15(JLabel L) {
		this.l15 = L;
	}

	public JLabel getL16() {
		return l16;
	}

	public void setL16(JLabel L) {
		this.l16 = L;
	}

	public JTextField getCarIDtextField() {
		return carIDtextField;
	}

	public void setCarIDtextField(JTextField T) {
		this.carIDtextField = T;
	}

	public JTextField getEnterParkingLevelTextField() {
		return enterParkingLevelTextField;
	}

	public void setEnterParkingLevelTextField(JTextField T) {
		this.enterParkingLevelTextField = T;
	}

	public JTextField getEnterParkingSlotTextField() {
		return enterParkingSlotTextField;
	}

	public void setEnterParkingSlotTextField(JTextField T) {
		this.enterParkingSlotTextField = T;
	}

	public JTextField getExitParkingLevelTextField() {
		return exitParkingLevelTextField;
	}

	public void setExitParkingLevelTextField(JTextField T) {
		this.exitParkingLevelTextField = T;
	}

	public JTextField getExitParkingSlotTextField() {
		return exitParkingSlotTextField;
	}

	public void setExitParkingSlotTextField(JTextField T) {
		this.exitParkingSlotTextField = T;
	}

	public JButton getCarIDRecognizedByCameraButton() {
		return carIDRecognizedByCameraButton;
	}

	public void setCarIDRecognizedByCameraButton(JButton B) {
		this.carIDRecognizedByCameraButton = B;
	}

	public JButton getCarEnteredParking() {
		return carEnteredParking;
	}

	public void setCarEnteredParking(JButton B) {
		this.carEnteredParking = B;
	}

	public JButton getCarPassedBarier() {
		return carPassedBarier;
	}

	public void setCarPassedBarier(JButton B) {
		this.carPassedBarier = B;
	}

	public JButton getCarInFrontOfExitBarier() {
		return carInFrontOfExitBarier;
	}

	public void setCarInFrontOfExitBarier(JButton B) {
		this.carInFrontOfExitBarier = B;
	}

	public JButton getCarExitParking() {
		return carExitParking;
	}

	public void setCarExitParking(JButton B) {
		this.carExitParking = B;
	}

	public JLabel getL5() {
		return l5;
	}

	public void setL5(JLabel L) {
		this.l5 = L;
	}

	public JComboBox getCarIDInExitModeComboBox() {
		return carIDInExitModeComboBox;
	}

	public void setCarIDInExitModeComboBox(JComboBox C) {
		this.carIDInExitModeComboBox = C;
	}

	public JComboBox getVehiclesEnteredGarageComboBox() {
		return vehiclesEnteredGarageComboBox;
	}

	public void setVehiclesEnteredGarageComboBox(JComboBox C) {
		this.vehiclesEnteredGarageComboBox = C;
	}

	public JButton getB7() {
		return b7;
	}

	public void setB7(JButton B) {
		this.b7 = B;
	}

	public JLabel getL8() {
		return l8;
	}

	public void setL8(JLabel L) {
		this.l8 = L;
	}

	public JLabel getL81() {
		return l81;
	}

	public void setL81(JLabel L) {
		this.l81 = L;
	}

	public JComboBox getCarInPaymentModeComboBox() {
		return carInPaymentModeComboBox;
	}

	public void setCarInPaymentModeComboBox(JComboBox C) {
		this.carInPaymentModeComboBox = C;
	}

	public JButton getParkingCardEntered() {
		return parkingCardEntered;
	}

	public void setParkingCardEntered(JButton B) {
		this.parkingCardEntered = B;
	}

	public JTextField getAmountOfCoinsField() {
		return amountOfCoinsField;
	}

	public void setAmountOfCoinsField(JTextField T) {
		this.amountOfCoinsField = T;
	}

	public JButton getAmountOfCoinsEntered() {
		return amountOfCoinsEntered;
	}

	public void setAmountOfCoinsEntered(JButton B) {
		this.amountOfCoinsEntered = B;
	}

	public JButton getB13() {
		return b13;
	}

	public void setB13(JButton B) {
		this.b13 = B;
	}

	public JButton getB14() {
		return b14;
	}

	public void setB14(JButton B) {
		this.b14 = B;
	}

	public JSeparator getSep1() {
		return sep1;
	}

	public void setSep1(JSeparator Seperator) {
		this.sep1 = Seperator;
	}

	public JSeparator getSep11() {
		return sep11;
	}

	public void setSep11(JSeparator Seperator) {
		this.sep11 = Seperator;
	}

	public JSeparator getSep2() {
		return sep2;
	}

	public void setSep2(JSeparator Seperator) {
		this.sep2 = Seperator;
	}

	public DefaultComboBoxModel<String> getVehiclesEnteredGarageModel() {
		return vehiclesEnteredGarageModel;
	}

	public DefaultComboBoxModel<String> getVehiclesInPaymentModeModel() {
		return vehiclesInPaymentModeModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == getCarIDtextField()) {
			getCarIDRecognizedByCameraButton().doClick();
			return;
		}

		String arg = e.getActionCommand();

		switch (arg) {
		case "רכב זוהה ע''י המצלמה":
			String tmpCarIDtextField = getCarIDtextField().getText();
			getCarIDtextField().setText("");
			getCarIDtextField().setEditable(false);
			getCarIDRecognizedByCameraButton().setEnabled(false);

			eventArgsHash.put("CarInEntranceGate", tmpCarIDtextField);

			break;

		case "כרטיס החניה נלקח":

			break;

		case "הרכב עבר במחסום":

			getEnterParkingLevelTextField().setText("");
			getEnterParkingSlotTextField().setText("");
			getExitParkingLevelTextField().setText("");
			getExitParkingSlotTextField().setText("");

			break;

		case "רכב נכנס לחניה":

			try {
				String tmpVehiclesEnteredGarageComboBox = getVehiclesEnteredGarageComboBox().getSelectedItem().toString();

				String tmpEnterParkingLevelTextField = getEnterParkingLevelTextField().getText();
				String tmpEnterParkingSlotTextField = getEnterParkingSlotTextField().getText();

				getCarExitParking().setEnabled(false);
				getEnterParkingLevelTextField().setText("");
				getEnterParkingSlotTextField().setText("");

				// Add to hash table the car ID that enter a parking
				eventArgsHash.put("CarEnteredParking", tmpVehiclesEnteredGarageComboBox);

				// Update the parking list in database
				EgarageDB.UpdateCarEnteredParkingSlot(tmpEnterParkingSlotTextField, tmpEnterParkingLevelTextField);
				// Update usage list in database
				EgarageDB.UpdateCarEnteredParkingSlotUsageList(Integer.parseInt(tmpEnterParkingSlotTextField), Integer.parseInt(tmpEnterParkingLevelTextField),
						Integer.parseInt(tmpVehiclesEnteredGarageComboBox));
				
				// if the car is VIP or Handicaps - mark as authorized
				// so it dose not need to go to the paying machine
				// else add the car to paying list if its not already there
				int tmpUserListCarType = EgarageDB.getCarTypeInUserList(Integer.parseInt(tmpVehiclesEnteredGarageComboBox));
				if(tmpUserListCarType > 1)
					EgarageDB.SetAuthorized(Integer.parseInt(tmpVehiclesEnteredGarageComboBox));
				else
					// Update combo box of cars in payment mode
					
					if(getVehiclesInPaymentModeModel().getIndexOf(tmpVehiclesEnteredGarageComboBox) == -1 ) {
						getVehiclesInPaymentModeModel().addElement(tmpVehiclesEnteredGarageComboBox);
					}
					
					

				// Add to hash table the car ID that enter a parking
				String tmpParkingListCarType = Integer.toString(EgarageDB.getCarTypeInParkingList(Integer.parseInt(tmpEnterParkingSlotTextField), Integer.parseInt(tmpEnterParkingLevelTextField)));
				eventArgsHash.put("ParkingListCarType", tmpParkingListCarType);
				eventArgsHash.put("ParkingLevelUsed", tmpEnterParkingLevelTextField);
				eventArgsHash.put("parkingSlotUsed", tmpEnterParkingSlotTextField);
				
				// Update combo box of cars to enter a parking slot with the car just entered a
				// parking slot
				getVehiclesEnteredGarageModel().removeElement(tmpVehiclesEnteredGarageComboBox);


			} catch (Exception e01) {
				// don't do any this here
				return;
			}

			break;

		case "רכב יצא מחניה":

			String tmpExitParkingLevelTextField = getExitParkingLevelTextField().getText();
			String tmpExitParkingSlotTextField = getExitParkingSlotTextField().getText();
			
			int tmpCaeExitFromParking = EgarageDB.getCarIdInLevelAndSlot(Integer.parseInt(tmpExitParkingSlotTextField), Integer.parseInt(tmpExitParkingLevelTextField));

			if (tmpCaeExitFromParking == 0) {
				return;
			}
			
			getCarExitParking().setEnabled(false);
			getExitParkingLevelTextField().setText("");
			getExitParkingSlotTextField().setText("");

			// Add to hash table the car ID that exits from parking
			eventArgsHash.put("CarExitFromParking", Integer.toString(tmpCaeExitFromParking));

			// Update the parking list in database
			EgarageDB.UpdateCarExitFromParkingSlot(tmpExitParkingSlotTextField, tmpExitParkingLevelTextField);
			// Update usage list in database
			EgarageDB.UpdateCarExitFromParkingSlotUsageList(Integer.parseInt(tmpExitParkingSlotTextField), Integer.parseInt(tmpExitParkingLevelTextField));

			
			// Add to hash table the car ID that exit from a parking
			String tmpParkingListCarType = Integer.toString(EgarageDB.getCarTypeInParkingList(Integer.parseInt(tmpExitParkingSlotTextField), Integer.parseInt(tmpExitParkingLevelTextField)));
			eventArgsHash.put("ParkingListCarType", tmpParkingListCarType);
			eventArgsHash.put("ParkingLevelUsed", tmpExitParkingLevelTextField);
			eventArgsHash.put("parkingSlotUsed", tmpExitParkingSlotTextField);
			
			break;

		case "רכב מול מחסום יציאה":

			try {
				String tmpCarIDInExitModeComboBox = getCarIDInExitModeComboBox().getSelectedItem().toString();

				getCarInFrontOfExitBarier().setEnabled(false);
				getCarIDInExitModeComboBox().setEnabled(false);

				// Add to hash table the car ID that enter a parking
				eventArgsHash.put("CarExitingParking", tmpCarIDInExitModeComboBox);

			} catch (Exception e01) {
				// don't do any this here
				return;
			}

			break;

		case "רכב יצא מהחניון":
			getCarInFrontOfExitBarier().setEnabled(true);
			getCarIDInExitModeComboBox().setEnabled(true);

			break;

		case "הוכנס כרטיס חניה":

			try {
				String tmpCarInPaymentModeComboBox = getCarInPaymentModeComboBox().getSelectedItem().toString();

				getAmountOfCoinsField().setEditable(true);
				getAmountOfCoinsEntered().setEnabled(true);

				getParkingCardEntered().setEnabled(false);
				getCarInPaymentModeComboBox().setEnabled(false);

				// Add to hash table the car ID that enter a parking
				eventArgsHash.put("PayingCarID", tmpCarInPaymentModeComboBox);

			} catch (Exception e01) {
				// don't do any this here
				return;
			}

			break;

		case "סך המטבעות הוכנס":
			String tmpT11 = getAmountOfCoinsField().getText();

			// Add to hash table the amount of coins entered
			eventArgsHash.put("CoinsEntered", tmpT11);

		}

		// pass the event to listeners
		if (myListener != null)
			myListener.onPressedEvent((JButton) e.getSource(), (Hashtable<String, String>) eventArgsHash);

	}

	public void setButtonEventListener(ButtonEventListener listener) {
		this.myListener = listener;
	}

}
