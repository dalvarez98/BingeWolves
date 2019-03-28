package controller.bingewolves;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import exceptions.bingewolves.CharacterNotFoundException;
import exceptions.bingewolves.MountNotFoundException;
import exceptions.bingewolves.PetNotFoundException;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.bingewolves.ApiAccess;
/**
 * This program is the controller class and will build the params for a character or pet request and
 * Format the data that is requested as well as load data from excel files.
 * 
 * @author David Alvarez, 2/17/19
 */
public class ApiDataRequest
{
	private static String params;
	private static String requestUrl;
	private static String apiUrl;
	//Character Profile
	private static String name;
	private static String realm;
	private static String region = "us"; //Default region is the US
	public static String chrImagePath;
	public static String health;
	public static String criticalStrike;
	public static String chrResources;
	public static String haste;
	public static String strength;
	public static String intellect;
	public static String agility;
	public static String mastery;
	public static String stamina;
	public static String versatility;
	public static String title;
	public static String achievPoints;
	public static String mountsCollected;
	public static String petsCollected;
	public static String classNum;
	//Battle Pet Slots
	public static String petSlot1;
	public static String slot1Name;
	public static String slot1L;
	private static String slot1Id;
	public static String petSlot2;
	public static String slot2Name;
	public static String slot2L;
	private static String slot2Id;
	public static String petSlot3;
	public static String slot3Name;
	public static String slot3L;
	private static String slot3Id;
	//Pet Profile
	public static String pet;
	private static String speciesId;
	public static String pDisplayId;
	private static String petDescription;
	private static String petTypeId;
	private static String petHowToObtain;
	//Mount Profile
	public static String mName;
	public static String mDisplayId;
	private static String mountDescription;
	private static String mountHowToObtain;
	
	private static final String locale = "locale=en_US";
	private static String fields;
	private static XSSFSheet sheet;
	private static XSSFRow row;
	public static Params selectedSearch;
	public static List<Mount> mountList = new ArrayList();
	public static List<PetSpeciesDisplay> petList= new ArrayList();
	static ApiAccess apiRequest = new ApiAccess();
	
