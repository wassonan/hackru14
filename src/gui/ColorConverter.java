package gui;

public class ColorConverter {

	public static int[] HexToRGB(int newHex){
		int[] rgb = new int[3];
		rgb[0] = newHex >> 16 & 0xff;    
		rgb[1] = newHex >> 8 & 0xff;
		rgb[2] = newHex & 0xff;
		return rgb;
	}

	public static int RGBtoHex(int R, int G, int B){
		return (R << 16) + (G << 8) + (B);
	}

}
