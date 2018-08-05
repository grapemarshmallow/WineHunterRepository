/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             variety.java
 * Semester:         Summer 2018
 *
 *
 * Author:           Orbi Ish-Shalom (oishshalom@wisc.edu)
 * CS Login:         orbi
 * Lecturer's Name:  Hien Hguyen
 *
 *                    PAIR PROGRAMMERS COMPLETE THIS SECTION
 *  Pair Partner:     Jennifer Shih
 * //////////////////////////// 80 columns wide //////////////////////////////////
 *******************************************************************************/

package WineObjects;


/**
  * Variety is a class for a grape variety.
  *
  */
public class Variety {
	
	// Data members
	private int id; // the unique id for this variety
	private String name; // the name for this variety
	private int red; // 1 if the variety normally produces red wine
	private int white; // 1 if the variety normally produces white wine
	
	@Override
	public String toString() {
		return this.name;
	}

	/**
     * Constructs a variety with the given id and name.
     * @param id the unique ID for the variety
     * @param name the name for the variety
     */
    public Variety(int id, String name) {
        this.id = id;
        this.name = name;
    }
	
    /**
     * Returns the id for this variety.
     * @return the id
     */
    public int getId() { 
        return id; 
    }
    
    /**
     * Returns the name for this variety.
     * @return the name
     */
    public String getName() { 
        return name; 
    }
    
    /**
     * Returns the red flag for this variety.
     * @return the red flag
     */
    public int getRed() { 
        return red; 
    }
    
    /**
     * Returns the white flag for this variety.
     * @return the white flag
     */
    public int getWhite() { 
        return white; 
    }
 
    /**
     * Changes the id for this variety to the one given.
     * @param newId the new id  
     */
    public void setId(int newId) { 
        id = newId; 
    }
    
    /**
     * Changes the name for this variety to the one given.
     * @param newName the new name  
     */
    public void setName(String newName) { 
        name = newName; 
    }
    
    /**
     * Changes the white flag for this variety to the one given.
     * @param newWhite the new white flag  
     */
    public void setWhite(int newWhite) { 
        if (newWhite == 1) {
        	this.white = 1;
        }
        else {
        	this.white = 0;
        }
    }
    
    /**
     * Changes the red flag for this variety to the one given.
     * @param newRed the new red flag  
     */
    public void setRed(int newRed) { 
        if (newRed == 1) {
        	this.red = 1;
        }
        else {
        	this.red = 0;
        }
    }
	
}
