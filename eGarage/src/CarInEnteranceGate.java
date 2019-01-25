import java.util.*;
import java.awt.*;
import javax.swing.*;

public class CarInEnteranceGate extends MainFrame implements State, ButtonEventListener {

	private EgarageUI egarageUI;
	private StateHeader stateHeaderPanel;
	private SignPostPanel signPostPanel;
	private ParkingUseMapPanel parkingUseMapPanel;
	private EntranceMachinePanel entranceMachinePanel;
	private ExitMachinePanel exitMachinePanel;
	private PaymentMachinePanel paymentMachinePanel;
	private VirtualButtonsPanel virtualButtonsPanel;
	
	
	public CarInEnteranceGate(EgarageUI egarageUI) {
		
		this.egarageUI = egarageUI;
		stateHeader = setStateHeader("רכב עומד בכניסה");
		signPost = setSignPost("תצוגת שילוט");
		parkingUseMap = setParkingUseMap("חישני החנייה");
		entranceMachine = setEntranceMachine("מכונת הכניסה");
		exitMachine = setExitMachine("מכונת היציאה");
		paymentMachine = setPaymentMachine("מכונת התשלום");
		virtualButtons = setVirtualButtons();
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
	public JPanel setStateHeader(String l1Text) {
		stateHeaderPanel = new StateHeader(l1Text);
		return stateHeaderPanel.getP();
	}


	@Override
	public JPanel setSignPost(String l1Text) {
		signPostPanel = new SignPostPanel(l1Text);
		return signPostPanel.getP();
	}

	@Override
	public JPanel setParkingUseMap(String l1Text) {		
		parkingUseMapPanel = new ParkingUseMapPanel();
		parkingUseMapPanel.getL1().setText(l1Text);
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
	public JPanel setVirtualButtons() {
		virtualButtonsPanel = new VirtualButtonsPanel();
		virtualButtonsPanel.setButtonEventListener(this);
		return virtualButtonsPanel.getP();
	}

	@Override
	public void onPressedEvent(JButton btn, Hashtable argv) {
		String arg = btn.getActionCommand();

		if (arg.equals("רכב זוהה ע''י המצלמה")) {		
			goToCarInEnteranceGate();
		}
		else if (arg.equals("רכב נכנס לחניה")) {		
			goToCarEnteredParking();
		}
		else if (arg.equals("רכב יצא מחניה")) {		
			goToCarExitFromParking();
		}
		
		egarageUI.getState().setVisible();
	}


}