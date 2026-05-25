import java.awt.event.*;
public class InputHandler implements KeyListener{
	public static boolean[] keys=new boolean[256];
	public  InputHandler(){}
	@Override
	public void keyPressed(KeyEvent e){
		int keyCode=e.getKeyCode();
		if(keyCode>0 && keyCode<keys.length){
			keys[keyCode]=true;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e){
		int keyCode=e.getKeyCode();
		if(keyCode>0 && keyCode<keys.length){
			keys[keyCode]=false;
		}
	}
	@Override 
	public void keyTyped(KeyEvent e){
		
	}
	public static boolean isKeyPressed(int KeyCode){
		return KeyCode>=0 && KeyCode<keys.length && keys[KeyCode];
	}
}