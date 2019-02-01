

public class EgarageUI {
	
	private State carInEnteranceGate;
	private State state;
	
	public EgarageUI() {
		// create all possible 5 states
		carInEnteranceGate = new CarInEnteranceGate(this);

		// set the initial state of the UI
		state = getCarInEnteranceGate();
		state.SetVisible();
	}
		
	public void goToCarInEnteranceGate() {
		getState().GoToCarInEnteranceGate();
		state.SetVisible();
	}

	public State getCarInEnteranceGate() {
		
		return carInEnteranceGate;
	}

	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	
}
