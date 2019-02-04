
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.*;

public class PaymentMachinePanel implements ActionListener {

	private JPanel p, pp1, pp2, p1p2, p2p2, p3p2, pp3;
	private JLabel l1, l2, l3;
	private JButton b1, b2, b3, b4, b5;
	private JTextArea  ta1;
	
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
		getPP2().setLayout(new BoxLayout(getPP2(), BoxLayout.PAGE_AXIS));
		getPP2().setAlignmentX(Component.CENTER_ALIGNMENT);
		getPP2().setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.black));
		setPP3(new JPanel(new GridLayout(1, 0)));
		getP().add(getPP1());
		getP().add(getPP2());
		getP().add(getPP3());
		
		setL1(new JLabel(l1Text));
		getL1().setFont(new Font("Ariel", Font.BOLD, 16));
		setL2(new JLabel("תעריף חניה לשעה = 15 ש''ח"));
		getL2().setFont(new Font("Ariel", Font.PLAIN, 16));
		setL3(new JLabel("תעריף יומי = 40 ש''ח"));
		getL3().setFont(new Font("Ariel", Font.PLAIN, 16));
		setP1P2(new JPanel());
		setP2P2(new JPanel());
		setP3P2(new JPanel());
		getP1P2().add(getL1());
		getP2P2().add(getL2());
		getP3P2().add(getL3());
		getPP2().add(getP1P2());
		getPP2().add(getP2P2());
		getPP2().add(getP3P2());
		
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
			
		setTA1(new JTextArea ("הכנס כרטיס לתשלום"));	
		getTA1().setLineWrap(true);
		getTA1().setWrapStyleWord(true);
		getTA1().setFont(new Font("Ariel", Font.PLAIN, 12));
		getTA1().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getTA1().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JScrollPane sp = new JScrollPane(getTA1());
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
	
	
	public JPanel getP1P2() {
		return p1p2;
	}

	public void setP1P2(JPanel p) {
		this.p1p2 = p;
	}
	
	public JPanel getP2P2() {
		return p2p2;
	}

	public void setP2P2(JPanel p) {
		this.p2p2 = p;
	}
	
	public JPanel getP3P2() {
		return p3p2;
	}

	public void setP3P2(JPanel p) {
		this.p3p2 = p;
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

	public JLabel getL2() {
		return l2;
	}

	public void setL2(JLabel l) {
		this.l2 = l;
	}

	public JLabel getL3() {
		return l3;
	}

	public void setL3(JLabel l) {
		this.l3 = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String arg = e.getActionCommand();

		switch (arg) {
		case "בצע תשלום":
			
			getB3().setEnabled(true);
			
			break;
			
		case "קבל את הכרטיס":
			getB1().setEnabled(false);
			getB3().setEnabled(false);
			
			break;
			
		case "קבל את העודף":
			
			getB5().setEnabled(false);
		}
	

		// pass the event to listeners
		if (myListener != null)
			myListener.onPressedEvent((JButton) e.getSource(), (Hashtable<String, String>) eventArgsHash);

	}
	

	public void setButtonEventListener(ButtonEventListener listener) {
		this.myListener = listener;
	}

}
