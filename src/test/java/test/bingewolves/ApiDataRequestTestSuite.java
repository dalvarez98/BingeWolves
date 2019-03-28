package test.bingewolves;

import org.hamcrest.collection.IsMapContaining;
import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.junit.runner.JUnitCore;

import controller.bingewolves.ApiDataRequest;
import controller.bingewolves.Mount;
import javafx.scene.text.Text;
import view.bingewolvesui.BingeWolvesUI;

import org.junit.Test;

/**
 * @author David Alvarez, 2/26/19
 * @version 1.0
 */
public class ApiDataRequestTestSuite {
	@After
	public void cleanList() {
	    ApiDataRequest.petList.clear();
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
		
		ApiDataRequest.chrRequestBuilder(realmName, chrName, regionCB);
		
		assertEquals(ApiDataRequest., expectedResult);
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
		ApiDataRequest.mountRequestBuilder(mountName, test);
		
		//assertEquals(expectedResultN, );
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
	 * Test method for {@link controller.bingewolves.ApiDataRequest#petRequestBuilder(java.lang.String, javafx.scene.text.Text)}.
	 * 
	 */
	@Test
	public void testPetRequestBuilder() {
		String petName = "Celestial Calf";
		String expectedResult = "pet/species/68858?";
		String result;
		
		result = classUnderTest.petRequestBuilder(petName);
		assertEquals(result, expectedResult);
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
	 * Test method for {@link controller.bingewolves.ApiDataRequest#formatCharacterData(java.lang.String)}.
	 * 
	 */
	@Test
	public void testFormatCharacterData() {
		//assertEquals("Ronnad", classUnderTest.getChrNameLabel());
	}

	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#formatMountData(java.lang.String)}.
	 * 
	 */
	@Test
	public void testFormatMountData() {
		//assertEquals("Artic Wolf", classUnderTest.getMountNameLabel());
	}

	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#formatPetData(java.lang.String)}.
	 * 
	 */
	@Test
	public void testFormatPetData() {
		//assertEquals("Celestial Calf", classUnderTest.getPetNameLabel());
	}

}
