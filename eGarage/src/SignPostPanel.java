

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class SignPostPanel {

	private JPanel p;
	private JLabel l1;
	
	public SignPostPanel(String l1Text) {
		
		l1 = new JLabel(l1Text);
		l1.setFont(new Font("Ariel", Font.PLAIN, 22));
		setP(new JPanel());
		getP().setBorder(BorderFactory.createLineBorder(Color.black));
		getP().add(l1);
	}

	public JPanel getP() {
		return p;
	}

	public void setP(JPanel p) {
		this.p = p;
	}
	
}
