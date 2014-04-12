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
		
		
		
		
		
		File output = new File("TestOut.jpg");
		
		try {
			ImageIO.write(img, "jpg", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //try catch
		
		
	} //main
} //ImageTest
