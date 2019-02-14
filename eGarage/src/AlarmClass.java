import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;

public class AlarmClass {
	Toolkit toolkit;
	Timer timer;

	public AlarmClass() {
		toolkit = Toolkit.getDefaultToolkit();

	}

	public void start() {
		timer = new Timer();
		timer.schedule(new RemindTask(), 0, 1 * 1000);
	}

	public void stop() {
		timer.cancel();
		timer.purge();
		timer = null;
	}

	class RemindTask extends TimerTask {

		public void run() {
			toolkit.beep();
		}
	}
}