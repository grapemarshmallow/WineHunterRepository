///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  (name of main application class)
// File:             Taster.java
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
  * The Taster class defines a taster.
  *
  * @author Orbi Ish-Shalom
  */
public class Taster {
	
	// Data members
	private int id;
	private String twitter; // the unique twitter for this taster
	private String name; // the name for this taster
	
	/**
     * Constructs a taster with the given twitter and name.
     * @param twitter the unique twitter for the taster
     * @param name the word for the taster
     */
    public Taster(int id, String twitter, String name) {
    	this.id = id;
        this.twitter = twitter;
        this.name = name;
    }
	
    /**
     * Returns the id for this taster.
     * @return the id
     */
    public int getId() { 
        return id; 
    }
    
    /**
     * Returns the twitter for this taster.
     * @return the twitter
     */
    public String getTwitter() { 
        return twitter; 
    }
    
    /**
     * Returns the name for this taster.
     * @return the name
     */
    public String getName() { 
        return name; 
    }
    
    /**
     * Changes the id for this taster to the one given.
     * @param newId the new id  
     */
    public void setId(int newId) { 
        id = newId; 
    }
    
    /**
     * Changes the twitter for this taster to the one given.
     * @param newTwitter the new twitter  
     */
    public void setTwitter(String newTwitter) { 
       twitter = newTwitter; 
    }
    
    /**
     * Changes the name for this taster to the one given.
     * @param newName the new name  
     */
    public void setName(String newName) { 
        name = newName; 
    }
    
	
}
