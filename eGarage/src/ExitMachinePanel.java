import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.*;

public class ExitMachinePanel implements ActionListener {

	private JPanel p, pp1, pp2, pp3;
	private JLabel l1, l2;
	private JButton b1;
	private JTextArea exitMachineConsole;

	private ButtonEventListener myListener;
	private Hashtable<String, String> eventArgsHash = new Hashtable<String, String>();

	public ExitMachinePanel(String l1Text) {

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

		setB1(new JButton("כרטיס חניה הוכנס"));
		getB1().setEnabled(false);
		getB1().addActionListener(this);

		getPP1().add(getB1());

		setExitMachineConsole(new JTextArea("המחסום סגור - אין רכב ביציאה"));
		getExitMachineConsole().setLineWrap(true);
		getExitMachineConsole().setWrapStyleWord(true);
		getExitMachineConsole().setFont(new Font("Ariel", Font.PLAIN, 14));
		getExitMachineConsole().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getExitMachineConsole().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		getExitMachineConsole().setEditable(false);

		getPP3().add(getExitMachineConsole());

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

	public JTextArea getExitMachineConsole() {
		return exitMachineConsole;
	}

	public void setExitMachineConsole(JTextArea ta) {
		this.exitMachineConsole = ta;
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
