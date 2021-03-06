

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.*;

public abstract class MainFrame extends JFrame {

	protected JFrame f = new JFrame("                                                               "
			+ "                                                                       "
			+ "                                                        החניון הממוחשב של יוסי, סשה וזאב ");
	protected JPanel p1 = new JPanel();
	protected JPanel p2 = new JPanel();
	private Dimension farmeDim;
	private int width; // window width
	private int height; // window height

	protected JPanel stateHeader; // state header JPanel always on top
	protected JPanel signPost; // sign post JPanel always on top
	protected JPanel parkingUseMap; // Parking Use Map JPanel always under sign post
	protected JPanel entranceMachine; // Entrance Machine JPanel
	protected JPanel exitMachine; // Exit Machine JPanel
	protected JPanel paymentMachine; // Payment Machine JPanel
	protected JPanel virtualButtons; // JPanel of buttons for the interaction between the system and drivers

	public abstract JPanel setUIHeader(String l1Text); // will be implemented by each state class
	
	public abstract JPanel setSignPost(String l1Text); // will be implemented by each state class

	public abstract JPanel setParkingUseMap(String l1Text); // will be implemented by each state class

	public abstract JPanel setEntranceMachine(String l1Text); // will be implemented by each state class

	public abstract JPanel setExitMachine(String l1Text); // will be implemented by each state class

	public abstract JPanel setPaymentMachine(String l1Text); // will be implemented by each state class

	public abstract JPanel setVirtualButtons(); // will be implemented by each state class

	public void DrawFrame() {

		// Actual screen size
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		// Take 80% of screen size for initial start of the software
		farmeDim = getRationalDimention(dim, 0.9, 0.9);
		// window dimensions
		width = farmeDim.width;
		height = farmeDim.height;
		
		// set orientation
		f.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


		// set the Frame size
		f.setSize(width, height);
		// Locate the software at the center of the screen
		f.setLocation(dim.width / 2 - width / 2, dim.height / 2 - height / 2);
		ImageIcon img = new ImageIcon("calc.png");
		f.setIconImage(img.getImage());
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new FlowLayout());
		p1.setLayout(new FlowLayout());
		p2.setLayout(new FlowLayout());
		p1.setPreferredSize(getRationalDimention(new Dimension(width, height), 0.30, 0.97));
		p2.setPreferredSize(getRationalDimention(new Dimension(width, height), 0.68, 0.97));
		f.add(p1);
		f.add(p2);
		

		// set panels preferred sizes to get the correct layout
		
		virtualButtons.setPreferredSize(getRationalDimention(new Dimension(width, height), 0.30, 0.95));
		stateHeader.setPreferredSize(getRationalDimention(new Dimension(width, height), 0.68, 0.06));
		signPost.setPreferredSize(getRationalDimention(new Dimension(width, height), 0.68, 0.19));
		parkingUseMap.setPreferredSize(getRationalDimention(new Dimension(width, height), 0.68, 0.17));
		entranceMachine.setPreferredSize(getRationalDimention(new Dimension(width, height), 0.68, 0.16));
		exitMachine.setPreferredSize(getRationalDimention(new Dimension(width, height), 0.68, 0.16));
		paymentMachine.setPreferredSize(getRationalDimention(new Dimension(width, height), 0.68, 0.178));
		

		// add panels to frame
		p1.add(virtualButtons);
		p2.add(stateHeader);
		p2.add(signPost);
		p2.add(parkingUseMap);
		p2.add(entranceMachine);
		p2.add(exitMachine);
		p2.add(paymentMachine);
		f.pack();
		
	}

	private Dimension getRationalDimention(Dimension OrigDim, double Wratio, double Hratio) {

		int width = (int) (OrigDim.width * Wratio);
		int height = (int) (OrigDim.height * Hratio);
		return (new Dimension(width, height));
	}
}
