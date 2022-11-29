package pack;

import java.awt.Color;
import javax.swing.JLabel;

public class Rectangle extends Shape {
	
	private int width, height;
	private JLabel selectedRect = new JLabel();;//The graphics object
	private int r = 50;
	private int g = 50;
	private int b = 50;
	private boolean isFlipped = false;
	public boolean isFood = false;
	public boolean isPlayerPart = false;
	
	
	Rectangle() {
		super();
		width = 10;
		height = 10;
		selectedRect.setBounds(getX(), getY(), width, height);
		selectedRect.setOpaque(true);
		Main.getMyFrame().add(selectedRect);
		setColor(new Color(r,g,b));
	}
	
	Rectangle(int x1, int y1, int x2, int y2) {
		setX(x1);
		setY(y1);
		width = x2;
		height = y2;
		selectedRect.setBounds(getX(), getY(), width, height);
		selectedRect.setBackground(Color.BLUE);
		selectedRect.setOpaque(true);
		Main.getMyFrame().add(selectedRect);
		setColor(new Color(r, g, b));
	}
	public void setColor(Color c) {
		selectedRect.setBackground(c);
	}
	
	public void moveRect(int xx, int yy) {
		setX(getX() + xx);
		setY(getY() + yy);
		selectedRect.setLocation(selectedRect.getX() + xx, selectedRect.getY() + yy);
	}
	public void setRectPosition(int xx, int yy) {
		setX(xx);
		setY(yy);
		selectedRect.setLocation(xx, yy);
	}
	
	public void flip() {
		if (isFlipped) {
			setColor(new Color(20,20,20));
		} else {
			setColor(new Color(150,150,150));
		}
		isFlipped = !isFlipped;
	}
	
	public void setFood() {
		isFood = true;
		setColor(new Color(255,0,0));
	}
}
