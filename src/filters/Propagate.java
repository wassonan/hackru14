/* Wasson An
 * This class will find the pixels connected to the given one
 */

package filters;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;

import utility.Utility;

public class Propagate {

	//this find the group of connected pixels
	public static boolean[][] propagate(BufferedImage img, int x, int y){

		int color = img.getRGB(x, y);
		boolean[][] points = new boolean[img.getWidth()][img.getHeight()];
		Stack<Point2D> toExplore = new Stack<Point2D>();

		toExplore.push(new Point2D.Float(x, y));

		while(!toExplore.empty()){

			Point2D point = toExplore.pop();
			int i = (int)(point.getX());
			int j = (int)(point.getY());

			if(!points[i][j]){

				if(i > 0){

					if(j > 0){

						if(color == img.getRGB(i - 1, j - 1)){

							toExplore.push(new Point2D.Float(i - 1, j - 1));
						} //if
					}//if

					if(j < img.getHeight() - 1){

						if(color == img.getRGB(i - 1, j + 1)){

							toExplore.push(new Point2D.Float(i - 1, j + 1));
						} //if
					} //if

					if(color == img.getRGB(i -  1, j)){

						toExplore.push(new Point2D.Float(i - 1, j));
					} //if
				} //if

				if(i < img.getWidth() - 1){

					if(j > 0){

						if(color == img.getRGB(i + 1, j - 1)){

							toExplore.push(new Point2D.Float(i + 1, j - 1));
						} //if
					}//if

					if(j < img.getHeight() - 1){

						if(color == img.getRGB(i + 1, j + 1))
							toExplore.push(new Point2D.Float(i + 1, j + 1));
					} //if

					if(color == img.getRGB(i + 1, j)){

						toExplore.push(new Point2D.Float(i + 1, j));
					} //if
				} //if

				if(j > 0)
					if(color == img.getRGB(i, j - 1)){

						toExplore.push(new Point2D.Float(i, j - 1));
					} //if

				if(j < img.getHeight() - 1){

					if(color == img.getRGB(i, j + 1)){

						toExplore.push(new Point2D.Float(i, j + 1));
					} //if
				} //if

				points[i][j] = true;
			} //if
		} //while

		return points;
	} //Propagate


	//main method to test pixelate function
	public static void main(String[] args){

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("TestImageFour.jpg"));
		} catch (IOException e) {

			System.out.println("Image Not Found");
			e.printStackTrace();
		}//try catch 

		Pixelate.pixilate(img, 9);
		Simplify.simplify(img);
		
		int back = Color.GRAY.getRGB();
		
		BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(),
				img.getType());
		
		for(int i = 0; i < img2.getWidth(); i++)
			for(int j = 0; j < img2.getHeight(); j++)
				img2.setRGB(i, j, back);
		

		int x = (int)(Math.random() * img.getWidth());
		int y = (int)(Math.random() * img.getHeight());
		int color = img.getRGB(x, y);
		Utility.fill(img2, propagate(img, x, y), color);
		
		File output = new File("TestOut.jpg");

		try {
			ImageIO.write(img2, "jpg", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //try catch
	} //main
} //Propagate
