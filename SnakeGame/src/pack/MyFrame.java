package pack;

import java.awt.Color;
import javax.swing.*;

public class MyFrame extends JFrame {
	
	private static int screenWidth = 655;
	private static int screenHeight = 677;
	
	
	MyFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(screenWidth, screenHeight);
		this.setLayout(null);
		this.addKeyListener(Main.getController());
		
		this.setVisible(true);
		
	}
	
	//accessor methods
	public static int getScreenWidth() {
		return screenWidth;
	}
	public static int getScreenHeight() {
		return screenHeight;
	}
	
}