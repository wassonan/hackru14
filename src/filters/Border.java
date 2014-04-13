/* Wasson An
 * This class adds a border to an image where different colors touch
 */

package filters;

import java.awt.image.BufferedImage;

import utility.Utility;

public class Border {

	//adds a border between areas of different color
	public static void border(BufferedImage img){

		drawBorder(img, getBorderLocation(img));
	} //border
	
	//returns the array that represents the border location
	public static boolean[][] getBorderLocation(BufferedImage img){
		
		boolean[][] points = new boolean[img.getWidth()][img.getHeight()];

		for(int i = 0; i < img.getWidth(); i++){
			for(int j = 0; j < img.getHeight(); j++){

				System.out.println("Calculating Border (" + i +", " + j + ")");

				int color = img.getRGB(i, j);

				if(i > 0){

					if(j > 0){

						if(color != img.getRGB(i - 1, j - 1))
							points[i][j] = true;
					}//if

					if(j < img.getHeight() - 1){

						if(color != img.getRGB(i - 1, j + 1))
							points[i][j] = true;
					} //if

					if(color != img.getRGB(i - 1, j))
						points[i][j] = true;
				} //if

				if(i < img.getWidth() - 1){

					if(j > 0){

						if(color != img.getRGB(i + 1, j - 1))
							points[i][j] = true;
					}//if

					if(j < img.getHeight() - 1){

						if(color != img.getRGB(i + 1, j + 1))
							points[i][j] = true;
					} //if

					if(color != img.getRGB(i + 1, j))
						points[i][j] = true;
				} //if

				if(j > 0)
					if(color != img.getRGB(i, j - 1))
						points[i][j] = true;

				if(j < img.getHeight() - 1)
					if(color != img.getRGB(i, j + 1))
						points[i][j] = true;
			} //for
		} //for		
		
		return points;
	} //getBorderLocation
	
	
	//paints the border based on the passed in locations
	public static void drawBorder(BufferedImage img, boolean[][] points){
		
		Utility.fill(img, points, 0);
	} //drawBorder
} //Border
