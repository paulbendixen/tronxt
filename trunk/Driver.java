import lejos.nxt.Motor;
//import lejos.nxt.*

public class Driver
{
	int speed;
	private Motor motorRight = Motor.A;
	private Motor motorLeft = Motor.B;
	/** Constructor, sets the speed to run at */
	Driver( int speed )
	{
		if (speed >0 && speed < 100)
		{
			this.speed = speed;
		}
		else
		{
			this.speed = 50;
		}
	}

	/** stops the tron bike so it is possible to wait for start, win or die*/
	public void stop()
	{
		motorRight.stop();
		motorLeft.stop();
	}

	/** Turns right if true */
	public void turn(boolean right)
	{
		// Should we use the built in functions in Car.java or just do our own?
	}

	/** instructs the tron bike to go forward
	 * should do this almost all the time */
	public void goForward()
	{
		motorRight.forward();
		motorLeft.forward();
	}
}
