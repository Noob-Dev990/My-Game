import java.awt.*;

import java.awt.image.*;

import javax.swing.*;
public class Display extends Canvas{
	public static final int WIDTH=640;
	public static final int HEIGHT=480;
	private JFrame frame;
	private BufferedImage image;
	private int[] pixels;
	private BufferStrategy strategy;
	private boolean running=true;
	public Display(){
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		pixels=((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		frame=new JFrame("TEST");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new InputHandler());
		createBufferStrategy(2);
		strategy=getBufferStrategy();
	}
	public void render(boolean showDbg,int fps,Player player){
		Graphics g=strategy.getDrawGraphics();
		for(int i=0;i<pixels.length;i++){
			pixels[i]=0x000000;
		}
		g.drawImage(image,0,0,null);
		g.setColor(Color.WHITE);
		g.fillRect((int)player.getX(), (int)player.getY(),32,32);
		if(showDbg){
			g.setColor(Color.GREEN);
			g.drawString("FPS: "+fps,530,20);
		}
		g.dispose();
		strategy.show();
	}
}