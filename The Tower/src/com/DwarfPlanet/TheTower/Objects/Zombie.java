package com.DwarfPlanet.TheTower.Objects;

import java.util.LinkedList;

import com.DwarfPlanet.TheTower.Draw;
import com.DwarfPlanet.TheTower.Game;
import com.DwarfPlanet.TheTower.framework.FloatPoint;
import com.DwarfPlanet.TheTower.framework.GameObject;
import com.DwarfPlanet.TheTower.framework.Handler;
import com.DwarfPlanet.TheTower.framework.KeyInput;
import com.DwarfPlanet.TheTower.framework.ObjectId;
import com.DwarfPlanet.TheTower.framework.Side;
import com.DwarfPlanet.TheTower.framework.Vector2D;

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
				break;
			case Player:
				if(temp.x > x) x += walkSpeed;
				if(temp.x < x) x -= walkSpeed;
				if(temp.y > y) y += walkSpeed;
				if(temp.y < y) y -= walkSpeed;
				if (getBounds().intersects(temp.getBounds())) {
					Player.health -= 0.25;
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
			}
			
		}
		
	}

	@Override
	public void render() {
		//Draw.rectangle((int) x, (int) y, (int) width, (int) height, 0xff0000);
		Vector2D v = new Vector2D(new FloatPoint(x,y), new FloatPoint(Player.xl, Player.yl));
		Side s = v.getSide();
		if (s == Side.right)
			Draw.texture((int) x, (int) y, (int) width,  (int) height, Game.playerSprite, 1, 0, false);
		if (s == Side.left)
			Draw.textureFlip((int) x, (int) y, (int) width,  (int) height, Game.playerSprite, 1, 0, false);
		if (s == Side.top)
			Draw.texture((int) x, (int) y, (int) width,  (int) height, Game.playerSprite, 1, 1, false);
		if (s == Side.bottom)
			Draw.texture((int) x, (int) y, (int) width,  (int) height, Game.playerSprite, 1, 2, false);
	}

}
