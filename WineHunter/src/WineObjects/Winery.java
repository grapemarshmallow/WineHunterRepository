package WineObjects;
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  (name of main application class)
// File:             Winery.java
// Semester:         Summer 2018
//
//
// Author:           Orbi Ish-Shalom (oishshalom@wisc.edu)
// CS Login:         orbi
// Lecturer's Name:  Hieng
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Jennifer Shih
// CS Login:         (partner's login name)
//////////////////////////// 80 columns wide //////////////////////////////////

/**
  * The Winery class defines a winery.
  *
  * @author Orbi Ish-Shalom
  */
public class Winery {
	
	
	// Data members
	private int id; // the unique id for this winery
	private String name; // the name for this winery
	Region region;
	Province province;
	
	/**
     * Constructs a winery with the given id and name.
     * @param id the unique ID for the winery
     * @param name the name for the winery
     */
    public Winery(int id, String name) {
        this.id = id;
        this.name = name;

    }
    
    /**
	 * @param id
	 * @param name
	 * @param region
	 * @param province
	 * @param country
	 */
	public Winery(int id, String name, Region region, Province province) {
		this.id = id;
		this.name = name;
		this.region = region;
		this.province = province;

	}

	
    /**
     * Returns the id for this winery.
     * @return the id
     */
    public int getId() { 
        return id; 
    }
    
    /**
     * Returns the name for this winery.
     * @return the name
     */
    public String getName() { 
        return name; 
    }
    

    /**
     * Changes the id for this winery to the one given.
     * @param newId the new id  
     */
    public void setId(int newId) { 
        id = newId; 
    }
    
    /**
     * Changes the name for this winery to the one given.
     * @param newName the new name  
     */
    public void setName(String newName) { 
        name = newName; 
    }

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}


    

}
