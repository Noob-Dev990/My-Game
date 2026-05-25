import java.awt.event.*;
public final class Core{
	private Display display;
	private Player player;
	private final double TARGET_FPS=60.0;
	private final long TARGET_F_TIMENS=(long)(1000000000.0/TARGET_FPS);
	private double dt=0;
	private int fps=0;
	private boolean running=false;
	private boolean F3WasPressed=false;
	private boolean showDbg=false;
	public void start(){
		if(running) return;
		running=true;
		this.player=new Player();
		this.display=new Display();
		new Thread(this::gameLoop,"GameLoop").start();
	}
	private void gameLoop(){
		long lastTime=System.nanoTime();
		long fpsTimer=System.nanoTime();
		int frameCount=0;
		while(running){
			long now=System.nanoTime();
			dt=(now-lastTime)/1000000000.0;
			lastTime=now;
			player.update(dt);
			chkF3();
			display.render(showDbg,fps,player);
			frameCount++;
			if(now-fpsTimer>=1000000000L){
				fps=frameCount;
				frameCount=0;
				fpsTimer+=1000000000L;
			}
			long workTimeNS=System.nanoTime()-lastTime;
			long sleepTimeNS=TARGET_F_TIMENS-workTimeNS;
			if(sleepTimeNS>0){
				long sleepTimeMS=sleepTimeNS/1000000;
				try{
					if(sleepTimeMS>1)
						Thread.sleep(sleepTimeMS-1);
				}
				catch(Exception e){
					Thread.currentThread().interrupt();
					running=false;
				}
			}
		}
	}
	private void chkF3(){
		if(InputHandler.isKeyPressed(KeyEvent.VK_F3)){
			if(!F3WasPressed){
				showDbg=!showDbg;
				F3WasPressed=true;
			}
		}
			else
				F3WasPressed=false;
	}
	public static void main(String[]args){
		Core c=new Core();
		c.start();
	}
}