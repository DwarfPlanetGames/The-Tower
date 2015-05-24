package com.DwarfPlanet.TheTower;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

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
	
	public static void texture(int x, int y, int width, int height, BufferedImage image, int cellX, int cellY, boolean dontMoveToCam) {
		boolean draw = false;
		Rectangle r = new Rectangle();
		r.x = Game.camX;
		r.y = Game.camY;
		r.width = Game.width;
		r.height = Game.height;
		if (r.contains(x,y)) draw = true;
		if (r.contains(x+width,y)) draw = true;
		if (r.contains(x,y+height)) draw = true;
		if (r.contains(x+width,y+height)) draw = true;
		
		if (dontMoveToCam) {
			draw = true;
			x += Game.camX;
			y += Game.camY;
		}
		
		if (draw) {			
			for(int xx = cellX; xx < cellX + width; xx++){
				for(int yy = cellY; yy < cellY + height; yy++){
					int pixel = image.getRGB(xx, yy);
					if (pixel != 0xffff00ff)
						rectangle(xx + x, yy + y, 1, 1, pixel);
				}
			}
		}
	}
	
}