	public enum Params
	{
		CHARACTER, PET;
	}
	/**
	 * This method will build the params and fields for a character request url and
	 * pass those params to the apiUrlBuilder
	 * 
	 * @param realmName The realm that the character resides in
	 * @param chrName The name of the character to be searched
	 * @param regionCB The region that the character is in NA, EU, etc.
	 */
	public static void chrRequestBuilder(String realmName, String chrName, String regionCB) 
	{
		name = chrName;
		realm = realmName;
		//Checking for spaces between a realm name like Twisting Nether
		Pattern pattern = Pattern.compile("\\s");
		Matcher matcher = pattern.matcher(realm);
		boolean found = matcher.find();
		//If space replace with %20 so that it can be used in a http request
		if (found)
		{
			realm = realm.replaceAll("\\s", "%20");
		}
		region = regionCB;
		//Set selectedSearch = Params.Character so it know which fields to load
		ApiDataRequest.selectedSearch = Params.CHARACTER;
		fields();
		params = "character/" + realm + "/" + name + fields;
		apiUrlBuilder(params);
	}
	/**
	 * This method will search the mountList by the provided mountName
	 * and get the data for the mount.
	 * 
	 * @param mountName The name of the mount that will be searched
	 * @param mountErr The text to be set if the mount does not exist
	 */
	public static void mountRequestBuilder(String mountName, Text mountErr) 
	{
		if (containsMount(mountName))
		{
			for (Mount mount : mountList)
			{
				if (mount.getMountName().equals(mountName))
				{
					mName = mount.getMountName();
					//Removing first char because data came in with apostrophe
					mDisplayId = mount.getDisplayId().substring(1);
					mountDescription = mount.getMountDescription();
					mountHowToObtain = mount.getHowToObtain();
					mountErr.setText("");
				}
			}
		}
		else {
			try {
				throw new MountNotFoundException();
			} catch (MountNotFoundException e) {
				mountErr.setText(e.getMessage());
				return;
			}
		}
	}
	/**
	 * This method will search through the mount list and check if the list contains that
	 * mount
	 * 
	 * @param mountName The mount to be searched for
	 * @return True if the mount was found false if it wasn't 
	 */
	public static boolean containsMount(String mountName) 
	{
		for (Mount mount : mountList) {
		      if (mount.getMountName().equals(mountName)) {
		        return true;
		      }
		    }
		    return false;
	}
	/**
	 * This method will build the params and fields for a pet request url as well as 
	 * the displayId for the image of the pet.
	 * 
	 * @param petName The name of the pet to search
	 * @param petErr The text to be set if pet does not exist
	 */
	public static void petRequestBuilder(String petName, Text petErr) 
	{
		ApiDataRequest.selectedSearch = Params.PET;
		if (containsPet(petName)) 
		{
			for (PetSpeciesDisplay petSD : petList)
			{
				if (petSD.getPetName().equals(petName))
				{
					pet = petSD.getPetName();
					speciesId = petSD.getPetSpeciesId();
					pDisplayId = petSD.getPetDisplayId();
					petErr.setText("");
				}
			}	
		}
		else
		{
			try {
				throw new PetNotFoundException();
			} catch (PetNotFoundException e) {
				petErr.setText(e.getMessage());
				return;
			}
		}
		fields();
		params = "pet/species/" + fields;
		apiUrlBuilder(params);
	}
	/**
	 * This method will search through the pet list and check if the list contains that
	 * pet
	 * 
	 * @param petName The pet to search for
	 * @return True if the pet is found False if it isn't found
	 */
	public static boolean containsPet(String petName) {
		for (PetSpeciesDisplay pet : petList) {
			if (pet.getPetName().equals(petName)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * This method will search for the displayId of the pet provided
	 * 
	 * @param petName The pet to be searched
	 * @return The id of the pet so that I can get the correct image
	 */
	private static String getPetSlotDisplayId(String petName) {
		String id = null;
		for (PetSpeciesDisplay petSD : petList)
		{
			if (petSD.getPetName().equals(petName))
			{
				id = petSD.getPetDisplayId();
			}
		}
		return id;	
	}
	/**
	 * This method will build the url that will be used when requesting data about a character or pet.
	 * 
	 * @param params The parameters to be used in the url 
	 * @return A String of the url that will be used to request data
	 */
	public static String apiUrlBuilder(String params)
	{
		urlSuffix();
		requestUrl = apiUrl + params + locale;
		return requestUrl;
	}
	/**
	 * This method will set the base url suffix for the api request url
	 */
	private static void urlSuffix() 
	{
		apiUrl = "https://" + region + ".api.blizzard.com/wow/";
	}
	/**
	 * This method will set a variable called fields with the fields for either a character or pet 
	 * request
	 */
	private static void fields() 
	{
		switch (ApiDataRequest.selectedSearch)
		{
		case CHARACTER:  
			fields = "?fields=titles%2C%20stats%2C%20mounts%2C%20pets%2C%20petSlots%20&";
			break;
		case PET:
			fields = speciesId + "?";
			break;
		}
	}
	/**
	 * This method will open an excel file called petspeciesid
	 * 
	 * @throws IOException If the specified file is not found
	 */
	private static void loadPetSpeciesIdExcel() throws IOException 
	{
		InputStream inputStream = ApiDataRequest.class.getResourceAsStream("/battlepets.xlsx"); 
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        sheet = workbook.getSheet("Pets");
        inputStream.close();
        workbook.close();
	}
	/**
	 * This method will parse through the pet excel file and separate the data columns into a list 
	 * of object called PetSpeciesDisplay
	 * 
	 */
	public static void getPetList() 
	{
		if (sheet == null) {
			try {
				loadPetSpeciesIdExcel();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//First for loop goes through ever row
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			PetSpeciesDisplay petSD = new PetSpeciesDisplay();
			row = sheet.getRow(i);
			//This nested for loop will get the data in all the columns for every row
			for (int j = row.getFirstCellNum(); j<= row.getLastCellNum(); j++) {
				Cell ce = row.getCell(j);
				if (j == 0) {
					petSD.setPetName(ce.getStringCellValue());
				}
				if (j == 1) {
					petSD.setPetSpeciesId(ce.getStringCellValue());
				}
				if (j == 2) {
					petSD.setPetDisplayId(ce.getStringCellValue());
				}
			}
			petList.add(petSD);
		}
	}
	/**
	 * This method will convert the data in the mount excel file into a list of objects called Mount
	 * 
	 * @throws IOException If the excel file can't be opened
	 */
	public static void getMountList() throws IOException
	{
		InputStream inputStream = ApiDataRequest.class.getResourceAsStream("/mounts.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sh = workbook.getSheet("Sheet 1");
		//First for loop goes through ever row
		for (int i = sh.getFirstRowNum(); i <= sh.getLastRowNum(); i++)
		{
			Mount e = new Mount();
			Row rw = sh.getRow(i);
			//This nested for loop will get the data in all the columns for every row
			for (int j = rw.getFirstCellNum(); j <= rw.getLastCellNum(); j++)
			{
				Cell ce = rw.getCell(j);
				if (j == 0) {
					e.setMountName(ce.getStringCellValue());
				}
				if (j == 1) {
					e.setDisplayId(ce.getStringCellValue());
				}
				if (j == 2) {
					e.setMountDescription(ce.getStringCellValue());
				}
				if (j == 3) {
					e.setHowToObtain(ce.getStringCellValue());
				}
			}
			mountList.add(e);
		}
		workbook.close();
	}
	/**
	 * This method will parse a Json string of the requested character data and store it
	 * into multiple variables that will then be used to populate Text fields in the UI.
	 * 
	 * @param chrErr The text to be set if chr not found
	 */
	public static void formatCharacterData(Text chrErr)
	{
		Gson gson = new Gson();
		try {
			String data = apiRequest.getRequestedData(requestUrl);
			JsonObject body = gson.fromJson(data, JsonObject.class);
			//If the char not found the request returns a Json that has reason in it
			//using that to tell if char was not found
			if (body.has("reason")) {
				try {
					throw new CharacterNotFoundException();
				} catch (CharacterNotFoundException e) {
					chrErr.setText(e.getMessage());
					return;
				}
			}
			chrErr.setText("");
			String thumbNail = body.get("thumbnail").getAsString();
			//This is the image of the char removing the ending of it so that I can get
			//a different version of the image
			chrImagePath = thumbNail.substring(0, thumbNail.length() - 10);
			JsonObject stats = body.get("stats").getAsJsonObject();
			//Stats
			health = stats.get("health").getAsString();
			//Rounding so that I can make it a percent
			int crit = (int) Math.round(stats.get("crit").getAsDouble());
			criticalStrike = Integer.toString(crit);
			chrResources = stats.get("power").getAsString();
			//Rounding so that I can make it a percent
			int hasteD = (int) Math.round(stats.get("haste").getAsDouble());
			haste = Integer.toString(hasteD);
			classNum = body.get("class").getAsString();
			//Checking class num so that I know which stat to parse from the Json
			if (classNum.equals("1") || classNum.equals("2") || classNum.equals("6"))
			{
				strength = stats.get("str").getAsString();
			}
			else if (classNum.equals("5") || classNum.equals("8") || classNum.equals("9"))
			{
				intellect = stats.get("int").getAsString();
			}
			else
			{
				agility = stats.get("agi").getAsString();
			}
			//Rounding so that I can make it a percent
			int masteryD = (int) Math.round(stats.get("mastery").getAsDouble());
			mastery = Integer.toString(masteryD);
			stamina = stats.get("sta").getAsString();
			versatility = stats.get("versatility").getAsString();
			
			JsonArray titles = body.get("titles").getAsJsonArray();
			//Looping so that I can find which title is equipped
			for (JsonElement pa : titles) {
				JsonObject selectedTitle = pa.getAsJsonObject();
				if (selectedTitle.has("selected")) {
					title = selectedTitle.get("name").getAsString();
				}
				else {
					title = "";
				}
			}
			
			achievPoints = body.get("achievementPoints").getAsString();
			JsonObject mCollected = body.get("mounts").getAsJsonObject();
			mountsCollected = mCollected.get("numCollected").getAsString();
			JsonObject pCollected = body.get("pets").getAsJsonObject();
			petsCollected = pCollected.get("numCollected").getAsString();
			JsonArray slots = body.get("petSlots").getAsJsonArray();
			//Looping so that I can get the id of the pet in the slots
			for (JsonElement pa: slots) {
				JsonObject slot = pa.getAsJsonObject();
				if (slot.get("slot").getAsString().equals("1"))
				{
					slot1Id = slot.get("battlePetGuid").getAsString();
				}
				else if (slot.get("slot").getAsString().equals("2"))
				{
					slot2Id = slot.get("battlePetGuid").getAsString();
				}
				else
				{
					slot3Id = slot.get("battlePetGuid").getAsString();
				}
			}
			JsonArray collected = pCollected.get("collected").getAsJsonArray();
			//Looping through all the pets collected and matching the id from the slots
			//so I know which pet is equipped
			for (JsonElement pa: collected) {
				JsonObject battlePetId = pa.getAsJsonObject();
				if (battlePetId.get("battlePetGuid").getAsString().equals(slot1Id))
				{
					JsonObject petLevel = battlePetId.get("stats").getAsJsonObject();
					slot1Name = battlePetId.get("name").getAsString();
					slot1L = petLevel.get("level").getAsString();
					petSlot1 = getPetSlotDisplayId(slot1Name);
				}
				else if (battlePetId.get("battlePetGuid").getAsString().equals(slot2Id))
				{
					JsonObject petLevel = battlePetId.get("stats").getAsJsonObject();
					slot2Name = battlePetId.get("name").getAsString();
					slot2L = petLevel.get("level").getAsString();
					petSlot2 = getPetSlotDisplayId(slot2Name);
				}
				else if (battlePetId.get("battlePetGuid").getAsString().equals(slot3Id))
				{
					JsonObject petLevel = battlePetId.get("stats").getAsJsonObject();
					slot3Name = battlePetId.get("name").getAsString();
					slot3L = petLevel.get("level").getAsString();
					petSlot3 = getPetSlotDisplayId(slot3Name);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method will use the data obtained from the mountRequestBuilder and 
	 * set the text of multiple labels in BingeWolvesUI for the specified mount.
	 * 
	 * @param mountDesc The mount description label to be set
	 * @param mountHTO The howToObtain label to be set
	 */
	public static void formatMountData(Label mountDesc, Label mountHTO)
	{
		mountDesc.setText(mountDescription);
		mountHTO.setText(mountHowToObtain);
	}
	/**
	 * This method will parse a Json string of the requested pet data and 
	 * set the text of multiple labels in BingeWolvesUi.
	 * 
	 * @param petDesc The pet description label to be set
	 * @param petSpec The pet spec label to be set
	 * @param petHTO The howToObtain label to be set
	 */
	public static void formatPetData(Label petDesc, Label petSpec,Label petHTO)
	{
			try {
				String data = apiRequest.getRequestedData(requestUrl);
				JsonElement jElement = new JsonParser().parse(data);
				JsonObject  jObj = jElement.getAsJsonObject();
				petDescription = jObj.get("description").getAsString();
				petTypeId = jObj.get("petTypeId").getAsString();
				petHowToObtain = jObj.get("source").getAsString();
				//Checking the petTypeId so that I know which species it is
				switch (petTypeId) 
				{
				case "0":
					petSpec.setText("Humanoid");
					break;
				case "1":
					petSpec.setText("Dragonkin");
					break;
				case "2":
					petSpec.setText("Flying");
					break;
				case "3":
					petSpec.setText("Undead");
					break;
				case "4":
					petSpec.setText("Critter");
					break;
				case "5":
					petSpec.setText("Magic");
					break;
				case "6":
					petSpec.setText("Elemental");
					break;
				case "7":
					petSpec.setText("Beast");
					break;
				case "8":
					petSpec.setText("Aquatic");
					break;
				case "9":
					petSpec.setText("Mechanical");
				}
				petDesc.setText(petDescription);
				petHTO.setText(petHowToObtain);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}


