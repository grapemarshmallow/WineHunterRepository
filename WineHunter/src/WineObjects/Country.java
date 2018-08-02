package WineObjects;
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  (name of main application class)
// File:             Country.java
// Semester:         Summer 2018
//
//
// Author:           Orbi Ish-Shalom (oishshalom@wisc.edu)
// CS Login:         orbi
// Lecturer's Name:  Hien
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Jennifer Shih
// CS Login:         (partner's login name)
//////////////////////////// 80 columns wide //////////////////////////////////

/**
  * The Country class defines a country.
  *
  * @author Orbi Ish-Shalom
  */
public class Country {
	
	// Data members
	private int id; // the unique id for this country
	private String name; // the name for this country
	
	/**
     * Constructs a country with the given id and name.
     * @param id the unique ID for the country
     * @param name the name for the country
     */
    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }
	
    @Override
	public String toString() {
		return this.name;
	}
    
    /**
     * Returns the id for this country.
     * @return the id
     */
    public int getId() { 
        return id; 
    }
    
    /**
     * Returns the name for this country.
     * @return the name
     */
    public String getName() { 
        return name; 
    }
 
    /**
     * Changes the id for this country to the one given.
     * @param newId the new id  
     */
    public void setId(int newId) { 
        id = newId; 
    }
    
    /**
     * Changes the name for this country to the one given.
     * @param newName the new name  
     */
    public void setName(String newName) { 
        name = newName; 
    }
    
	
}
