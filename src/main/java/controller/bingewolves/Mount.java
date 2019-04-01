package controller.bingewolves;

/**
 * This program is a get and set program and will get the mount name, display id, description, 
 * and the how to obtain from the mount excel file to be stored into a map.
 * 
 * @author David Alvarez, 2/26/19
 */
public class Mount 
{
	private String mountName;
	private long displayId;
	private String mountDescription;
	private String howToObtain;
	
	public Mount(){}
	
	public Mount(String mountName, long displayId, String mountDescription, String howToObtain) {
		super();
		this.mountName = mountName;
		this.displayId = displayId;
		this.mountDescription = mountDescription;
		this.howToObtain = howToObtain;
	}
	/**
	 * @return the mountName
	 */
	public String getMountName() {
		return mountName;
	}
	/**
	 * @param mountName the mountName to set
	 */
	public void setMountName(String mountName) {
		this.mountName = mountName;
	}
	/**
	 * @return the displayId
	 */
	public long getDisplayId() {
		return displayId;
	}
	/**
	 * @param displayId the displayId to set
	 */
	public void setDisplayId(long displayId) {
		this.displayId = displayId;
	}
	/**
	 * @return the description
	 */
	public String getMountDescription() {
		return mountDescription;
	}
	/**
	 * @param description the description to set
	 */
	public void setMountDescription(String mountDescription) {
		this.mountDescription = mountDescription;
	}
	/**
	 * @return the howToObtain
	 */
	public String getHowToObtain() {
		return howToObtain;
	}
	/**
	 * @param howToObtain the howToObtain to set
	 */
	public void setHowToObtain(String howToObtain) {
		this.howToObtain = howToObtain;
	}
}
