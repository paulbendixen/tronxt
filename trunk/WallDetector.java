import lejos.nxt.LightSensor;

public class WallDetector extends Thread
{
	LightSensor ls;
	Player play;
	int th;
	int sleepTime;
	public WallDetector(SensorPort port,Player play,int threshold)
	{
		ls = new LightSensor(port);
		this.play = play;
		th = threshold;
	}

	public void run()
	{
		while (ls.getLightValue() > th);
		play.die();
	}
}
