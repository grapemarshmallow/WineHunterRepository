/*******************************************************************************
 * ///////////////////////////////////////////////////////////////////////////////
 *                   
 * Main Class File:  WineHunterApplication.java
 * File:             Keyword.java
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
  * This class characterizes a keyword used to describe wine.
  *
  * @author Orbi Ish-Shalom
  */
public class Keyword {
	
	

	// Data members
	private int id; // the unique id for this keyword
	private String word; // the word for this keyword
	
	/**
     * Constructs a keyword with the given id and word.
     * @param id the unique ID for the keyword
     * @param word the word for the keyword
     */
    public Keyword(int id, String word) {
        this.id = id;
        this.word = word;
    }
	
    /**
     * overridden toString method
     */
    @Override
	public String toString() {
		return this.word.toUpperCase();
	}
    
    /**
     * Returns the id for this Keyword.
     * @return the id
     */
    public int getId() { 
        return id; 
    }
    
    /**
     * Returns the word for this Keyword.
     * @return the word
     */
    public String getWord() { 
        return word; 
    }
 
    /**
     * Changes the id for this keyword to the one given.
     * @param newId the new id  
     */
    public void setId(int newId) { 
        id = newId; 
    }
    
    /**
     * Changes the word for this keyword to the one given.
     * @param newWord the new word  
     */
    public void setWord(String newWord) { 
        word = newWord; 
    }
    
	
}
