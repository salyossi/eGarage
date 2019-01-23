import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VirtualButtonsPanel implements ActionListener {

	private JPanel p, pp1, pp11, pp2, pp3, pp4;
	private JLabel l1, l11, l5, l8;
	private JButton b3, b33, b4, b44, b7, b10, b12, b13, b14;
	private JTextField t2, t22, t222, t11;
	private JComboBox c6, c66, c9;
	private JSeparator sep1, sep11, sep2;

	private ButtonEventListener myListener;

	public VirtualButtonsPanel(String l1Text) {

		setP(new JPanel());
		getP().setLayout(new BoxLayout(getP(), BoxLayout.PAGE_AXIS));
		getP().setBorder(BorderFactory.createLineBorder(Color.black));

		setL1(new JLabel("אזור ווירטואלי למצב כניסת רכב מהחניון"));
		getL1().setFont(new Font("Ariel", Font.BOLD, 16));

		setPP1(new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2)));		
		setT2(new JTextField(10));		
		getPP1().add(getT2());

		setB3(new JButton("כרטיס החניה נלקח"));
		getB3().addActionListener(this);
		getB3().setMaximumSize(new Dimension(200, 40));

		setB4(new JButton("הרכב עבר"));
		getB4().addActionListener(this);
		getB4().setMaximumSize(new Dimension(200, 40));
		
		setSep1(new JSeparator());
		getSep1().setMaximumSize(new Dimension((int) getSep1().getMaximumSize().getWidth(), 25));

		setL11(new JLabel("אזור ווירטואלי לכניסת ויציאת רכבים מחנייה"));
		getL11().setFont(new Font("Ariel", Font.BOLD, 16));

		setPP11(new JPanel(new FlowLayout()));
		String[] Vehicles3 = { "123456", "456789", "369258" };
		setC66(new JComboBox(Vehicles3));
		getC66().addActionListener(this);
		getC66().setName("VehiclesGoingOut");
		getC66().setMaximumSize(new Dimension(200, 40));
		setT22(new JTextField(10));
		setT222(new JTextField(10));
		getPP11().add(getT22());
		getPP11().add(getT222());
		getPP11().add(getC66());

		setB33(new JButton("רכב נכנס לחניה"));
		getB33().addActionListener(this);
		getB33().setMaximumSize(new Dimension(200, 40));

		setB44(new JButton("רכב יצא מחניה"));
		getB44().addActionListener(this);
		getB44().setMaximumSize(new Dimension(200, 40));

		setSep11(new JSeparator());
		getSep11().setMaximumSize(new Dimension((int) getSep11().getMaximumSize().getWidth(), 25));

		setL5(new JLabel("אזור ווירטואלי למצב יציאת רכב מהחניון"));
		getL5().setFont(new Font("Ariel", Font.BOLD, 16));

		setPP2(new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)));
		String[] Vehicles1 = { "123456", "456789", "369258" };
		setC6(new JComboBox(Vehicles1));
		getC6().addActionListener(this);
		getC6().setName("VehiclesGoingOut");
		getC6().setMaximumSize(new Dimension(200, 40));
		getPP2().add(getC6());

		setB7(new JButton("הרכב יצא"));
		getB7().addActionListener(this);
		getB7().setMaximumSize(new Dimension(200, 40));

		setSep2(new JSeparator());
		getSep2().setMaximumSize(new Dimension((int) getSep2().getMaximumSize().getWidth(), 25));

		setL8(new JLabel("אזור ווירטואלי למצב תשלום"));
		getL8().setFont(new Font("Ariel", Font.BOLD, 16));
		
		setPP3(new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)));
		String[] Vehicles2 = { "123456", "456789", "369258" };
		setC9(new JComboBox(Vehicles2));
		getC9().addActionListener(this);
		getC9().setName("VehiclesToPay");
		getC9().setMaximumSize(new Dimension(200, 40));
		getPP3().add(getC9());
		
		setB10(new JButton("הוכנס כרטיס חניה"));
		getB10().addActionListener(this);
		getB10().setMaximumSize(new Dimension(200, 40));
		
		setPP4(new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2)));
		setT11(new JTextField(10));
		getPP4().add(getT11());
		
		setB12(new JButton("סך המטבעות הוכנס"));
		getB12().addActionListener(this);
		getB12().setMaximumSize(new Dimension(200, 40));
		
		setB13(new JButton("בצע תשלום"));
		getB13().addActionListener(this);
		getB13().setMaximumSize(new Dimension(200, 40));
		
		setB14(new JButton("הכרטיס נלקח"));
		getB14().addActionListener(this);
		getB14().setMaximumSize(new Dimension(200, 40));
                   
		getP().add(getL1());
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getPP1());                         
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getB3());                         
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getB4());                         
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getSep1());
		getP().add(getL11());
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getPP11());                         
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getB33());                         
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getB44());                         
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getSep11());
		getP().add(Box.createRigidArea(new Dimension(40, 1)));
		getP().add(getL5());                         
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getPP2());                         
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getB7());                         
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getSep2());                       
		getP().add(Box.createRigidArea(new Dimension(40, 1)));
		getP().add(getL8());                         
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getPP3());                         
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getB10());                       
		getP().add(Box.createRigidArea(new Dimension(40, 1)));
		getP().add(getPP4());                         
		getP().add(Box.createRigidArea(new Dimension(40, 1)));
		getP().add(getB12());                         
		getP().add(Box.createRigidArea(new Dimension(40, 1)));
		getP().add(getB13());                       
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
		getP().add(getB14());                       
		getP().add(Box.createRigidArea(new Dimension(40, 5)));
	}

	public JPanel getP() {
		return p;
	}

	public void setP(JPanel p) {
		this.p = p;
	}
	
	public void setPP1(JPanel P) {
		this.pp1 = P;
	}
	
	public JPanel getPP1() {
		return pp1;
	}

	public void setPP11(JPanel P) {
		this.pp11 = P;
	}
	
	public JPanel getPP11() {
		return pp11;
	}

	public void setPP2(JPanel P) {
		this.pp2 = P;
	}
	
	public JPanel getPP2() {
		return pp2;
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


	public JLabel getL11() {
		return l11;
	}

	public void setL11(JLabel L) {
		this.l11 = L;
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

	public JButton getB3() {
		return b3;
	}

	public void setB3(JButton B) {
		this.b3 = B;
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
