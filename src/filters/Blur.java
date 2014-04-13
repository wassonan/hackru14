/* Wasson An
 * This filter applies a blur effect to the entire photo
 */

package filters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import utility.ColorConverter;

public class Blur {

	//blurs the passed in image
	public static void blur(BufferedImage img, int pixe){

		//iterates over the selected area pixilateing it
		for(int i = 0; i < img.getWidth() - pixe; i++){
			for(int j = 0; j < img.getHeight() - pixe; j++){

				int[] rgb = new int[3]; //red, blue, and green color values
				int color; //the average color of the pixel

				//iterate over each pixel adding up the rgb values
				for(int x = i; x < i + pixe; x++){
					for(int y = j; y < j + pixe; y++){

						System.out.println("(" + x + ", " + y + ")");

						int[] pix = ColorConverter.HexToRGB(img.getRGB(x, y));

						for(int k = 0; k < 3; k++)
							rgb[k] += pix[k];
					} //for
				} //for

				//calculate the average color
				for(int k = 0; k < 3; k++)
					rgb[k] /= (pixe * pixe); 

				color = ColorConverter.RGBtoHex(rgb[0], rgb[1], rgb[2]);
				
				//go through the pixel setting the color to the average color
				for(int x = i; x < i + 3; x++)
					for(int y = j; y < j + 3; y++)
						img.setRGB(x, y, color);
			} //for
		} //for
	} //blur
	

	//main class for testing blur
	public static void main(String args[]){
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("TestImageFour.jpg"));
		} catch (IOException e) {

			System.out.println("Image Not Found");
			e.printStackTrace();
		}//try catch 

		blur(img, 9);
		
		File output = new File("TestOut.jpg");

		try {
			ImageIO.write(img, "jpg", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //try catch
	} //main
} //Blur
