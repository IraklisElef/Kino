

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		switch(key) {
		
			case KeyEvent.VK_ESCAPE:
				System.exit(1);
		}
		
	}
	
}
