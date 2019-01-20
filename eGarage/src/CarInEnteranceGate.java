

import java.util.*;
import java.awt.*;
import javax.swing.*;

public class CarInEnteranceGate extends MainFrame implements State {

	private EgarageUI egarageUI;
	private SignPostPanel signPostPanel;
	private ParkingUseMapPanel parkingUseMapPanel;
	private EntranceMachinePanel entranceMachinePanel;
	private ExitMachinePanel exitMachinePanel;
	private PaymentMachinePanel paymentMachinePanel;
	private VirtualButtonsPanel virtualButtonsPanel;
	
	
	public CarInEnteranceGate(EgarageUI egarageUI) {
		
		this.egarageUI = egarageUI;
		signPost = setSignPost("תצוגת שילוט" + " - " + "רכב עומד בכניסה");
		parkingUseMap = setParkingUseMap("חישני החנייה" + " - " + "רכב עומד בכניסה");
		entranceMachine = setEntranceMachine("מכונת הכניסה" + " - " + "רכב עומד בכניסה");
		exitMachine = setExitMachine("מכונת היציאה" + " - " + "רכב עומד בכניסה");
		paymentMachine = setPaymentMachine("מכונת התשלום" + " - " + "רכב עומד בכניסה");
		virtualButtons = setVirtualButtons("כפתורי בקרה" + " - " + "רכב עומד בכניסה");
		DrawFrame();
	
	}
	
	
	@Override
	public void goToCarInEnteranceGate() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getCarInEnteranceGate());
	}

	@Override
	public void goToCarInExitGate() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getCarInExitGate());
	}

	@Override
	public void goToCarEnteredParking() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getCarEnteredParking());
	}

	@Override
	public void goToCarExitFromParking() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getCarExitFromParking());		
	}

	@Override
	public void goToDriverPaying() {
		f.setVisible(false);
		egarageUI.setState(egarageUI.getDriverPaying());
	}

	@Override
	public void setVisible() {
		f.setVisible(true);
	}

	@Override
	public JPanel setSignPost(String l1Text) {
		signPostPanel = new SignPostPanel(l1Text);
		return signPostPanel.getP();
	}

	@Override
	public JPanel setParkingUseMap(String l1Text) {
		parkingUseMapPanel = new ParkingUseMapPanel(l1Text);		
		return parkingUseMapPanel.getP();
	}

	@Override
	public JPanel setEntranceMachine(String l1Text) {
		entranceMachinePanel = new EntranceMachinePanel(l1Text);
		return entranceMachinePanel.getP();
	}

	@Override
	public JPanel setExitMachine(String l1Text) {
		exitMachinePanel = new ExitMachinePanel(l1Text);
		return exitMachinePanel.getP();
	}

	@Override
	public JPanel setPaymentMachine(String l1Text) {
		paymentMachinePanel = new PaymentMachinePanel(l1Text);
		return paymentMachinePanel.getP();
	}

	@Override
	public JPanel setVirtualButtons(String l1Text) {
		virtualButtonsPanel = new VirtualButtonsPanel(l1Text);
		return virtualButtonsPanel.getP();
	}

}
