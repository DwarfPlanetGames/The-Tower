package com.DwarfPlanet.TheTower;

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
}
