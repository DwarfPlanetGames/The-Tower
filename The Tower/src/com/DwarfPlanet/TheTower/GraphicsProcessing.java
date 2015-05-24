package com.DwarfPlanet.TheTower;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.DwarfPlanet.TheTower.framework.BufferedImageLoader;

public class GraphicsProcessing {
	
	public static BufferedImage image = BufferedImageLoader.loadImage("/128SpriteSheet.png");
	
	public static void preBuffer() {
		for (int x = -1; x < Game.width / 128 + 1; x++) {
			for (int y = -Game.height / 128 - 1; y < 1; y++) {
				Draw.texture(-Game.camX % 128 + 128 * x, -Game.camY % 128 - 128 * y, 128, 128, image, 128, 0, true);
			}
		}
	}
	
	public static void postBuffer() {
		
	}

	public static void postGraphics(Graphics2D g) {
		
	}
	
}
