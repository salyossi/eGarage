
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.*;

public class PaymentMachinePanel implements ActionListener {

	private JPanel p, pp1, pp2, pp3;
	private JLabel l1;
	private JButton b1, b2, b3, b4, b5;
	private JTextArea ta1;
	
	private ButtonEventListener myListener;
	private Hashtable<String, String> eventArgsHash = new Hashtable<String, String>();

	public PaymentMachinePanel(String l1Text) {

		setP(new JPanel(new GridLayout(0, 3)));
		getP().setBorder(BorderFactory.createLineBorder(Color.black));
		getP().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		setPP1(new JPanel());
		getPP1().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		getPP1().setLayout(new GridLayout(3, 0));
		setPP2(new JPanel());
		getPP2().setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
		setPP3(new JPanel(new GridLayout(1, 0)));
		getP().add(getPP1());
		getP().add(getPP2());
		getP().add(getPP3());
		
		setL1(new JLabel(l1Text));
		getL1().setFont(new Font("Ariel", Font.PLAIN, 16));
		getPP2().add(getL1());
		
		setB1(new JButton("בצע תשלום"));
		getB1().setEnabled(false);
		getB1().addActionListener(this);
		setB3(new JButton("קבל את הכרטיס"));
		getB3().setEnabled(false);
		getB3().addActionListener(this);
		setB5(new JButton("קבל את העודף"));
		getB5().setEnabled(false);
		getB5().addActionListener(this);
			
		getPP1().add(getB1());
		getPP1().add(getB3());
		getPP1().add(getB5());
			
		setTA1(new JTextArea("הכנס כרטיס לתשלום"));	
		getTA1().setLineWrap(true);
		getTA1().setWrapStyleWord(true);
		getTA1().setFont(new Font("Ariel", Font.PLAIN, 14));
		getTA1().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getTA1().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		getTA1().setEditable(false);

		getPP3().add(getTA1());
	}

	public JPanel getP() {
		return p;
	}

	public void setP(JPanel p) {
		this.p = p;
	}


	public JPanel getPP1() {
		return pp1;
	}

	public void setPP1(JPanel p) {
		this.pp1 = p;
	}
	
	public JPanel getPP2() {
		return pp2;
	}

	public void setPP2(JPanel p) {
		this.pp2 = p;
	}
	

	public JPanel getPP3() {
		return pp3;
	}

	public void setPP3(JPanel p) {
		this.pp3 = p;
	}
	
	
	public JButton getB1() {
		return b1;
	}

	public void setB1(JButton b) {
		this.b1 = b;
	}

	public JButton getB2() {
		return b2;
	}

	public void setB2(JButton b) {
		this.b2 = b;
	}

	public JButton getB3() {
		return b3;
	}

	public void setB3(JButton b) {
		this.b3 = b;
	}

	public JButton getB4() {
		return b4;
	}

	public void setB4(JButton b) {
		this.b4 = b;
	}

	public JButton getB5() {
		return b5;
	}

	public void setB5(JButton b) {
		this.b5 = b;
	}

	public JTextArea getTA1() {
		return ta1;
	}

	public void setTA1(JTextArea ta) {
		this.ta1 = ta;
	}

	public JLabel getL1() {
		return l1;
	}

	public void setL1(JLabel l) {
		this.l1 = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
