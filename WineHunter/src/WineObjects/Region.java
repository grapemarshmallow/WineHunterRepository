package WineObjects;
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  (name of main application class)
// File:             Region.java
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
  * The Region class defines a region.
  *
  * @author Orbi Ish-Shalom
  */
public class Region {
	
	// Data members
	private int id; // the unique id for this region
	private String name; // the name for this region
	
	/**
     * Constructs a region with the given id and name.
     * @param id the unique ID for the region
     * @param name the name for the region
     * @param province the province the region is located in
     */
    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }
	
    /**
     * Returns the id for this region.
     * @return the id
     */
    public int getId() { 
        return id; 
    }
    
    /**
     * Returns the name for this region.
     * @return the name
     */
    public String getName() { 
        return name; 
    }
    
 
    /**
     * Changes the id for this region to the one given.
     * @param newId the new id  
     */
    public void setId(int newId) { 
        id = newId; 
    }
    
    /**
     * Changes the name for this region to the one given.
     * @param newName the new name  
     */
    public void setName(String newName) { 
        name = newName; 
    }
    
	
}
