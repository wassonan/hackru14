package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class TestShit extends JFrame{
	String imageIn = "TestImage.jpg";
	String imageOut = "Default.jpg";

	BackgroundPanel panel;


	JPanel pane = new JPanel(new GridBagLayout());
	TestShit() 
	{ 
		super("The Amazing Picture Shitty-ifier");
		try {
			panel = new BackgroundPanel(ImageIO.read(new File("Background.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		Rectangle rect = new Rectangle(screenSize.width / 2 - screenSize.width*3/8,
//				screenSize.height / 2 - screenSize.height*3/8,
//	            screenSize.width*3/4, screenSize.height*3/4);
		Rectangle rect = new Rectangle(0,0, screenSize.width, screenSize.height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(rect.width, rect.height));
		setResizable(false);
		pack();
		setLocation(rect.x, rect.y);
		Container con = this.getContentPane(); // inherit main frame
		panel.add(pane);	
		con.add(panel);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JLabel title = new JLabel();
		try {
			title.setIcon(new ImageIcon(getScaledImage(ImageIO.read(new File("Title.png")), 1024, 80)));
		} catch (IOException e) {}       
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(20,20,20,20);
		pane.add(title, c);
		
		JLabel label = new JLabel("Image Path:");
		label.setFont(new Font("Dialog", Font.BOLD, 28));
		c.fill = GridBagConstraints.WEST;
		c.weightx =
		c.gridx = 0;
		c.gridy = 1;
		pane.add(label, c);
		
		JButton button = new JButton("Button 1");
		c.fill = GridBagConstraints.EAST;
		c.gridx = 3;
		c.gridy = 1;
		pane.add(button, c);
		
		JTextField url = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		pane.add(url, c);
		
		JLabel pre = new JLabel();
		try {
			pre.setIcon(new ImageIcon(getScaledImage(ImageIO.read(new File(imageIn)), screenSize.width/3, screenSize.height/3)));
		} catch (IOException e) {
		}       
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(20,20,20,20);
		pane.add(pre, c);
		
		JLabel post = new JLabel();
		try {
			post.setIcon(new ImageIcon(getScaledImage(ImageIO.read(new File(imageOut)), screenSize.width/3, screenSize.height/3)));
		} catch (IOException e) {
		}       
		c.fill = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.gridx = 3;
		c.gridy = 2;
		c.insets = new Insets(20,20,20,20);
		pane.add(post, c);


		setVisible(true);
	}

	private Image getScaledImage(Image srcImg, int w, int h){
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();
		return resizedImg;
	}


	public static void main(String[] args){
		new TestShit();
	}
} 
