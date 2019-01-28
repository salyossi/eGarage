
import java.awt.*;
import javax.swing.*;

public class PaymentMachinePanel {

	private JPanel p;
	private JLabel l1;
	private JButton b1, b2, b3, b4, b5;
	private JTextArea ta1;

	public PaymentMachinePanel(String l1Text) {

		setP(new JPanel());
		getP().setBorder(BorderFactory.createLineBorder(Color.black));
		getP().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		setL1(new JLabel(l1Text));
		getL1().setFont(new Font("Ariel", Font.PLAIN, 16));
		setB1(new JButton("בצע תשלום"));
		setB3(new JButton("קבל את הכרטיס"));
		setB5(new JButton("קבל את העודף"));
		setTA1(new JTextArea("הכנס כרטיס לתשלום"));
		getTA1().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getTA1().setBorder(BorderFactory.createLineBorder(Color.black));

		addComponentsToPane(getP());
	}

	public void addComponentsToPane(Container pane) {

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 0.2;
		c.insets = new Insets(5, 10, 0, 10); // padding
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(getB1(), c);

		c.fill = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(getL1(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.2;
		c.insets = new Insets(5, 10, 0, 10); // padding
		c.gridx = 2;
		c.gridy = 0;
		pane.add(getB3(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40; // make this component tall
		c.weightx = 0.3;

		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(getTA1(), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0; // reset to default
		c.weighty = 1.0; // request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; // bottom of space
		c.insets = new Insets(0, 10, 5, 10); // padding
		c.gridx = 2; // aligned with button 3
		c.gridwidth = 1; // 2 columns wide
		c.gridy = 2; // third row
		pane.add(getB5(), c);

	}

	public JPanel getP() {
		return p;
	}

	public void setP(JPanel p) {
		this.p = p;
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
}
