

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class SignPostPanel {

	private JPanel p, pp1;
	private JLabel l1, l2, l3, l4, l5, l6, l7, l8;
	private JTextField t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;

	public SignPostPanel(String l1Text) {

		setP(new JPanel());
		getP().setBorder(BorderFactory.createLineBorder(Color.black));
		getP().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		setPP1(new JPanel());
		getPP1().setBorder(BorderFactory.createLineBorder(Color.black));
		getPP1().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		

		setL1(new JLabel(l1Text));
		setL2(new JLabel("רגיל"));
		setL3(new JLabel("מנוי"));
		setL4(new JLabel("נכה"));
		getL1().setFont(new Font("Ariel", Font.PLAIN, 16));

		setL5(new JLabel("0"));
		setL6(new JLabel("1"));
		setL7(new JLabel("2"));
		setL8(new JLabel("3"));
		
		setT1(new JTextField(23));
		setT2(new JTextField(23));
		setT3(new JTextField(23));
		setT4(new JTextField(23));
		setT5(new JTextField(23));
		setT6(new JTextField(23));
		setT7(new JTextField(23));
		setT8(new JTextField(23));
		setT9(new JTextField(23));
		setT10(new JTextField(23));
		setT11(new JTextField(23));
		setT12(new JTextField(23));

		addComponentsToPane(getP());
		addComponents2ToPane(getPP1());
	}

	public void addComponentsToPane(Container pane) {

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.CENTER;

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 3;
		pane.add(getL1(), c);

		c.fill = GridBagConstraints.CENTER;
		c.weightx = 0.25;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(getL2(), c);
		
		c.fill = GridBagConstraints.CENTER;
		c.weightx = 0.25;
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(getL3(), c);
		
		c.fill = GridBagConstraints.CENTER;
		c.weightx = 0.25;
		c.gridx = 3;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(getL4(), c);
		
		c.fill = GridBagConstraints.CENTER;
		c.weightx = 0.1;
		c.weighty = 0.15;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		pane.add(getL5(), c);
		
		c.fill = GridBagConstraints.CENTER;
		c.weightx = 0.1;
		c.weighty = 0.15;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		pane.add(getL6(), c);
		
		c.fill = GridBagConstraints.CENTER;
		c.weightx = 0.1;
		c.weighty = 0.15;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		pane.add(getL7(), c);
		
		c.fill = GridBagConstraints.CENTER;
		c.weightx = 0.1;
		c.weighty = 0.15;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		pane.add(getL8(), c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 3;
		c.gridheight = 4;
	
		c.insets = new Insets(1, 10, 2, 10); // padding
		//c.anchor = GridBagConstraints.PAGE_END;
		pane.add(getPP1(), c);
		
		

	}


	public void addComponents2ToPane(Container pane) {

		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.CENTER;
		
		c.insets = new Insets(1, 4, 0, 3); // padding
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		pane.add(getT1(), c);
		
		c.fill = GridBagConstraints.CENTER;
		
		c.insets = new Insets(1, 4, 0, 3);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		pane.add(getT2(), c);
		
		c.fill = GridBagConstraints.CENTER;
		
		c.insets = new Insets(1, 3, 0, 3);
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		pane.add(getT3(), c);
		
		c.fill = GridBagConstraints.CENTER;
		
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(getT4(), c);
		
		c.fill = GridBagConstraints.CENTER;
		
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(getT5(), c);
		
		c.fill = GridBagConstraints.CENTER;
		
		
		c.gridx = 2;
		c.gridy = 1;
		c.gridwidth = 1;
		pane.add(getT6(), c);
		
		c.fill = GridBagConstraints.CENTER;
		
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		pane.add(getT7(), c);
		
		c.fill = GridBagConstraints.CENTER;
		
		
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		pane.add(getT8(), c);
		
		c.fill = GridBagConstraints.CENTER;
		
		
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		pane.add(getT9(), c);
		
		c.fill = GridBagConstraints.CENTER;
		
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		pane.add(getT10(), c);
		
		c.fill = GridBagConstraints.CENTER;
		
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		pane.add(getT11(), c);
		
		c.fill = GridBagConstraints.CENTER;
		
		
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 1;
		pane.add(getT12(), c);

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
	

	public JLabel getL4() {
		return l4;
	}

	public void setL4(JLabel l) {
		this.l4 = l;
	}
	

	public JLabel getL5() {
		return l5;
	}

	public void setL5(JLabel l) {
		this.l5 = l;
	}
	

	public JLabel getL6() {
		return l6;
	}

	public void setL6(JLabel l) {
		this.l6 = l;
	}
	

	public JLabel getL7() {
		return l7;
	}

	public void setL7(JLabel l) {
		this.l7 = l;
	}
	

	public JLabel getL8() {
		return l8;
	}

	public void setL8(JLabel l) {
		this.l8 = l;
	}
	
	public JTextField getT1() {
		return t1;
	}

	public void setT1(JTextField t) {
		this.t1 = t;
	}
	
	
	public JTextField getT2() {
		return t2;
	}

	public void setT2(JTextField t) {
		this.t2 = t;
	}
	
	
	public JTextField getT3() {
		return t3;
	}

	public void setT3(JTextField t) {
		this.t3 = t;
	}
	
	
	public JTextField getT4() {
		return t4;
	}

	public void setT4(JTextField t) {
		this.t4 = t;
	}
	
	
	public JTextField getT5() {
		return t5;
	}

	public void setT5(JTextField t) {
		this.t5 = t;
	}
	
	
	public JTextField getT6() {
		return t6;
	}

	public void setT6(JTextField t) {
		this.t6 = t;
	}
	
	
	public JTextField getT7() {
		return t7;
	}

	public void setT7(JTextField t) {
		this.t7 = t;
	}
	
	
	public JTextField getT8() {
		return t8;
	}

	public void setT8(JTextField t) {
		this.t8 = t;
	}
	
	
	public JTextField getT9() {
		return t9;
	}

	public void setT9(JTextField t) {
		this.t9 = t;
	}
	
	
	public JTextField getT10() {
		return t10;
	}

	public void setT10(JTextField t) {
		this.t10 = t;
	}
	
	
	public JTextField getT11() {
		return t11;
	}

	public void setT11(JTextField t) {
		this.t11 = t;
	}
	
	
	public JTextField getT12() {
		return t12;
	}

	public void setT12(JTextField t) {
		this.t12 = t;
	}
}
