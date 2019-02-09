public interface AlarmEventListener {
	
	void raseAlarm(int Level, int Slot);
	void checkAlarm(int Level, int Slot);
	void reportAlarm(String AlarmMessage);
	
}