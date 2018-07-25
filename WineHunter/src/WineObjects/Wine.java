import java.util.ArrayList;
import java.util.List;

public class Wine {
	
	//Instance Variables
		int wineId;
		int vintage;
		float price;
		String name;
		Winery winery;
		List<Integer> keywords = new ArrayList<Integer>();
		List<Variety> varieties = new ArrayList<Variety>();
	
	/**
	 * @param wineId
	 * @param vintage
	 * @param price
	 * @param name
	 * @param winery
	 */
	public Wine(int wineId, int vintage, float price, String name, Winery winery) {
		this.wineId = wineId;
		this.vintage = vintage;
		this.price = price;
		this.name = name;
		this.winery = winery;
	}

	/**
	 * @return the wineId
	 */
	public int getWineId() {
		return wineId;
	}

	/**
	 * @param wineId the wineId to set
	 */
	public void setWineId(int wineId) {
		this.wineId = wineId;
	}

	/**
	 * @return the vintage
	 */
	public int getVintage() {
		return vintage;
	}

	/**
	 * @param vintage the vintage to set
	 */
	public void setVintage(int vintage) {
		this.vintage = vintage;
	}

	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the winery
	 */
	public Winery getWinery() {
		return winery;
	}

	/**
	 * @param winery the winery to set
	 */
	public void setWinery(Winery winery) {
		this.winery = winery;
	}

	/**
	 * @return the keywords
	 */
	public List<Integer> getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(List<Integer> keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the varieties
	 */
	public List<Variety> getVarieties() {
		return varieties;
	}

	/**
	 * @param varieties the varieties to set
	 */
	public void setVarieties(List<Variety> varieties) {
		this.varieties = varieties;
	}
	
	
	
	
}
