import lejos.nxt.TouchSensor;

public class Bumper extends Thread
{
	TouchSensor ts;

	public Bumper(SensorPort port,Player play)
	{
		ts= new TouchSensor(port);
	}

	public void run()
	{
		while (!ts.isPressed()) ;
		play.die();
	}
}
