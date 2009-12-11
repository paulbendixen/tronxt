import lejos.nxt.*; // Do we have to?


public class Player
{
	private Driver drive = new Driver( 100 ); // sets the speed of all types of players equal

	public Player()
	{
	}

	/** wait for all players to signal ready */
	public void waitForReady()
	{
		drive.stop();
		// Get ready signal, if not
		boolean ready = getReady();
		while (!ready)
		{
			sleep(200);
			ready =getReady();
		}
		drive.goForward();
	}

	/** routine for terminating the tron bike */
	public void die()
	{
		drive.stop();
		// Play music, non blocking
		for (int i = 0; i < 4*3;i++)
		{
			drive.turn(true);
		}
		// should we wait for a new game here ?
	}
	
	/** win the game */
	public void win()
	{
		drive.stop();
		// Play victory music
	}
}
