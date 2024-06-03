package com.xyoye.subtitle.info;

public class Style {
	
	private static int styleCounter;

	/**
	 * Constructor that receives a String to use a its identifier
	 * 
	 * @param styleName = identifier of this style
	 */
	public Style(String styleName) {
		this.iD = styleName;
	}
	
	/**
	 * Constructor that receives a String with the new styleName and a style to copy
	 * 
	 * @param styleName
	 * @param style
	 */
	public Style(String styleName, Style style) {
		this.iD = styleName;
		this.font = style.font;
		this.fontSize = style.fontSize;
		this.color = style.color;
		this.backgroundColor = style.backgroundColor;
		this.textAlign = style.textAlign;
		this.italic = style.italic;
		this.underline = style.underline;
		this.bold = style.bold;
		
	}

	/* ATTRIBUTES */
	public String iD;
	public String font;
	public String fontSize;
	/**colors are stored as 8 chars long RGBA*/
	public String color;
	public String backgroundColor;
	public String textAlign = "";
	
	public boolean italic;
	public boolean bold;
	public boolean underline;
	
	/* METHODS */
	
	/**
	 * To get the string containing the hex value to put into color or background color
	 * 
	 * @param format supported: "name", "&HBBGGRR", "&HAABBGGRR", "decimalCodedBBGGRR", "decimalCodedAABBGGRR"
	 * @param value RRGGBBAA string
	 * @return
	 */
	public static String getRGBValue(String format, String value){
		String color = null;
		if ("name".equalsIgnoreCase(format)){
			//standard color format from W3C
			if ("transparent".equals(value))
				color = "00000000";
			else if ("black".equals(value))
				color = "000000ff";
			else if ("silver".equals(value))
				color = "c0c0c0ff";
			else if ("gray".equals(value))
				color = "808080ff";
			else if ("white".equals(value))
				color = "ffffffff";
			else if ("maroon".equals(value))
				color = "800000ff";
			else if ("red".equals(value))
				color = "ff0000ff";
			else if ("purple".equals(value))
				color = "800080ff";
			else if ("fuchsia".equals(value))
				color = "ff00ffff";
			else if ("magenta".equals(value))
				color = "ff00ffff ";
			else if ("green".equals(value))
				color = "008000ff";
			else if ("lime".equals(value))
				color = "00ff00ff";
			else if ("olive".equals(value))
				color = "808000ff";
			else if ("yellow".equals(value))
				color = "ffff00ff";
			else if ("navy".equals(value))
				color = "000080ff";
			else if ("blue".equals(value))
				color = "0000ffff";
			else if ("teal".equals(value))
				color = "008080ff";
			else if ("aqua".equals(value))
				color = "00ffffff";
			else if ("cyan".equals(value))
				color = "00ffffff ";
		} else if ("&HBBGGRR".equalsIgnoreCase(format)){
			//hex format from SSA
			if (value.length() == 3){
				color = value.substring(3) +
						value.substring(2, 3) +
						value.substring(1, 2) +
						"ff";
			} else {
				color = value.substring(6) +
						value.substring(4, 5) +
						value.substring(2, 3) +
						"ff";
			}
		} else if ("&HAABBGGRR".equalsIgnoreCase(format) && value.length() > 8){
			//hex format from ASS
			StringBuilder sb = new StringBuilder();
			sb.append(value.substring(8));
			sb.append(value, 6, 7);
			sb.append(value, 4, 5);
			sb.append(value, 2, 3);
			color = sb.toString();
		} else if ("decimalCodedBBGGRR".equalsIgnoreCase(format)){
			//normal format from SSA
			color = Integer.toHexString(Integer.parseInt(value));
			//any missing 0s are filled in
			while(color.length()<6)color="0"+color;
			//order is reversed
			color = color.substring(4)+color.substring(2,4)+color.substring(0,2)+"ff";
		}  else if ("decimalCodedAABBGGRR".equalsIgnoreCase(format)){
			//normal format from ASS
			color = Long.toHexString(Long.parseLong(value));
			//any missing 0s are filled in
			while(color.length()<8)color="0"+color;
			//order is reversed
			color = color.substring(6)+color.substring(4,6)+color.substring(2,4)+color.substring(0,2);
		}
		 return color;
	}

	public static String defaultID() {
		return "default"+styleCounter++;
	}

    @Override
    public String toString() {
        return "Style{" +
                "id='" + iD + '\'' +
                ", font='" + font + '\'' +
                ", fontSize='" + fontSize + '\'' +
                ", color='" + color + '\'' +
                ", backgroundColor='" + backgroundColor + '\'' +
                ", textAlign='" + textAlign + '\'' +
                ", italic=" + italic +
                ", bold=" + bold +
                ", underline=" + underline +
                '}';
    }
}
