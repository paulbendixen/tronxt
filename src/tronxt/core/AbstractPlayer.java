package tronxt.core;

public abstract class AbstractPlayer implements Player {

	protected TronBike bike;
	protected String name;
	
	protected AbstractPlayer(String name, TronBike bike) {
		this.bike = bike;
		this.name = name;
	}
	
	@Override
	public TronBike getBike() {
		return bike;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void register() {
		
	}
	
	@Override
	public void die()
	{
		// Play music, non blocking
		for (int i = 0; i < 4*3;i++)
		{
			bike.turnRight();
		}
		bike.stop();
	}
	
	@Override
	public void win()
	{
		bike.stop();
		// Play victory music
	}
}
