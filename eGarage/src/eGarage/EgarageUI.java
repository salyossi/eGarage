package eGarage;

public class EgarageUI {
	
	private State carInEnteranceGate;
	private State carInExitGate;
	private State carEnteredParking;
	private State carExitFromParking;
	private State driverPaying;
	
	private State state;
	
	public EgarageUI() {
		// create all possible 5 states
		carInEnteranceGate = new CarInEnteranceGate(this);
		//carInExitGate = new carInExitGate(this);
		//carEnteredParking = new carEnteredParking(this);
		//carExitFromParking = new carExitFromParking(this);
		//driverPaying = new driverPaying(this);
		
		// set the initial state of the UI
		state = getCarInEnteranceGate();
		state.setVisible();
	}
		
	public void goToCarInEnteranceGate() {
		getState().goToCarInEnteranceGate();
		state.setVisible();
	}
		
	public void goToCarInExitGate() {
		getState().goToCarInExitGate();
		state.setVisible();
	}
	
	public void goToCarEnteredParking() {
		getState().goToCarEnteredParking();
		state.setVisible();
	}
	
	public void goToCarExitFromParking() {
		getState().goToCarExitFromParking();
		state.setVisible();
		
	}
	
	public void goToDriverPaying() {
		getState().goToDriverPaying();
		state.setVisible();
	}
	
	public State getCarInEnteranceGate() {
		return carInEnteranceGate;
	}

	public State getCarInExitGate() {
		return carInExitGate;
	}
	
	public State getCarEnteredParking() {
		return carEnteredParking;
	}
		
	public State getCarExitFromParking() {
		return carExitFromParking;
	}
		
	public State getDriverPaying() {
		return driverPaying;
	}

	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	
}
