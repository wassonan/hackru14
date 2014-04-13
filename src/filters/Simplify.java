/* Wasson An
 * This filter simplifies all the colors in the image into a simple color
 */

package filters;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import utility.Utility;

public class Simplify {

	final static int[] colors = {Color.WHITE.getRGB(), Color.BLUE.getRGB(),
			Color.RED.getRGB(), Color.YELLOW.getRGB(), Color.BLACK.getRGB(), 
			Color.GREEN.getRGB()}; 

	//simplifies the picture colors
	public static void simplify(BufferedImage img){

		//iterates over the selected area pixelateing it
		for(int i = 0; i < img.getWidth() - 1; i++){
			for(int j = 0; j < img.getHeight() - 1; j++){

				int minDistance = Integer.MAX_VALUE; //the current min distance
				int color = 0; // the current closest color
				
				//finds the closest color
				for(int k = 0; k < colors.length; k++){
					
					int dis = Utility.distance(colors[k], img.getRGB(i, j));
					
					if(dis < minDistance){
						
						minDistance = dis;
						color = colors[k];
					} //if
				} //for
				
				img.setRGB(i, j, color);
			} //for
		} //for
	} //simplify
	
	
	//main method to test simplify function
		public static void main(String[] args){

			BufferedImage img = null;
			try {
				img = ImageIO.read(new File("TestImageFour.jpg"));
			} catch (IOException e) {

				System.out.println("Image Not Found");
				e.printStackTrace();
			}//try catch 

//			Pixelate.pixilate(img, 3);
			simplify(img);
//			Pixelate.pixilate(img, 3);
//			simplify(img);
			boolean[][] points = Border.getBorderLocation(img);
//			Pixelate.pixilate(img, 9);
			Border.drawBorder(img, points);
			
			File output = new File("TestOut.jpg");

			try {
				ImageIO.write(img, "jpg", output);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //try catch
		} //main
} //Simplify
