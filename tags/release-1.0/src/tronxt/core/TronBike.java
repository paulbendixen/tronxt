package tronxt.core;

public interface TronBike {
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
	
	/**
	 * 
	 */
	int getTachoCount();
	
}
