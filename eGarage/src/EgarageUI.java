

public class EgarageUI {
	
	private State carInEnteranceGate;
	private State carInExitGate;
	private State carExitFromParking;
	private State driverPaying;
	
	private State state;
	
	public EgarageUI() {
		// create all possible 5 states
		carInEnteranceGate = new CarInEnteranceGate(this);
		carInExitGate = new CarInExitGate(this);
		carExitFromParking = new CarExitFromParking(this);
		driverPaying = new DriverPaying(this);
		
		// set the initial state of the UI
		state = getCarInEnteranceGate();
		state.SetVisible();
	}
		
	public void goToCarInEnteranceGate() {
		getState().GoToCarInEnteranceGate();
		state.SetVisible();
	}
		
	public void goToCarInExitGate() {
		getState().GoToCarInExitGate();
		state.SetVisible();
	}
	
	public void goToCarExitFromParking() {
		getState().GoToCarExitFromParking();
		state.SetVisible();
		
	}
	
	public void goToDriverPaying() {
		getState().GoToDriverPaying();
		state.SetVisible();
	}
	
	public State getCarInEnteranceGate() {
		
		return carInEnteranceGate;
	}

	public State getCarInExitGate() {
		return carInExitGate;
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
