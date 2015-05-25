package com.DwarfPlanet.TheTower.framework;

import java.awt.Point;

public class Vector2D {
	
	public FloatPoint from;
	public FloatPoint to;
	
	public Vector2D(FloatPoint from, FloatPoint to) {
		this.from = from;
		this.to = to;
	}
	
	public FloatPoint move(float dist) {
		
		float m = (to.y-from.y)/(to.x-from.x);
		float X = (float) (Math.sqrt(Math.pow(dist, 2)/(Math.pow(m, 2) + 1)));
		if (to.x < from.x) {
			X *= -1;
		}
		return new FloatPoint(from.x + X, from.y + m * X);
		
		/*
		FloatPoint f = new FloatPoint(from.x, from.y);
		if(to.x > from.x) f.x += dist;
		if(to.x < from.x) f.x -= dist;
		if(to.y > from.y) f.y += dist;
		if(to.y < from.y) f.y -= dist;
		return f;
		*/
	}
	
}
