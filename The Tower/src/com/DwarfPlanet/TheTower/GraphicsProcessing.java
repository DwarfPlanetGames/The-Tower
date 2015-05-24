package com.DwarfPlanet.TheTower;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.DwarfPlanet.TheTower.framework.BufferedImageLoader;

public class GraphicsProcessing {
	
	public static int[] pixels;
	public static BufferedImage img;
	
	public static void init() {
		int height = Game.height * Window.scale;
		img = new BufferedImage(1, height, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		for (int y = 0; y < height; y++) {
			if (y % 2 == 0) {
				pixels[y] = 0x16000000;
			} else {
				pixels[y] = 0x00ffffff;
			}
		}
	}
	
	public static void preBuffer() {
		for (int x = -1; x < Game.width / 128 + 2; x++) {
			for (int y = -Game.height / 128 - 1; y < 1; y++) {
				Draw.texture(-Game.camX % 128 + 128 * x, -Game.camY % 128 - 128 * y, 128, 128, Game.texture, 1, 0, true);
			}
		}
	}
	
	public static void postBuffer() {
		
	}

	public static void postGraphics(Graphics2D g) {
		g.drawImage(img, 0, 0, Game.width * Window.scale, Game.height * Window.scale, null);
	}
	
}
