package com.DwarfPlanet.TheTower;

import java.awt.image.BufferedImage;

import com.DwarfPlanet.TheTower.Objects.Block;
import com.DwarfPlanet.TheTower.Objects.Player;
import com.DwarfPlanet.TheTower.framework.BufferedImageLoader;

public class Draw {
	
	public static void rectangle(int x,int y, int width, int height, int color) {
		x -= Game.camX;
		y -= Game.camY;
		int x2 = x + width;
		if (x < 0) x = 0;
		if (x > Game.width) x = Game.width;
		if(x2 < 0) x2 = 0;
		if(x2 > Game.width) x2 = Game.width;
		int y2 = y + height;
		if (y < 0) y = 0;
		if (y > Game.height) y = Game.height;
		if(y2 < 0) y2 = 0;
		if(y2 > Game.height) y2 = Game.height;
		for(int xl = x; xl < x2; xl++) {
			for (int yl = y; yl < y2; yl++) {
				Game.pixels[xl + yl * Game.width] = color;
			}
		}
	}	
	
	public static void texture(int x, int y, int width, int height, String path, int cellX, int cellY, boolean metadata) {
		BufferedImage image = BufferedImageLoader.loadImage(path);
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = cellX * width; xx < cellX * width + width; xx++){
			for(int yy = cellY * height; yy < cellY * height + height; yy++){
				int pixel = image.getRGB(xx, yy);
				if (pixel != 0xff00ffff)
					rectangle(xx + x, yy + y, 1, 1, pixel);
			}
		}
	}
	
}