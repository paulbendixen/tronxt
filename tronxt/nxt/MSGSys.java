package tronxt.nxt;

import lejos.nxt.*;

	/** 
	 * Message system class.
	 * Writes debug information to a single source.
	 * This source can be either file, bluetooth or LCD.
	 * The field mode decides wich one is used, mode is:
	 *	'f' for file
	 *	'b' for bluetooth
	 *	'l' for LCD
	 * @author Paul M. Bendixen
	 */
public class MSGSys
{
	static char mode = 'f';
	static boolean connected = false;

	/**
	 * Getter method for the connection status.
	 * Cheks wether the class has been connected or not
	 * @return Returns true if the class has been connected, false otherwise
	 */
	public static boolean isConnected( )
	{
		// maybe add a check to see if the bluetooth device is actually connected
		return connected;
	}

	/**
	 * Sets the mode of operation for the message system.
	 * Sets the mode of operation and disconnects if the system was already connected and mode is different.
	 * Don't forget to connect the device again after changing the mode
	 * @param newMode Modes of operation:
	 *	'f' for file
	 *	'b' for bluetooth
	 *	'l' for LCD
	 * @return Returns true if the mode was succesfully sent, false otherwise
	 */
	public static boolean setMode(char newMode)
	{
		switch(newMode)
		{
			case 'f':
			case 'b':
			case 'l':
				mode = newMode;
				return true;
				break;
			default:
				return false;
		}
	}

	/**
	 * Get the current message model.
	 * Gets the char to describe the current model
	 * @return Returns the character for the model acording to the plan:
	 *	'f' for file
	 *	'b' for bluetooth
	 *	'l' for LCD
	 */
	public static char getMode( )
	{
		return mode;
	}

	/**
	 * Method for writing out debug information.
	 * Writes out debug information to whatever type of medium is set in the mode.
	 * @param message The message to write to the medium will not add linebreaks for the LCD
	 * @return returns the number of characters written to the medium or a non positive value for errors
	 */
	public static int write(String message)
	{
	}

	/**
	 * Method for setting up the connection.
	 * Sets up a connection to the 
	 * @param endpoint The endpoint attribute is used for filename when using file mode,
	 * the bluetooth adress or name for bluetooth.
	 * When using LCD, the string will be ignored
	 * @return Will return 0 on success ,-1 for invalid modes set.
	 */
	public static int connect(String endpoint)
	{
		switch(mode)
		{
			case 'f':
				break;
			case 'b':
				break;
			case 'l':
				connected = true;
				break;
			default:
				return -1;
		}
	}
}
