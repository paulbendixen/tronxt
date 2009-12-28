package tronxt.core;

public interface TronBike {
	/**
	 * Add the event handler that is to be invoked, when the bike crashes.
	 * 
	 * @param handler The event handler.
	 */
	void addCrashHandler(CrashHandler handler);
	
	/**
	 * Makes the bikes go forward.
	 */
	void start();
	
	/**
	 * Makes the bike stop.
	 */
	void stop();
	
	/**
	 * Makes the bike turn left. 
	 */
	void turnLeft();
	
	/**
	 * Makes the bike turn right.
	 */
	void turnRight();
}
