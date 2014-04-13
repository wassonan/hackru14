/* Wasson An
 * This class does provides the dithering effect and can add borders
 */

package filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import utility.ColorConverter;

public class Dithering {

	//edits the image with the dithering effect
	public static void dither(BufferedImage img, int pixSize, int option){

		//iterates over the selected area pixilateing it
		for(int i = 0; i < img.getWidth() - pixSize; i += pixSize){
			for(int j = 0; j < img.getHeight() - pixSize; j += pixSize){

				int[] rgb = new int[3]; //red, blue, and green color values
				int color; //the dithered color

				//iterate over each pixel adding up the rgb values
				for(int x = i; x < i + pixSize; x++){
					for(int y = j; y < j + pixSize; y++){

						System.out.println("(" + x + ", " + y + ")");

						int[] pix = ColorConverter.HexToRGB(img.getRGB(x, y));

						for(int k = 0; k < 3; k++)
							rgb[k] += pix[k];
					} //for
				} //for

				//calculate the average color
				for(int k = 0; k < 3; k++)
					rgb[k] /= (pixSize * pixSize); 

				//dithers the average color
				if(option == 1){

					for(int k = 0; k < 3; k++){

						if(rgb[k] > 127){
							if(rgb[k] > 192)
								rgb[k] = 255;
							else
								rgb[k] = 255;
						} //if
						else{
							if(rgb[k] > 64)
								rgb[k] = 0;
							else
								rgb[k] = 0;
						} //else
					}// for
				} //if
				
				else if(option == 2){

					for(int k = 0; k < 3; k++){

						if(rgb[k] > 127){
							if(rgb[k] > 192)
								rgb[k] = 255;
							else
								rgb[k] = 128;
						} //if
						else{
							if(rgb[k] > 64)
								rgb[k] = 128;
							else
								rgb[k] = 0;
						} //else
					}// for
				} //else if
				
				else if(option == 3){

					for(int k = 0; k < 3; k++){

						if(rgb[k] > 127){
							if(rgb[k] > 192)
								rgb[k] = 255;
							else
								rgb[k] = 192;
						} //if
						else{
							if(rgb[k] > 64)
								rgb[k] = 64;
							else
								rgb[k] = 0;
						} //else
					}// for
				} //else if
				
				
				//sets the dithered color
				color = ColorConverter.RGBtoHex(rgb[0], rgb[1], rgb[2]);

				//go through the pixel setting the color to the average color
				for(int x = i; x < i + pixSize; x++)
					for(int y = j; y < j + pixSize; y++)
						img.setRGB(x, y, color);
			} //for
		} //for
	} //dither


	//main method to test pixelate function
	public static void main(String[] args){

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("TestImageThree.jpg"));
		} catch (IOException e) {

			System.out.println("Image Not Found");
			e.printStackTrace();
		}//try catch 

		BufferedImage img2 = null;
		try {
			img2 = ImageIO.read(new File("TestImageTwo.jpg"));
		} catch (IOException e) {

			System.out.println("Image Not Found");
			e.printStackTrace();
		}//try catch 
		
		dither(img, 27, 3);
		dither(img2, 27, 1);
		
		File output = new File("TestOut.jpg");

		try {
			ImageIO.write(img, "jpg", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //try catch

		boolean[][] border = Border.getBorderLocation(img2);
		Border.drawBorder(img, border);
		
		output = new File("TestOut.jpg");

		try {
			ImageIO.write(img, "jpg", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //try catch
	} //main
} //Dithering
