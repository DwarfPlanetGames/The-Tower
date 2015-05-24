package com.DwarfPlanet.TheTower;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.DwarfPlanet.TheTower.Objects.Block;
import com.DwarfPlanet.TheTower.Objects.Hole;
import com.DwarfPlanet.TheTower.Objects.Player;
import com.DwarfPlanet.TheTower.framework.BufferedImageLoader;
import com.DwarfPlanet.TheTower.framework.Handler;
import com.DwarfPlanet.TheTower.framework.KeyInput;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1755866874599483047L;
	
	private boolean running;
	private Thread thread;
	
	public static int[] pixels;
	public BufferedImage img;
	public static int camX, camY;
	

	
	public Handler handler;
	
	public static int width, height; 
	
	public void init(){
		
		BufferedImage level = BufferedImageLoader.loadImage("/Level-1.png");
		
		handler = new Handler();
		
		LoadImageLevel(level, 0, 0);
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start(){
		
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			
			while(delta >= 1){
				ticks++;
				tick();
				delta -=1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			if(shouldRender){
				frames++;
				render();
			}
			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer += 1000;
				System.out.println("ticks: " + ticks + " frames: " + frames);
				frames = 0;
				ticks =0;
			}
		}
	}
	
	public void tick(){
		handler.tick();
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2d = (Graphics2D) g;
		
		if (camX < 0) camX = 0;
		if (camY < 0) camY = 0;
		if (camX+width > 16*128) camX = 16*128-width;
		if (camY+height > 16*128) camY = 16*128-height;
		
		for(int i =0; i < pixels.length;i++){
			pixels[i] = 0;
		}
		
		GraphicsProcessing.preBuffer();
		handler.render();
		GraphicsProcessing.postBuffer();
		
		g.drawImage(img,0,0,width*Window.scale + 11,height*Window.scale + 10,null);
		GraphicsProcessing.postGraphics(g2d);
		
		g.dispose();
		bs.show();
		
		
	}
	
	private void LoadImageLevel(BufferedImage image, int x, int y){
		handler.object.clear();
		
		for(int xx = x * 16; xx < x * 16 + 16; xx++){
			for(int yy = y * 16; yy < y * 16 + 16; yy++){
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 0 && green == 0 && blue == 0){
					handler.addObject(new Block(xx*128,yy*128));
				}
				
				if(red == 0 && green == 0 && blue == 255){
					handler.addObject(new Player(xx*128,yy*128,handler));
				}
				
				if(red == 0 && green == 255 && blue == 255) {
					handler.addObject(new Hole(xx*128,yy*128));
				}
			}
		}
	}
	
	
	public static void main(String args[]){
		new Window(800, 500, "The Tower",new Game());
	}

}
