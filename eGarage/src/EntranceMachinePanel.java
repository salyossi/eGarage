import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.*;


public class EntranceMachinePanel implements ActionListener  {

	private JPanel p, pp1, pp2, pp3;
	private JLabel l1;
	private JButton entranceButon, takeParkingTicket;
	private JTextArea entranceMachineConsole;
	private String carIDatEnranceGate;
	
	private ButtonEventListener myListener;
	private Hashtable<String, String> eventArgsHash = new Hashtable<String, String>();


	public EntranceMachinePanel(String l1Text) {

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
		getL1().setFont(new Font("Ariel", Font.BOLD, 16));
		getPP2().add(getL1());
		
		setEntranceButon(new JButton("לחץ לכניסה לחניון"));
		getEntranceButon().setEnabled(false);
		getEntranceButon().addActionListener(this);
		setTakeParkingTicket(new JButton("כרטיס חניה נלקח"));
		getTakeParkingTicket().setEnabled(false);
		getTakeParkingTicket().addActionListener(this);
		
		getPP1().add(getEntranceButon());
		getPP1().add(Box.createRigidArea(new Dimension(40, 2)));
		getPP1().add(getTakeParkingTicket());
	
		setEntranceMachineConsole(new JTextArea("אין רכב בכניסה"));
		getEntranceMachineConsole().setLineWrap(true);
		getEntranceMachineConsole().setWrapStyleWord(true);
		getEntranceMachineConsole().setFont(new Font("Ariel", Font.PLAIN, 14));
		getEntranceMachineConsole().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getEntranceMachineConsole().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		getEntranceMachineConsole().setEditable(false);

		getPP3().add(getEntranceMachineConsole());
	
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
	

	public JButton getEntranceButon() {
		return entranceButon;
	}

	public void setEntranceButon(JButton b) {
		this.entranceButon = b;
	}


	public JButton getTakeParkingTicket() {
		return takeParkingTicket;
	}

	public void setTakeParkingTicket(JButton b) {
		this.takeParkingTicket = b;
	}
	
	public JTextArea getEntranceMachineConsole() {
		return entranceMachineConsole;
	}

	public void setEntranceMachineConsole(JTextArea ta) {
		this.entranceMachineConsole = ta;
	}

	public JLabel getL1() {
		return l1;
	}

	public void setL1(JLabel l) {
		this.l1 = l;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String arg = e.getActionCommand();

		switch (arg) {
		case "כרטיס חניה נלקח":
			getTakeParkingTicket().setEnabled(false);
			getEntranceMachineConsole().setText("המחסום נפתח , נא להיכנס לחניון");
			break;
			
		case "לחץ לכניסה לחניון":
			
			eventArgsHash.put("CarInEntranceGate", getCarIDatEnranceGate());
			
			getEntranceButon().setEnabled(false);
			getTakeParkingTicket().setEnabled(true);
			
			break;
		}

		// pass the event to listeners
		if (myListener != null)
			myListener.onPressedEvent((JButton) e.getSource(), (Hashtable<String, String>) eventArgsHash);

	}
	
	public void setButtonEventListener(ButtonEventListener listener) {
		this.myListener = listener;
	}

	public String getCarIDatEnranceGate() {
		return carIDatEnranceGate;
	}

	public void setCarIDatEnranceGate(String carIDatEnranceGate) {
		this.carIDatEnranceGate = carIDatEnranceGate;
	}

}
