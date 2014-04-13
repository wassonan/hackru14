/* Wasson An
 * This class pixlilated a given image within the given bounds
 */

package filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import utility.Utility;


public class Pixelate {

	//pixelates the passed in picture
	public static void pixilate(BufferedImage img, int pixSize){

		//iterates over the selected area pixilateing it
		for(int i = 0; i < img.getWidth() - pixSize; i += pixSize){
			for(int j = 0; j < img.getHeight() - pixSize; j += pixSize){

				int[] rgb = new int[3]; //red, blue, and green color values
				int color; //the average color of the pixel

				//iterate over each pixel adding up the rgb values
				for(int x = i; x < i + pixSize; x++){
					for(int y = j; y < j + pixSize; y++){

						System.out.println("(" + x + ", " + y + ")");
						
						int[] pix = Utility.HexToRGB(img.getRGB(x, y));

						for(int k = 0; k < 3; k++)
							rgb[k] += pix[k];
					} //for
				} //for

				//calculate the average color
				for(int k = 0; k < 3; k++)
					rgb[k] /= (pixSize * pixSize); 

				color = Utility.RGBtoHex(rgb[0], rgb[1], rgb[2]);
				
				//go through the pixel setting the color to the average color
				for(int x = i; x < i + pixSize; x++)
					for(int y = j; y < j + pixSize; y++)
						img.setRGB(x, y, color);
			} //for
		} //for
	} //pixelate


	//main method to test pixelate function
	public static void main(String[] args){

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("TestImageThree.jpg"));
		} catch (IOException e) {

			System.out.println("Image Not Found");
			e.printStackTrace();
		}//try catch 

		pixilate(img, 27);
		
		File output = new File("TestOut.jpg");

		try {
			ImageIO.write(img, "jpg", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //try catch
	} //main
} //Pixeliate
