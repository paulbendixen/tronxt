package tronxt.core;

public interface Player {
	
	TronBike getBike();
	
	String getName();
	
	void register();
	
	void die();
	void win();
}
