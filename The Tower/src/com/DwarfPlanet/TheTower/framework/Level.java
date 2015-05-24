package com.DwarfPlanet.TheTower.framework;

import java.awt.Point;

public class Level {
	public static Point dim(int l, int width) {
		Point p = new Point();
		p.x = l % width;
		p.y = l / width;
		return p;
	}
}
