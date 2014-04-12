package com.ru.hack;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class TestShit extends JFrame{


	JPanel pane = new JPanel();
	TestShit() 
	{
		super("My Simple Frame"); setBounds(100,100,300,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = this.getContentPane(); // inherit main frame
		con.add(pane); // add the panel to frame
		// customize panel here
		// pane.add(someWidget);
		setVisible(true); // display this frame
	}

	

	public static void main(String[] args){
		new TestShit();

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
