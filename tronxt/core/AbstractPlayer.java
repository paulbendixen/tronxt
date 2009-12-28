package tronxt.core;

public abstract class AbstractPlayer implements Player {

	protected TronBike bike;
	
	public TronBike getBike() {
		return bike;
	}
	
	public void register() {
		
	}
	
	public void die()
	{
		// Play music, non blocking
		for (int i = 0; i < 4*3;i++)
		{
			bike.turnRight();
		}
		bike.stop();
	}
	
	public void win()
	{
		bike.stop();
		// Play victory music
	}
}
