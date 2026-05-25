import java.awt.event.*;
public final class Player{
	private double x=320.0;
	private double y=240.0;
	private final double speed=180.0;
	public Player(){
		this.x=320.0;
		this.y=240.0;
	}
	public void update(double dt){
		double move=speed*dt;
		if (InputHandler.isKeyPressed(KeyEvent.VK_W)) {
			y -= move;
		}
		if (InputHandler.isKeyPressed(KeyEvent.VK_S)) {
			y += move;
		}
		if (InputHandler.isKeyPressed(KeyEvent.VK_A)) {
			x -= move;
		}
		if (InputHandler.isKeyPressed(KeyEvent.VK_D)) {
			x += move;
		}
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
}