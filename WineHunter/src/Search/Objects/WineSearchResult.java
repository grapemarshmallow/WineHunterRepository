package Search.Objects;
import WineObjects.*;

public class WineSearchResult {
	private Wine wine;
	private WineReview wineReview;
	
	/**
	 * @param wine
	 * @param wineReview
	 * @param winery
	 */
	public WineSearchResult(Wine wine, WineReview wineReview, Winery winery) {
		this.wine = wine;
		this.wineReview = wineReview;
	}
	
	public Wine getWine() {
		return this.wine;
	}
	public WineReview getWineReview() {
		return this.wineReview;
	}
	
	public void setWine(Wine wine) {
		this.wine = wine;
	}
	public void setWineReview(WineReview wineReview) {
		this.wineReview = wineReview;
	}

	
	
}
