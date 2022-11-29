package pack;

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;
import java.awt.List;
import java.math.*;

public class Main {
	
	private static int gameSize = 32;
	
	private static MyFrame m;
	private static Controller c;
	private static Rectangle[][] rectMatrix;//grid of graphics
	
	private static boolean loop = true;
	private static double deltaTime, lastTime; //only used in updateDeltaTime()
	private static ArrayList<Double> deltaTimes = new ArrayList<Double>();//array of deltaTimes to get an average deltaTime
	private static int deltaTimeSamples = 50;//inputs for deltaTimes list; less samples = better performance
	
	private static ArrayList<Rectangle> playerRects = new ArrayList<Rectangle>();
	
	private static int playerX, playerY;
	
	private static int frameCount;
	private static int preferedFps = 9;
	
	public static void main(String[] args) {
		//initialize fps samples
		//for (int i = 0; i < deltaTimeSamples; i++) {
		//	deltaTimes.add(0.0);
		//}
		c = new Controller();
		m = new MyFrame();
		
		startGame();
	}
	
	public static void startGame() {
		deltaTimes = new ArrayList<Double>();
		for (int i = 0; i < deltaTimeSamples; i++) {
			deltaTimes.add(0.0);
		}
		frameCount = 0;
		loop = true;
		playerRects = new ArrayList<Rectangle>();
		rectMatrix = new Rectangle[gameSize][gameSize];
		for (int i = 0; i < rectMatrix.length; i++) {
			for (int j = 0; j < rectMatrix[0].length; j++) {
				rectMatrix[i][j] = new Rectangle((i*20),j*20,21,20);
				rectMatrix[i][j].setColor(new Color(20,20,20));
			}
		}
		
		playerX = gameSize/2;
		playerY = gameSize/2;
		playerRects.add(rectMatrix[playerX][playerY]);
		rectMatrix[playerX][playerY].flip();
		
		summonFood();
		updateDeltaTime();
		_update();
	}
	
	public static Controller getController() {
		return c;
	}
	public static void updateDeltaTime() {
		deltaTime = 1000000000.0 / (System.nanoTime() - lastTime);
        lastTime = System.nanoTime();
	}
	
	public static double getDeltaAverage() {
		double a = 0;
		for (double d : deltaTimes) {
			a += d;
		}
		
		return a/deltaTimes.size();
	}
	public static void takeDeltaSample() {
		deltaTimes.remove(0);
		deltaTimes.add(deltaTime);
	}
	
	//vvvvvvvvvvvvvvvv----------Updating Methods: calls other classes Update methods--------------vvvvvvv
	public static void _update() {//loop updates every instance
		
		while (loop) {
			getFrameUpdates();
			
		}
		
	}
	
	public static void _frameUpdate() {//loop updates preferedFps per second : send _frameUpdate loop to other scripts
			movePlayer();
		
	}
	//^^^^^^^^^^^^----------Updating Methods: calls other classes Update methods--------------^^^^^
	
	public static void getFrameUpdates() {
		updateDeltaTime();
		takeDeltaSample();
		if (frameCount >= getDeltaAverage()/preferedFps) {
			_frameUpdate();
			frameCount = 0;
		}
		frameCount++;
	}
	
	private static void movePlayer() {
		if (c.leftRight) {
			try {
				if (!rectMatrix[playerX+c.velocity][playerY].isPlayerPart) {
					playerRects.add(rectMatrix[playerX+c.velocity][playerY]);//move horizontal
					c.leftRightMoved = true;
					//System.out.println("horizontal last move");
				} else {
					stop();
				}
			} catch (Exception e){
				stop();
			}
			playerX += c.velocity;
		} else {
			try {
				if (!rectMatrix[playerX][playerY+c.velocity].isPlayerPart) {
					playerRects.add(rectMatrix[playerX][playerY+c.velocity]);//move vertical
					c.leftRightMoved = false;
					//System.out.println("vertical last move");
				} else {
					stop();
				}
			} catch (Exception e){
				stop();
			}
			playerY += c.velocity;
		}
		
		playerRects.get(playerRects.size()-1).flip();
		playerRects.get(playerRects.size()-1).isPlayerPart = true;
		
		if (!playerRects.get(playerRects.size()-1).isFood) {//players front eats a food
			playerRects.get(0).flip();
			playerRects.get(0).isPlayerPart = false;
			playerRects.remove(0);
			
		} else {
			playerRects.get(playerRects.size()-1).isFood = false;
			summonFood();
			
		}
		
	}
	
	
	public static MyFrame getMyFrame() {
		return m;
	}
	
	public static void summonFood() {
		int xx = (int) (Math.random()*gameSize);
		int yy = (int) (Math.random()*gameSize);
		boolean isPlayer = false;
		for (Rectangle rr : playerRects) {
			if (rectMatrix[xx][yy] == rr) {//food placed on a playerbox
				isPlayer = true;
				summonFood();
			}
				
		}
		if (!isPlayer)
			rectMatrix[xx][yy].setFood();
	}
	
	public static void stop() {
		loop = false;
	}
	
	
}
