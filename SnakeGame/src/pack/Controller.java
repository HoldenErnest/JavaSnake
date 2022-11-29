package pack;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller extends Component implements KeyListener {
	
	public int velocity = 1;
	public boolean leftRight = true;
	public boolean leftRightMoved = true;//only changes when moved, last input
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		switch (e.getKeyChar()) {
		case 'w': if (leftRightMoved) {velocity = -1;leftRight = false;System.out.print("w");}
			break;
		case 'a': if (!leftRightMoved) {velocity = -1;leftRight = true;System.out.print("a");}
			break;
		case 's': if (leftRightMoved) {velocity = 1;leftRight = false;System.out.print("s");}
			break;
		case 'd': if (!leftRightMoved) {velocity = 1;leftRight = true;System.out.print("d");}
			break;
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	
	}
	
}
