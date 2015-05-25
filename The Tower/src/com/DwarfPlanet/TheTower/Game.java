package com.DwarfPlanet.TheTower;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.DwarfPlanet.TheTower.Objects.Block;
import com.DwarfPlanet.TheTower.Objects.Entry;
import com.DwarfPlanet.TheTower.Objects.Hole;
import com.DwarfPlanet.TheTower.Objects.Player;
import com.DwarfPlanet.TheTower.Objects.Table;
import com.DwarfPlanet.TheTower.Objects.Zombie;
import com.DwarfPlanet.TheTower.framework.BufferedImageLoader;
import com.DwarfPlanet.TheTower.framework.Handler;
import com.DwarfPlanet.TheTower.framework.KeyInput;
import com.DwarfPlanet.TheTower.framework.Level;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1755866874599483047L;

	private boolean running;
	private Thread thread;

	public static int[] pixels;
	public static BufferedImage img;
	public static int camX, camY;
	public static int camToX, camToY;
	public static int leveli = 0;
	public static int time = 0;
	public static KeyInput k;

	public static final BufferedImage level = BufferedImageLoader.loadImage("/Level-1.png");
	public static final BufferedImage texture = BufferedImageLoader.loadImage("/128SpriteSheet.png");

	public static Handler handler;

	public static int width, height;
	
	public Game() {
		handler = new Handler();
		k = new KeyInput(handler);

		addKeyListener(k);
		addMouseListener(k);
		addMouseMotionListener(k);
	}

	public void init() {

		leveli = -1 + 0;
		levelUp();

		GraphicsProcessing.init();
	}

	public synchronized void start() {

		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		if (running) return;

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;

			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (shouldRender) {
				frames++;
				render();
			}
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println("ticks: " + ticks + " frames: " + frames);
				frames = 0;
				ticks = 0;
			}
		}
	}

	public void tick() {
		time++;
		handler.tick();
		camX = (int) ((camToX - camX) * 0.1 + camX);
		camY = (int) ((camToY - camY) * 0.2 + camY);
		KeyInput.camMouse.x = KeyInput.mouse.x + Game.camX;
		KeyInput.camMouse.y = KeyInput.mouse.y + Game.camY;
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		Graphics2D g2d = (Graphics2D) g;

		if (camX < 0) camX = 0;
		if (camY < 0) camY = 0;
		if (camX + width > 16 * 128) camX = 16 * 128 - width;
		if (camY + height > 16 * 128) camY = 16 * 128 - height;

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}

		GraphicsProcessing.preBuffer();
		handler.render();
		GraphicsProcessing.postBuffer();

		g.drawImage(img, 0, 0, width * Window.scale + 11, height * Window.scale + 10, null);
		GraphicsProcessing.postGraphics(g2d);

		g.dispose();
		bs.show();

	}

	public static void levelUp() {
		leveli++;
		LoadImageLevel(level, Level.dim(leveli, 8).x, Level.dim(leveli, 8).y);
		camX = 0;
		camToX = 0;
		camY = 0;
		camToY = 0;
		time = 0;
	}

	private static void LoadImageLevel(BufferedImage image, int x, int y) {
		handler.object.clear();
		System.out.println("Loading level at cell: " + x + "x" + y);
		for (int xx = 0; xx < 16; xx++) {
			for (int yy = 0; yy < 16; yy++) {
				int pixel = image.getRGB(xx + x * 16, yy + y * 16);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if (red == 0 && green == 0 && blue == 0) {
					handler.addObject(new Block(xx * 128, yy * 128));
				}

				if (red == 0 && green == 0 && blue == 255) {
					handler.addObject(new Entry(xx * 128, yy * 128));
					handler.addObject(new Player(xx * 128 + 32, yy * 128 + 32, handler));
				}

				if (red == 0 && green == 255 && blue == 255) {
					handler.addObject(new Hole(xx * 128, yy * 128));
				}
				if (red == 255 && green == 255 && blue == 0) {
					handler.addObject(new Table(xx * 128, yy * 128));
				}
				if (red == 255 && green == 0 && blue == 0) {
					handler.addObject(new Zombie(xx * 128 + 32, yy * 128 + 32, handler));
				}
			}
		}
	}

	public static void main(String args[]) {
		new Window(800, 500, "The Tower", new Game());
	}

}
