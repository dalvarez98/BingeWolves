package test.bingewolves;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;

import controller.bingewolves.ApiDataRequest;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import org.junit.Test;

/**
 * @author David Alvarez, 2/26/19
 * @version 1.0
 */
public class ApiDataRequestTestSuite {
	@BeforeClass
	public static void initJFX() {
	    Thread t = new Thread("JavaFX Init Thread") {
	        public void run() {
	            Application.launch(AsNonApp.class, new String[0]);
	        }
	    };
	    t.setDaemon(true);
	    t.start();
	}
	@After
	public void cleanList() {
	    ApiDataRequest.petList.clear();
	    ApiDataRequest.mountList.clear();
	}
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#chrRequestBuilder(java.lang.String, java.lang.String, java.lang.String)}.
	 * 
	 */
	@Test
	public void testChrRequestBuilderParams() {
		String realmName = "Stormrage";
		String chrName = "Ronnad";
		String regionCB = "us";
		String expectedResult = "character/Stormrage/Ronnad?fields=titles%2C%20stats%2C%20mounts%2C%20pets%2C%20petSlots%20&";
		String result;
		
		ApiDataRequest.chrRequestBuilder(realmName, chrName, regionCB);
		result = ApiDataRequest.getParams();
		
		assertEquals(result, expectedResult);
	}

	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#mountRequestBuilder(java.lang.String, javafx.scene.text.Text)}.
	 * 
	 */
	@Test
	public void testMountRequestBuilder() {
		String expectedResultN = "Arctic Wolf";
		String expectedResultId = "1166";
		String mountName = "Arctic Wolf";
		Text test = new Text();
		
		try {
			ApiDataRequest.getMountList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ApiDataRequest.mountRequestBuilder(mountName, test);
		
		assertEquals(expectedResultN, ApiDataRequest.mName);
		assertEquals(expectedResultId, ApiDataRequest.mDisplayId);
	}
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#mountRequestBuilder(java.lang.String, javafx.scene.text.Text)}.
	 * 
	 */
	@Test
	public void testMountNotFoundException() {
		String mountName = "Arlokl";
		Text test = new Text();
		
		ApiDataRequest.mountRequestBuilder(mountName, test);
		
		assertEquals(test.getText(), "That Mount does not exist please check spelling or search for a new mount.");
	}
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#containsMount(java.lang.String)}.
	 * 
	 */
	@Test
	public void testContainsMount() {
		boolean expectedTrue = true;
		String mountName = "Arctic Wolf";
		boolean result;
		
		try {
			ApiDataRequest.getMountList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		result = ApiDataRequest.containsMount(mountName);
		
		assertEquals(expectedTrue, result);
	}
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#petRequestBuilder(java.lang.String, javafx.scene.text.Text)}.
	 * 
	 */
	@Test
	public void testPetRequestBuilder() {
		String petName = "Celestial Calf";
		String expectedResultN = "Celestial Calf";
		String expectedResultId = "68858";
		Text test = new Text();
		
		ApiDataRequest.getPetList();
		ApiDataRequest.petRequestBuilder(petName, test);
		
		assertEquals(ApiDataRequest.pet, expectedResultN);
		assertEquals(ApiDataRequest.pDisplayId, expectedResultId);
	}
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#petRequestBuilder(java.lang.String, javafx.scene.text.Text)}.
	 * 
	 */
	@Test
	public void testPetNotFoundException() {
		String petName = "Arlokl";
		Text test = new Text();
		
		ApiDataRequest.petRequestBuilder(petName, test);
		
		assertEquals(test.getText(), "That Pet does not exist please check spelling or enter in a new Pet.");
	}
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#containsPet(java.lang.String)}.
	 * 
	 */
	@Test
	public void testContainsPet() {
		boolean expectedTrue = true;
		String petName = "Celestial Calf";
		boolean result;
		
		ApiDataRequest.getPetList();
		result = ApiDataRequest.containsPet(petName);
		
		assertEquals(expectedTrue, result);
	}
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#apiUrlBuilder()}.
	 * 
	 */
	@Test
	public void testApiUrlBuilder() {
		String expectedURL = "https://us.api.blizzard.com/wow/character/Stormrage/Ronnad?fields=titles%2C%20stats%2C%20mounts%2C%20pets%2C%20petSlots%20&locale=en_US";
		String params = "character/Stormrage/Ronnad?fields=titles%2C%20stats%2C%20mounts%2C%20pets%2C%20petSlots%20&";
		String result;
		
		
		result = ApiDataRequest.apiUrlBuilder(params);
		
		assertEquals(result, expectedURL);
	}
	
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#getPetList()}.
	 * 
	 */
	@Test
	public void testGetPetList() {
		ApiDataRequest.getPetList();
		
		assertEquals(1619, ApiDataRequest.petList.size());
	}
	
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#getMountList()}.
	 */
	@Test
	public void testGetMountList() {
		try {
			ApiDataRequest.getMountList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(733, ApiDataRequest.mountList.size());
	}

	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#formatCharacterData(javafx.scene.text.Text)}.
	 * 
	 */
	@Test
	public void testFormatCharacterData() {
		String realmName = "Stormrage";
		String chrName = "Ronnad";
		String regionCB = "us";
		Text test = new Text();
		String expectedHealth = "95020";
		String expectedTitle = "Blood Champion %s";
		String expectedNumMounts = "139";
		
		ApiDataRequest.chrRequestBuilder(realmName, chrName, regionCB);
		ApiDataRequest.formatCharacterData(test);
		
		assertEquals(ApiDataRequest.health, expectedHealth);
		assertEquals(ApiDataRequest.title, expectedTitle);
		assertEquals(ApiDataRequest.mountsCollected, expectedNumMounts);
	}
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#formatCharacterData(javafx.scene.text.Text)}.
	 */
	@Test
	public void testCharacterNotFoundException() {
		String realmName = "Stormrage";
		String chrName = "Arlock";
		String regionCB = "us";
		Text test = new Text();
		
		ApiDataRequest.chrRequestBuilder(realmName, chrName, regionCB);
		ApiDataRequest.formatCharacterData(test);
		
		assertEquals(test.getText(), "That Character does not exist please check spelling or search for a different character.");
	}
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#formatMountData(javafx.scene.control.Label, javafx.scene.control.Label)}.
	 * 
	 */
	@Test
	public void testFormatMountData() {
		Label resultDesc = new Label();
		Label resultHTO = new Label();
		String expectedDesc = "Once thought to be nearly extinct, this wolf can still occasionally be seen in the company of a few Horde veterans";
		String expectedHTO = "Legacy";
		String petName = "Arctic Wolf";
		Text test = new Text();
		
		try {
			ApiDataRequest.getMountList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ApiDataRequest.mountRequestBuilder(petName, test);
		ApiDataRequest.formatMountData(resultDesc, resultHTO);
		
		assertEquals(resultDesc.getText(), expectedDesc);
		assertEquals(resultHTO.getText(), expectedHTO);
	}
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#formatPetData(javafx.scene.control.Label, javafx.scene.control.Label, javafx.scene.control.Label)}.
	 * 
	 */
	@Test
	public void testFormatPetData() {
		Label resultDesc = new Label();
		Label resultSpec = new Label();
		Label resultHTO = new Label();
		String expectedDesc = "Powerful artillery of the Terran army. The Thor is always the first one in and the last one out!";
		String expectedSpec = "Mechanical";
		String expectedHTO = "Promotion: StarCraft II: Wings of Liberty Collector's Edition";
		String petName = "Mini Thor";
		Text test = new Text();
		
		ApiDataRequest.getPetList();
		ApiDataRequest.petRequestBuilder(petName, test);
		ApiDataRequest.formatPetData(resultDesc, resultSpec, resultHTO);
		
		assertEquals(resultDesc.getText(), expectedDesc);
		assertEquals(resultSpec.getText(), expectedSpec);
		assertEquals(resultHTO.getText(), expectedHTO);
	}

}

