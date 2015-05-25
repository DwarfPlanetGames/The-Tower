package com.DwarfPlanet.TheTower.Objects;

import java.util.LinkedList;

import com.DwarfPlanet.TheTower.Draw;
import com.DwarfPlanet.TheTower.framework.GameObject;
import com.DwarfPlanet.TheTower.framework.Handler;
import com.DwarfPlanet.TheTower.framework.ObjectId;
import com.DwarfPlanet.TheTower.framework.Side;

public class Zombie extends GameObject {
	
	private Handler handler;
	public static int walkSpeed = 1;
	
	public Zombie(float x, float y,Handler handler) {
		super(x, y, 64, 64, ObjectId.Zombie);
		this.handler = handler;
	}
//
	@Override
	public void tick(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			int v = walkSpeed + 1;
			switch (temp.id) {
			case Player:
				if(temp.x > x) x += walkSpeed;
				if(temp.x < x) x -= walkSpeed;
				if(temp.y > y) y += walkSpeed;
				if(temp.y < y) y -= walkSpeed;
				if (getBounds().intersects(temp.getBounds())) {
					Player.health -= 1;
				}
				if (getBounds(Side.top,v).intersects(temp.getBounds())) {
					y = temp.y + temp.height;
					velY = 0;
				}
				if (getBounds(Side.bottom,v).intersects(temp.getBounds())) {
					y = temp.y - height;
					velY = 0;
				}
				if (getBounds(Side.left,v).intersects(temp.getBounds())) {
					x = temp.x + temp.width;
					velX = 0;
				}
				if (getBounds(Side.right,v).intersects(temp.getBounds())) {
					x = temp.x - width;
					velX = 0;
				}
				break;
			case Zombie:
				if (getBounds(Side.top,v).intersects(temp.getBounds())) {
					y = temp.y + temp.height;
					velY = 0;
				}
				if (getBounds(Side.bottom,v).intersects(temp.getBounds())) {
					y = temp.y - height;
					velY = 0;
				}
				if (getBounds(Side.left,v).intersects(temp.getBounds())) {
					x = temp.x + temp.width;
					velX = 0;
				}
				if (getBounds(Side.right,v).intersects(temp.getBounds())) {
					x = temp.x - width;
					velX = 0;
				}
				break;
			case Entry:
				break;
			case Hole:
				break;
			case Block:
				if (getBounds(Side.top,v).intersects(temp.getBounds())) {
					y = temp.y + temp.height;
					velY = 0;
				}
				if (getBounds(Side.bottom,v).intersects(temp.getBounds())) {
					y = temp.y - height;
					velY = 0;
				}
				if (getBounds(Side.left,v).intersects(temp.getBounds())) {
					x = temp.x + temp.width;
					velX = 0;
				}
				if (getBounds(Side.right,v).intersects(temp.getBounds())) {
					x = temp.x - width;
					velX = 0;
				}
				break;
			case Bullet:
				if (getBounds().intersects(temp.getBounds())) {
					dispose();
					Player.health += 10;
				}
			}
		}
		
	}

	@Override
	public void render() {
		Draw.rectangle((int) x, (int) y, (int) width, (int) height, 0xff0000);
	}

}
