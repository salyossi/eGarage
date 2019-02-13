
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.*;

public class PaymentMachinePanel implements ActionListener {

	private JPanel p, pp1, pp2, p1p2, p2p2, p3p2, pp3;
	private JLabel l1, l2, l3;
	private JButton makePayment, b2, takeParkingTicket, b4, takeChange;
	private JTextArea  paymentMachineConsole;
	
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
		
		setMakePayment(new JButton("בצע תשלום"));
		getMakePayment().setEnabled(false);
		getMakePayment().addActionListener(this);
		setTakeParkingTicket(new JButton("קבל את הכרטיס"));
		getTakeParkingTicket().setEnabled(false);
		getTakeParkingTicket().addActionListener(this);
		setTakeChange(new JButton("קבל את העודף"));
		getTakeChange().setEnabled(false);
		getTakeChange().addActionListener(this);
			
		getPP1().add(getMakePayment());
		getPP1().add(getTakeParkingTicket());
		getPP1().add(getTakeChange());
			
		setPaymentMachineConsole(new JTextArea ("הכנס כרטיס לתשלום"));	
		getPaymentMachineConsole().setLineWrap(true);
		getPaymentMachineConsole().setWrapStyleWord(true);
		getPaymentMachineConsole().setFont(new Font("Ariel", Font.PLAIN, 12));
		getPaymentMachineConsole().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getPaymentMachineConsole().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JScrollPane sp = new JScrollPane(getPaymentMachineConsole());
		getPaymentMachineConsole().setEditable(false);

		getPP3().add(getPaymentMachineConsole());
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
	
	
	public JButton getMakePayment() {
		return makePayment;
	}

	public void setMakePayment(JButton b) {
		this.makePayment = b;
	}

	public JButton getB2() {
		return b2;
	}

	public void setB2(JButton b) {
		this.b2 = b;
	}

	public JButton getTakeParkingTicket() {
		return takeParkingTicket;
	}

	public void setTakeParkingTicket(JButton b) {
		this.takeParkingTicket = b;
	}

	public JButton getB4() {
		return b4;
	}

	public void setB4(JButton b) {
		this.b4 = b;
	}

	public JButton getTakeChange() {
		return takeChange;
	}

	public void setTakeChange(JButton b) {
		this.takeChange = b;
	}

	public JTextArea getPaymentMachineConsole() {
		return paymentMachineConsole;
	}

	public void setPaymentMachineConsole(JTextArea ta) {
		this.paymentMachineConsole = ta;
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
			
			getTakeParkingTicket().setEnabled(true);
			
			break;
			
		case "קבל את הכרטיס":
			getMakePayment().setEnabled(false);
			getTakeParkingTicket().setEnabled(false);
			
			break;
			
		case "קבל את העודף":
			
			getTakeChange().setEnabled(false);
		}
	

		// pass the event to listeners
		if (myListener != null)
			myListener.onPressedEvent((JButton) e.getSource(), (Hashtable<String, String>) eventArgsHash);

	}
	

	public void setButtonEventListener(ButtonEventListener listener) {
		this.myListener = listener;
	}

}
