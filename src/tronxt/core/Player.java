package tronxt.core;

public interface Player {
	
	TronBike getBike();
	
	String getName();
	
	void register();
	
	void start();
	void die();
	void win();
}
