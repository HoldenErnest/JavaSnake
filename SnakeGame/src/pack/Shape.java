package pack;

import java.awt.Color;

public class Shape {
	
	private static MyFrame frame;
	static int shapesAlive = 0;
	//instance variables
	private String type;
	private int x, y;
	
	//constructors
	Shape() {
		type = "Basic Shape";
		x = 0;
		y = 0;
		shapesAlive++;
	}
	
	//accessor methods
	public String getType() {
		return type;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public MyFrame getMyFrame() {
		return frame;
	}
	
	//mutator methods
	public void setX(int xx) {
		x = xx;
	}
	public void setY(int yy) {
		y = yy;
	}
	public void setType(String s) {
		type = s;
	}
	public void instantiateShape() {
		System.out.print("Drawign " + type);
	}
}
