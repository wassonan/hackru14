package filters;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/* Wasson An
 * This class is the test for basic image modification
 */


public class ImageTest {

	public static void main(String[] args){

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("TestImage.jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
		} //try catch
		
		int side = 3;
		
		System.out.println(426 < img.getHeight());
		
		for(int i = 0; i < img.getWidth() - side - 1; i += side){
			for(int j = 0; j < img.getHeight() - side - 1; j += side){
				
				int rgb = 0;
				
				for(int x = i; x < i + side; x++){
					for(int y = j; y < j + side; y++){
						
						System.out.println("(" + x +", " + y + ")");
						
						rgb += img.getRGB(x, y);
					} //for
				} //for		
				
				rgb /= side * side;
				
				for(int x = i; x < i + side; x++){
					for(int y = j; y < j + side; y++){
						
						img.setRGB(x, y, rgb);
					} //for
				} //for	
			} //for
		} //for
		
		
		
		File output = new File("TestOut.jpg");
		
		try {
			ImageIO.write(img, "jpg", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //try catch
		
		
	} //main
} //ImageTest
