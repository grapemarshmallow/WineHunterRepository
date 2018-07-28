package Core;

import java.awt.Font;

public class Formatting {
	private Font boldFont;
	private Font baseFont;
	private Font headingFont;
	private Font subheadingFont;
	private Font subheadingFont2;
	private Font subheadingFont3;
	private Font headingFontBase;
	private Font subheadingFontBase;
	private Font subheadingFont2Base;
	private Font subheadingFont3Base;
	

	public Formatting() {
		boldFont = new Font("Lucida Grande", Font.BOLD, 10);
		baseFont = new Font("Lucida Grande", Font.PLAIN, 10);
		headingFont = new Font("Lucida Grande", Font.BOLD, 18);
		subheadingFont = new Font("Lucida Grande", Font.BOLD, 16);
		subheadingFont2 = new Font("Lucida Grande", Font.BOLD, 14);
		subheadingFont3 = new Font("Lucida Grande", Font.BOLD, 12);
		headingFontBase = new Font("Lucida Grande", Font.PLAIN, 18);
		subheadingFontBase = new Font("Lucida Grande", Font.PLAIN, 16);
		subheadingFont2Base = new Font("Lucida Grande", Font.PLAIN, 14);
		subheadingFont3Base = new Font("Lucida Grande", Font.PLAIN, 12);
	}
	
	public Font getSubheadingFontBase() {
		return this.subheadingFontBase;
	}


	public Font getSubheadingFont2Base() {
		return this.subheadingFont2Base;
	}


	public Font getSubheadingFont3Base() {
		return this.subheadingFont3Base;
	}

	
	public Font getBoldFont() {
		return this.boldFont;
	}

	public Font getBaseFont() {
		return this.baseFont;
	}

	public Font getHeadingFont() {
		return this.headingFont;
	}

	public Font getSubheadingFont() {
		return this.subheadingFont;
	}

	public Font getSubheadingFont2() {
		return this.subheadingFont2;
	}

	public Font getSubheadingFont3() {
		return this.subheadingFont3;
	}


	public Font getHeadingFontBase() {
		return headingFontBase;
	}

	public void setHeadingFontBase(Font headingFontBase) {
		this.headingFontBase = headingFontBase;
	}
	
	
}
