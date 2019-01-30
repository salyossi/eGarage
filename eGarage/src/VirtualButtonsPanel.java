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

public class VirtualButtonsPanel implements ActionListener {

	private JPanel p, p1p1, pp1, p1p11, pp11, pp12, pp13, pp2, pp21, pp3, pp4;
	private JLabel l1, l1a, l11, l12, l13, l14, l15, l16, l5, l8, l81;
	private JButton ba3, b33, b4, b44, b45, b7, b10, b12, b13, b14;
	private JTextField t2, t22, t222, t223, t224, t11;
	private JComboBox c6, c66, c9;
	private JSeparator sep1, sep11, sep2;

	private ButtonEventListener myListener;
	private Hashtable<String, String> eventArgsHash = new Hashtable<String, String>();

	public VirtualButtonsPanel() {

		setP(new JPanel());
		getP().setLayout(new BoxLayout(getP(), BoxLayout.PAGE_AXIS));
		getP().setBorder(BorderFactory.createLineBorder(Color.black));

		setP1P1(new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2)));
		setL1(new JLabel("<HTML><U>אזור ווירטואלי למצב כניסת רכב מהחניון</U></HTML>"));
		getL1().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getL1().setFont(new Font("Ariel", Font.BOLD, 16));
		getP1P1().add(getL1());

		setPP1(new JPanel(new FlowLayout()));
		setT2(new JTextField(8));
		getT2().addKeyListener(new KeyListener() {

		    @Override
		    public void keyTyped(KeyEvent arg0) {

		    }

		    @Override
		    public void keyReleased(KeyEvent arg0) {
		    	// check if number for car entering the garage is entered the number
	    		// needs to be 8 digits long
	    		if (getT2().getText().length() >= 8) {
	    			getBA3().setEnabled(true);
	    		}
	    		else
	    		{
	    			getBA3().setEnabled(false);
	    		}

		    }

		    @Override
		    public void keyPressed(KeyEvent arg0) {
		        
		    }
		});
		setL1A(new JLabel("מספר הרכב הנכנס לזיהוי מצלמת הכניסה"));
		getPP1().add(getT2());
		getPP1().add(getL1A());

		setBA3(new JButton("רכב זוהה ע''י המצלמה"));
		getBA3().addActionListener(this);
		getBA3().setMaximumSize(new Dimension(180, 20));
		getBA3().setAlignmentX(Component.CENTER_ALIGNMENT);
		getBA3().setEnabled(false);

		setB4(new JButton("הרכב עבר במחסום"));
		getB4().addActionListener(this);
		getB4().setMaximumSize(new Dimension(180, 20));
		getB4().setAlignmentX(Component.CENTER_ALIGNMENT);
		getB4().setEnabled(false);

		setSep1(new JSeparator());
		getSep1().setMaximumSize(new Dimension((int) getSep1().getMaximumSize().getWidth(), 25));

		setP1P11(new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2)));
		setL11(new JLabel("<HTML><U>אזור ווירטואלי לכניסת ויציאת רכבים מחנייה</U></HTML>"));
		getL11().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getL11().setFont(new Font("Ariel", Font.BOLD, 16));
		getP1P11().add(getL11());

		setPP11(new JPanel(new FlowLayout()));
		setL12(new JLabel("רכבים בחניון"));
		String[] Vehicles3 = { "123456", "456789", "369258", "123456", "456789", "369258", "123456", "456789",
				"369258" };
		setC66(new JComboBox(Vehicles3));
		getC66().addActionListener(this);
		getC66().setName("VehiclesGoingOut");
		getC66().setMaximumSize(new Dimension(180, 20));
		setL13(new JLabel("קומה מספר"));
		setT22(new JTextField(3));
		setL14(new JLabel("חניה מספר"));
		setT222(new JTextField(3));
		getPP11().add(getT22());
		getPP11().add(getL14());
		getPP11().add(getT222());
		getPP11().add(getL13());
		getPP11().add(getC66());
		getPP11().add(getL12());

		setB33(new JButton("רכב נכנס לחניה"));
		getB33().addActionListener(this);
		getB33().setMaximumSize(new Dimension(180, 20));
		getB33().setAlignmentX(Component.CENTER_ALIGNMENT);

		setPP12(new JPanel(new FlowLayout()));
		setL15(new JLabel("קומה מספר"));
		setT223(new JTextField(3));
		setL16(new JLabel("חניה מספר"));
		setT224(new JTextField(3));
		getPP12().add(getT223());
		getPP12().add(getL16());
		getPP12().add(getT224());
		getPP12().add(getL15());

		setB44(new JButton("רכב מול מחסום יציאה"));
		getB44().addActionListener(this);
		getB44().setMaximumSize(new Dimension(180, 20));
		getB44().setAlignmentX(Component.CENTER_ALIGNMENT);

		setB45(new JButton("רכב יצא מחניה"));
		getB45().addActionListener(this);
		getB45().setMaximumSize(new Dimension(180, 20));
		getB45().setAlignmentX(Component.CENTER_ALIGNMENT);

		setSep11(new JSeparator());
		getSep11().setMaximumSize(new Dimension((int) getSep11().getMaximumSize().getWidth(), 25));

		setPP13(new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2)));
		setL5(new JLabel("<HTML><U>אזור ווירטואלי למצב יציאת רכב מהחניון</U></HTML>"));
		getL5().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getL5().setFont(new Font("Ariel", Font.BOLD, 16));
		getPP13().add(getL5());

		setPP2(new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)));
		String[] Vehicles1 = { "123456", "456789", "369258" };
		setC6(new JComboBox(Vehicles1));
		getC6().addActionListener(this);
		getC6().setName("VehiclesGoingOut");
		getC6().setMaximumSize(new Dimension(180, 20));
		getPP2().add(getC6());

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
		String[] Vehicles2 = { "123456", "456789", "369258" };
		setC9(new JComboBox(Vehicles2));
		getC9().addActionListener(this);
		getC9().setName("VehiclesToPay");
		getC9().setMaximumSize(new Dimension(180, 20));
		getPP3().add(getC9());

		setB10(new JButton("הוכנס כרטיס חניה"));
		getB10().addActionListener(this);
		getB10().setMaximumSize(new Dimension(180, 20));
		getB10().setAlignmentX(Component.CENTER_ALIGNMENT);

		setPP4(new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2)));
		setL81(new JLabel("סך המטבעות"));
		setT11(new JTextField(10));
		getPP4().add(getT11());
		getPP4().add(getL81());

		setB12(new JButton("סך המטבעות הוכנס"));
		getB12().addActionListener(this);
		getB12().setMaximumSize(new Dimension(180, 20));
		getB12().setAlignmentX(Component.CENTER_ALIGNMENT);

		getP().add(getP1P1());
		getP().add(getPP1());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getBA3());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getB4());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getSep1());
		getP().add(getP1P11());
		getP().add(getPP11());
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getB33());
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getPP12());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getB45());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getSep11());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getPP13());
		getP().add(getPP2());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getB44());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getB7());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getSep2());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getPP21());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getPP3());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getB10());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getPP4());
		getP().add(Box.createRigidArea(new Dimension(40, 2)));
		getP().add(getB12());
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

	public JTextField getT2() {
		return t2;
	}

	public void setT2(JTextField T) {
		this.t2 = T;
	}

	public JTextField getT22() {
		return t22;
	}

	public void setT22(JTextField T) {
		this.t22 = T;
	}

	public JTextField getT222() {
		return t222;
	}

	public void setT222(JTextField T) {
		this.t222 = T;
	}

	public JTextField getT223() {
		return t223;
	}

	public void setT223(JTextField T) {
		this.t223 = T;
	}

	public JTextField getT224() {
		return t224;
	}

	public void setT224(JTextField T) {
		this.t224 = T;
	}

	public JButton getBA3() {
		return ba3;
	}

	public void setBA3(JButton B) {
		this.ba3 = B;
	}

	public JButton getB33() {
		return b33;
	}

	public void setB33(JButton B) {
		this.b33 = B;
	}

	public JButton getB4() {
		return b4;
	}

	public void setB4(JButton B) {
		this.b4 = B;
	}

	public JButton getB44() {
		return b44;
	}

	public void setB44(JButton B) {
		this.b44 = B;
	}

	public JButton getB45() {
		return b45;
	}

	public void setB45(JButton B) {
		this.b45 = B;
	}

	public JLabel getL5() {
		return l5;
	}

	public void setL5(JLabel L) {
		this.l5 = L;
	}

	public JComboBox getC6() {
		return c6;
	}

	public void setC6(JComboBox C) {
		this.c6 = C;
	}

	public JComboBox getC66() {
		return c66;
	}

	public void setC66(JComboBox C) {
		this.c66 = C;
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

	public JComboBox getC9() {
		return c9;
	}

	public void setC9(JComboBox C) {
		this.c9 = C;
	}

	public JButton getB10() {
		return b10;
	}

	public void setB10(JButton B) {
		this.b10 = B;
	}

	public JTextField getT11() {
		return t11;
	}

	public void setT11(JTextField T) {
		this.t11 = T;
	}

	public JButton getB12() {
		return b12;
	}

	public void setB12(JButton B) {
		this.b12 = B;
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

	@Override
	public void actionPerformed(ActionEvent e) {

		String arg = e.getActionCommand();

		switch (arg) {
		case "רכב זוהה ע''י המצלמה":
			eventArgsHash.put("CarInEntranceGate", getT2().getText());
			getT2().setText("");
			getT2().setEditable(false);
			getBA3().setEnabled(false);
			break;
		case "כרטיס החניה נלקח":

			break;
		case "הרכב עבר במחסום":
			
			break;
		case "רכב נכנס לחניה":
			// Update the database
			EgarageDB.UpdateCarEnteredParkingSlot(getT222().getText(), getT22().getText());
			break;
		case "רכב יצא מחניה":
			// Update the database
			EgarageDB.UpdateCarExitFromParkingSlot(getT224().getText(), getT223().getText());
			break;
		case "רכב מול מחסום יציאה":

			break;
		case "רכב יצא מהחניון":

			break;
		case "הוכנס כרטיס חניה":

			break;
		case "סך המטבעות הוכנס":

		}

		// pass the event to listeners
		if (myListener != null)
			myListener.onPressedEvent((JButton) e.getSource(), (Hashtable<String, String>) eventArgsHash);

	}

	public void setButtonEventListener(ButtonEventListener listener) {
		this.myListener = listener;
	}
	

}
