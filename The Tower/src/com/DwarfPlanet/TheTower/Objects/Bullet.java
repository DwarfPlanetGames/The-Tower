package com.DwarfPlanet.TheTower.Objects;

import java.util.LinkedList;

import com.DwarfPlanet.TheTower.Draw;
import com.DwarfPlanet.TheTower.framework.FloatPoint;
import com.DwarfPlanet.TheTower.framework.GameObject;
import com.DwarfPlanet.TheTower.framework.KeyInput;
import com.DwarfPlanet.TheTower.framework.ObjectId;
import com.DwarfPlanet.TheTower.framework.Vector2D;

public class Bullet extends GameObject {
	
	public static Vector2D v;
	public int time = 0;
	public int speed = 8;
	
	public Bullet(float x, float y) {
		super(x, y, 8, 8, ObjectId.Bullet);
		v = new Vector2D(new FloatPoint(x,y), new FloatPoint(KeyInput.camMouse.x - width / 2, KeyInput.camMouse.y - height / 2));
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		time++;
		x = v.move(time*speed).x;
		y = v.move(time*speed).y;
		if (time > 100) dispose();
	}

	@Override
	public void render() {
		Draw.rectangle((int) x, (int) y, (int) width, (int) height, 0xff0000);
		
	}

}
