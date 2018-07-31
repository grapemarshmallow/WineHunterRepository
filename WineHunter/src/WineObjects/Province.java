package WineObjects;
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  (name of main application class)
// File:             Province.java
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
  * The Province class defines a province.
  *
  * @author Orbi Ish-Shalom
  */
public class Province {
	

	// Data members
	private int id; // the unique id for this province
	private String name; // the name for this province
	private Country country;
	
	public Country getCountry() {
		return this.country;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	/**
     * Constructs a province with the given id and name.
     * @param id the unique ID for the province
     * @param name the name for the province
     */
    public Province(int id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
	
    /**
     * Returns the id for this province.
     * @return the id
     */
    public int getId() { 
        return id; 
    }
    
    /**
     * Returns the name for this province.
     * @return the name
     */
    public String getName() { 
        return name; 
    }
    
 
    /**
     * Changes the id for this province to the one given.
     * @param newId the new id  
     */
    public void setId(int newId) { 
        id = newId; 
    }
    
    /**
     * Changes the name for this province to the one given.
     * @param newName the new name  
     */
    public void setName(String newName) { 
        name = newName; 
    }
    
	
}
