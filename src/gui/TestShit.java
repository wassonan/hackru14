package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*; 

import filters.*;

public class TestShit extends JFrame {
	String imageIn = "Default.jpg"; //input url
	String imageOut = "Default.jpg"; //output url

	BackgroundPanel BGPanel;

	JPanel pane; // main pain holding everything, held by bg
	PicPanel optionsPane = new PicPanel(new FlowLayout());
	JPanel imgPane = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
	BackgroundPanel titlePane;
	JLabel pre;
	JLabel post;
	JTextField url;
	Dimension screenSize;
	JComboBox task;
	String[] options =  {"Pixelate", "Blur", "Wash"};
	TestShit() 
	{ 
		super("The Amazing Picture Shitty-ifier");
		try {// set bg image
			BGPanel = new BackgroundPanel(ImageIO.read(new File("Background.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {// set title img
			titlePane = new BackgroundPanel(ImageIO.read(new File("Title.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//		Rectangle rect = new Rectangle(screenSize.width / 2 - screenSize.width*3/8,
		//				screenSize.height / 2 - screenSize.height*3/8,
		//	            screenSize.width*3/4, screenSize.height*3/4);
		Rectangle rect = new Rectangle(0,0, screenSize.width, screenSize.height); // sets screen to be full
		pane = new JPanel(new BorderLayout(screenSize.width/8,screenSize.height/8));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(rect.width, rect.height));
		setResizable(false);
		pack();
		setLocation(rect.x, rect.y);
		Container con = this.getContentPane();
		BGPanel.add(pane);	
		con.add(BGPanel);

		JLabel label = new JLabel("Image Path:");
		label.setFont(new Font("Dialog", Font.BOLD, 28));
		optionsPane.add(label);
		url = new JTextField(20);
		optionsPane.add(url);
		JButton browse = new JButton("Browse");
		browse.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				int returnVal = jfc.showOpenDialog(pane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					imageIn = jfc.getSelectedFile().toString();
					try {
						pre.setIcon(new ImageIcon(getScaledImage(ImageIO.read(new File(imageIn)), (int)(screenSize.width*5/11), (int)(screenSize.height*5/11))));
					} catch (IOException e) {
					}
					pre.repaint();
					pane.validate();
					pane.repaint();
					url.setText(imageIn);
					url.repaint();
				}
			}
		});
		optionsPane.add(browse);
		task = new JComboBox(options);
		task.setSelectedIndex(0);
		task.addActionListener(optionsPane);
		optionsPane.add(task);
		JButton go = new JButton("Shittify");
		go.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				BufferedImage img = null;
				try {
					img = ImageIO.read(new File(imageIn));
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(task.getSelectedItem().toString() == options[0]){
					Pixelate.pixilate(img, 9);
				}
				File output = new File("TestOut.jpg");
				try {
					ImageIO.write(img, "jpg", output);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} //try catch
				imageOut = "TestOut.jpg";
				try {
					post.setIcon(new ImageIcon(getScaledImage(ImageIO.read(new File(imageOut)), (int)(screenSize.width*5/11), (int)(screenSize.height*5/11))));
				} catch (IOException e) {
				}
				post.repaint();
				pane.validate();
				pane.repaint();
			}
		});
		optionsPane.add(go);

		pre = new JLabel();
		try {
			pre.setIcon(new ImageIcon(getScaledImage(ImageIO.read(new File(imageIn)), (int)(screenSize.width*5/11), (int)(screenSize.height*5/11))));
		} catch (IOException e) {
		}       
		imgPane.add(pre);

		post = new JLabel();
		try {
			post.setIcon(new ImageIcon(getScaledImage(ImageIO.read(new File(imageOut)), (int)(screenSize.width*5/11), (int)(screenSize.height*5/11))));
		} catch (IOException e) {
		}       
		imgPane.add(post);

		pane.add(titlePane, BorderLayout.PAGE_START);
		pane.add(imgPane, BorderLayout.SOUTH);
		pane.add(optionsPane, BorderLayout.CENTER);
		optionsPane.setOpaque(false);
		imgPane.setOpaque(false);
		titlePane.setOpaque(false);
		setVisible(true);
		pane.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
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
