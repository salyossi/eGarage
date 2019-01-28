import java.awt.*;


import javax.swing.*;


public class ExitMachinePanel {

	private JPanel p;
	private JLabel l1, l2;
	private JButton b1;
	private JTextArea ta1;

	public ExitMachinePanel(String l1Text) {

		setP(new JPanel());
		getP().setBorder(BorderFactory.createLineBorder(Color.black));
		getP().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

		setL1(new JLabel(l1Text));
		setL2(new JLabel());
		getL1().setFont(new Font("Ariel", Font.PLAIN, 16));
		setB1(new JButton("כרטיס חניה הוכנס"));
		setTA1(new JTextArea("המחסום סגור - אין רכב ביציאה"));
		getTA1().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		getTA1().setBorder(BorderFactory.createLineBorder(Color.black));

		addComponentsToPane(getP());
	}

	public void addComponentsToPane(Container pane) {

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 0.1;
		c.insets = new Insets(5, 10, 10, 10); // padding
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(getB1(), c);

		c.fill = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(getL1(), c);


		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40; // make this component tall
		c.weightx = 0.2;

		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(getTA1(), c);
		
		c.weightx = 0.36;
		c.gridx = 2;
		c.gridy = 1;
		pane.add(getL2(), c);

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
	
}
