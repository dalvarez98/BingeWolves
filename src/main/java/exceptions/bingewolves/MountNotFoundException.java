/**
 * 
 */
package exceptions.bingewolves;

/**
 * This class is a custom exception that was made to be thrown when the mount name provided
 * returns null when searched for in the mountMap
 * 
 * @author David Alvarez, 2/20/19
 */
public class MountNotFoundException extends Exception {
	
	public MountNotFoundException() {
		super("That Mount does not exist please check spelling or search for a new mount.");
	}

}
