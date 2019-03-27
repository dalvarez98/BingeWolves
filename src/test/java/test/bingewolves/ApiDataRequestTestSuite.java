package test.bingewolves;

import org.hamcrest.collection.IsMapContaining;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.runner.JUnitCore;

import controller.bingewolves.ApiDataRequest;
import controller.bingewolves.Mount;

import org.junit.Test;

/**
 * @author David Alvarez, 2/26/19
 * @version 1.0
 */
public class ApiDataRequestTestSuite {
	private ApiDataRequest classUnderTest;
	
	@Before
	public void setUp() throws Exception {
		classUnderTest = new ApiDataRequest();
	}
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#chrRequestBuilder(java.lang.String, java.lang.String, java.lang.String)}.
	 * 
	 */
	@Test
	public void testChrRequestBuilder() {
		String realmName = "Stormrage";
		String chrName = "Ronnad";
		String regionCB = "us";
		String expectedResult = "character/Stormrage/Ronnad?fields=titles%2C%20stats%2C%20mounts%2C%20pets&";
		String result;
		
		result = classUnderTest.chrRequestBuilder(realmName, chrName, regionCB);
		assertEquals(result, expectedResult);
	}

	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#mountRequestBuilder()}.
	 * 
	 */
	@Test
	public void testMountRequestBuilder() {
		String expectedResult = "mount/?";
		String mountName = "Artic Wolf";
		String result;
		
		//result = classUnderTest.mountRequestBuilder(mountName);
		//assertEquals(result, expectedResult);
	}

	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#petRequestBuilder(java.lang.String)}.
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
	 * Test method for {@link controller.bingewolves.ApiDataRequest#apiUrlBuilder()}.
	 * 
	 */
	@Test
	public void testApiUrlBuilder() {
		String expectedURL = "https://us.api.blizzard.com/wow/character/Stormrage/Ronnad?fields=titles%2C%20stats%2C%20mounts%2C%20pets&locale=en_US";
		String realmName = "Stormrage";
		String chrName = "Ronnad";
		String regionCB = "us";
		String result;
		
		classUnderTest.chrRequestBuilder(realmName, chrName, regionCB);
		result = classUnderTest.apiUrlBuilder();
		
		assertEquals(result, expectedURL);
	}
	
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#getPetMap()}.
	 * 
	 */
	@Test
	public void testGetPetMap() {
		Map <String, String> mapResult;
		mapResult = classUnderTest.getPetMap();
		
		//number 68858 is the species id of the pet and each pet has it's own unique species id
		assertThat(mapResult, IsMapContaining.hasEntry("Celestial Calf", "68858"));
	}
	
	/**
	 * Test method for {@link controller.bingewolves.ApiDataRequest#getMountMap()}.
	 * 
	 */
	@Test
	public void testGetMountMap() {
		Map <String, Mount> mapResult;
		//mapResult = classUnderTest.getMountMap();
		
		//number 1166 is the display id of the mount and each mount has a unique display id
		//assertThat(mapResult, IsMapContaining.hasEntry("Artic Wolf", "1166"));
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
