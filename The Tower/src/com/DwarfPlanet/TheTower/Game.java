package com.DwarfPlanet.TheTower;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.DwarfPlanet.TheTower.Objects.Block;
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
		
		LoadImageLevel(level);
		
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
		this.requestFocus();
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
		
		g.drawImage(img,0,0,width*2 + 11,height*2 + 10,null);
		for(int i =0; i < pixels.length;i++){
			pixels[i] = 0;
		}
		
		handler.render();
		g.dispose();
		bs.show();
		
		
	}
	
	private void LoadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx < h; xx++){
			for(int yy = 0; yy < w; yy++){
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 0 && green == 0 && blue == 0){
					handler.addObject(new Block(xx*64,yy*64));
				}
				
				if(red == 0 && green == 0 && blue == 255){
					handler.addObject(new Player(xx*64,yy*64,handler));
				}
			}
		}
	}
	
	
	public static void main(String args[]){
		new Window(800, 500, "the floor",new Game());
	}

}
