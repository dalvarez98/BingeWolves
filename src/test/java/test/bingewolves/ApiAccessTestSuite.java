/**
 * 
 */
package test.bingewolves;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;
import org.powermock.reflect.Whitebox;

import model.bingewolves.ApiAccess;

/**
 * @author David Alvarez, 2/26/19
 * @version 1.0
 */
public class ApiAccessTestSuite {
	private ApiAccess classUnderTest;
	
	@Before
	public void setUp() throws Exception {
		classUnderTest = new ApiAccess();
	}
	/**
	 * Test method for the getAccessToken method in the ApiAccess class
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetAccessToken() throws Exception {
		String result = Whitebox.invokeMethod(classUnderTest, "getAccessToken");
		System.out.println(result);
		
		assertNotNull(result);
	}
	/**
	 * Test method for {@link model.bingewolves.ApiAccess#getRequestedData(java.lang.String)}.
	 * 
	 * @throws Exception 
	 */
	@Test
	public void testGetRequestedData() throws Exception {
		String testURL = "https://us.api.blizzard.com/wow/character/Stormrage/Ronnad?fields=titles%2C%20stats%2C%20mounts%2C%20pets&";
		String result;
		
		result = classUnderTest.getRequestedData(testURL);
		
		assertThat(result, containsString("Blood Champion"));
		assertThat(result, containsString("achievementPoints"));
		assertThat(result, containsString("rage"));
	}

}
