package com.DwarfPlanet.TheTower.Objects;

import java.util.LinkedList;

import com.DwarfPlanet.TheTower.Draw;
import com.DwarfPlanet.TheTower.Game;
import com.DwarfPlanet.TheTower.framework.FloatPoint;
import com.DwarfPlanet.TheTower.framework.GameObject;
import com.DwarfPlanet.TheTower.framework.KeyInput;
import com.DwarfPlanet.TheTower.framework.ObjectId;
import com.DwarfPlanet.TheTower.framework.Side;
import com.DwarfPlanet.TheTower.framework.Vector2D;

public class Bullet extends GameObject {
	
	public static Vector2D v;
	public int time = 0;
	public int speed = 8;
	public int length;
	public int power;
	
	public Bullet(float x, float y, int power, int length) {
		super(x, y, 8, 8, ObjectId.Bullet);
		v = new Vector2D(new FloatPoint(x,y), new FloatPoint(KeyInput.camMouse.x - width / 2, KeyInput.camMouse.y - height / 2));
		this.length = length;
		this.power = power;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		time++;
		x = v.move(time*speed).x;
		y = v.move(time*speed).y;
		if (time > length) dispose();
		for (int i = 0; i < Game.handler.object.size(); i++) {
			GameObject temp = Game.handler.object.get(i);
			
			//Exceptions
			if (temp.id == ObjectId.Block){
				if (getBounds().intersects(temp.getBounds())) {
					dispose();
				}
			}
		}
	}

	@Override
	public void render() {
		Draw.rectangle((int) x+2, (int) y+2, (int) width-4, (int) height-4, 0xff0000);
		
	}

}
