/**
 * 
 */
package exceptions.bingewolves;

/**
 * This class is a custom exception that was made to be thrown when the character
 * provided is searched for using an http request returns an error 404 code 
 * 
 * @author David Alvarez, 2/20/19
 */
public class CharacterNotFoundException extends Exception {
	
	public CharacterNotFoundException()	{
		super("That Character does not exist please check spelling or search for a different character.");
	}

}

