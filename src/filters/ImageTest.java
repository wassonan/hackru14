package filters;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import utility.Utility;


/* Wasson An
 * This class is the test for basic image modification
 */


public class ImageTest {

	public static void main(String[] args){

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("TestTwo.jpg"));
		} catch (IOException e) {

			e.printStackTrace();
		} //try catch

		int startX = 0;
		int startY = 0;
		int endX = img.getWidth();
		int endY = img.getHeight();
		int width = 27;
		int height = 27;

		BufferedImage newImage = new BufferedImage(img.getWidth() / width,
				img.getHeight() / height, img.getType());

		System.out.println(426 < img.getHeight());

		//DITHERS THE COLOR
		for(int i = startX; i < endX - width - 1; i += width){
			for(int j = startY; j < endY - height - 1; j += height){

				int[] rgb = new int[3];
				rgb[0] = 0;
				rgb[1] = 0;
				rgb[2] = 0;

				for(int x = i; x < i + width; x++){
					for(int y = j; y < j + height; y++){

						System.out.println("Dithering (" + x +", " + y + ")");

						int[] pixel = Utility.HexToRGB(img.getRGB(x, y));

						rgb[0] += pixel[0];
						rgb[1] += pixel[1];
						rgb[2] += pixel[2];
					} //for
				} //for		

				rgb[0] /= (width * height);
				rgb[1] /= (width * height);
				rgb[2] /= (width * height);

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
							rgb[k] = k;
					} //else
				}// for

				//								rgb[0] &= 0xF0;
				//								rgb[1] &= 0xF0;
				//								rgb[2] &= 0xF0;
				//								
				//								rgb[0] /= 64;
				//								rgb[0] *= 64;
				//								rgb[1] /= 64;
				//								rgb[1] *= 64;
				//								rgb[2] /= 64;
				//								rgb[2] *= 64;
				//
				//				rgb[0] *= 3;
				//				rgb[1] *= 3;
				//				rgb[2] *= 3;

				int pixel = Utility.RGBtoHex(rgb[0], rgb[1], rgb[2]);
				newImage.setRGB(i / width, j / height, pixel);
			} //for
		} //for


		//		//printing out smaller file
		File output = new File("TestTwoInterOut" + width + ".jpg");
		//
		//		try {
		//			ImageIO.write(newImage, "jpg", output);
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} //try catch


		boolean[][] points = new boolean[img.getWidth()][img.getHeight()];

		//		img = scale(newImage, height);

		//MAKES BLACK BORDER	
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

		for(int i = 0; i < points.length; i++)
			for(int j = 0; j < points[i].length; j++){
				
				System.out.println("Painting Border (" + i +", " + j + ")");
				img.setRGB(i, j, 0);
			} //for
				output = new File("TestTwoOut" + width + ".jpg");

				try {
					ImageIO.write(img, "jpg", output);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //try catch
			} //main

		private static BufferedImage scale(BufferedImage base, int scale){

			BufferedImage largeImage = new BufferedImage(base.getWidth() * scale,
					base.getHeight() * scale, base.getType());

			for(int i = 0; i < base.getWidth(); i++){
				for(int j = 0; j < base.getHeight(); j++){

					System.out.println("Scaling (" + i +", " + j + ")");
					paint(i * scale, j * scale, scale, base.getRGB(i, j), largeImage);

				} //for
			} //for 

			return largeImage;
		}  //scale

		private static void paint(int x, int y, int side, int color, BufferedImage img){

			for(int i = x; i < x + side; i++){
				for(int j = y; j < y + side; j++){

					System.out.println("Painting(" + i +", " + j + ") " + x +
							" " + side + " " + y);
					img.setRGB(i, j, color);
				} //for
			}//for
		}//paint
	} //ImageTest
