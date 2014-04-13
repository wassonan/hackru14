/* Wasson An
 * This class takes in a group of points and turns it into a polygon
 */

package filters;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeSet;

import javax.imageio.ImageIO;

import utility.Utility;

public class Polygonize {

	// turns the passed in group of points into a polygon
	public static boolean[][] polygonize(boolean[][] points){

		boolean[][] toReturn = new boolean[points.length][points[0].length];
		HashSet<Point2D> corners = new HashSet<Point2D>();
		Polygon shape = new Polygon();		

		System.out.println("hello");

		int x = 0;
		int y = points[0].length - 1;

		//left
		while(x < points.length && !points[x][y]){

			x++;
			y = points[0].length - 1;

			while(y > 0 && !points[x][y])
				y--;
		} //while

		System.out.println("Left (" + x + ", " + y + ")");
		corners.add(new Point2D.Float(x, y));
		shape.addPoint(x, y);

		x = points.length - 1;
		y = points[0].length - 1;

		//bottom
		while(y >= 0 && !points[x][y]){

			y--;
			x = points.length - 1;

			while(x > 0 && !points[x][y])
				x--;


		} //while

		System.out.println("Bottom (" + x + ", " + y + ")");
		corners.add(new Point2D.Float(x, y));
		shape.addPoint(x, y);

		x = points.length - 1;
		y = 0;

		//right
		while(x >= 0 && !points[x][y]){

			x--;
			y = 0;

			while(y < points[x].length - 1 && !points[x][y])
				y++;


		} //while

		System.out.println("Right (" + x + ", " + y + ")");
		corners.add(new Point2D.Float(x, y));
		shape.addPoint(x, y);

		x = points.length - 1;
		y = 0;

		//top
		while(y < points[x].length - 1 && !points[x][y]){

			y++;
			x = points.length - 1;
			System.out.println("hello");
			while(x > 0 && !points[x][y])
				x--;
		} //while

		System.out.println("Top (" + x + ", " + y + ")");
		corners.add(new Point2D.Float(x, y));
		shape.addPoint(x, y);


		//		for(Point2D p: corners){
		//
		//			shape.addPoint((int)p.getX(), (int)p.getY());
		//		} //for

		for(x = 0; x < toReturn.length; x++)
			for(y = 0; y < toReturn[x].length; y++)
				if(shape.contains(x, y))
					toReturn[x][y] = true;

		return toReturn;
	} //polygonize


	public static void main(String[] args){

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("TestImageFour.jpg"));
		} catch (IOException e) {

			System.out.println("Image Not Found");
			e.printStackTrace();

		}//try catch

		Simplify.simplify(img);
		Pixelate.pixilate(img, 1);
		Simplify.simplify(img);

		File output = new File("Test1Out.jpg");

		try {
			ImageIO.write(img, "jpg", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //try catch

		int back = Color.GRAY.getRGB();

		BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(),
				img.getType());

		for(int i = 0; i < img2.getWidth(); i++)
			for(int j = 0; j < img2.getHeight(); j++)
				img2.setRGB(i, j, back);

		BufferedImage img3 = new BufferedImage(img.getWidth(), img.getHeight(),
				img.getType());

		for(int i = 0; i < img3.getWidth(); i++)
			for(int j = 0; j < img3.getHeight(); j++)
				img3.setRGB(i, j, back);

		//Random Polygonization
		
//		for(int i = 0; i < 10; i++){
//			
//			int x = (int)(Math.random() * img.getWidth());
//			int y = (int)(Math.random() * img.getHeight());
//			
//			while(img3.getRGB(x, y) != back){
//				
//				x = (int)(Math.random() * img.getWidth());
//				y = (int)(Math.random() * img.getHeight());
//			} //while
//			
//			int color = img.getRGB(x, y);
//			boolean[][] points = Propagate.propagate(img, x, y);
//
//			Utility.fill(img2, points, color);
//
//			output = new File("Test2Out.jpg");
//
//			try {
//				ImageIO.write(img2, "jpg", output);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} //try catch
//
//			
//
//			System.out.println("Starting Polygonization");
//
//			Utility.fill(img3, polygonize(points), color);
//		} //for
		
		for(int x = 0; x < img3.getWidth(); x++){
			for(int y = 0; y < img3.getHeight(); y++){
				
				if(img3.getRGB(x, y) == back)
					Utility.fill(img3, polygonize(Propagate.propagate(img3, x, y)),
							img3.getRGB(x, y));
			} //for
		} //for
		
		
		output = new File("TestOut.jpg");

		try {
			ImageIO.write(img3, "jpg", output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //try catch
	} //main
} //Polygonize
