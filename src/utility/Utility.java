package utility;

import java.awt.image.BufferedImage;

public class Utility {

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

	public static int distance(int s, int e){

		int [] start = HexToRGB(s);
		int [] end = HexToRGB(e);

		return (int)(Math.sqrt(((start[0] - end[0]) * (start[0] - end[0])) + 
				((start[1] - end[1]) * (start[1] - end[1])) +
				((start[2] - end[2]) * (start[2] - end[2]))));
	} //distance
	
	
	public static void fill(BufferedImage img, boolean[][] points, int color){
		
		for(int i = 0; i < points.length; i++)
			for(int j = 0; j < points[i].length; j++){
				
				
				if(points[i][j]){
					System.out.println("Painting Border (" + i +", " + j + ")");
					img.setRGB(i, j, color);
				}
			} //for
	} //fill
}
