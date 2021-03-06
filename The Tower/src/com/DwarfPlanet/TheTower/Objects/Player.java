package com.DwarfPlanet.TheTower.Objects;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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

public class Player extends GameObject{
	
	private Handler handler;
	public static int walkSpeed = 1;
	public static final int walkSpeedO = 1;
	public static float health = 100;
	public static int xl, yl;

	public Player(float x, float y,Handler handler) {
		super(x, y, 64, 64, ObjectId.Player);
		this.handler = handler;
	}

	
	public void tick(LinkedList<GameObject> object) {
		if (health <= 0){
			Game.leveli--;
			Game.levelUp();
		}
		if (health < 100) {
			health += 0.01;
		}
		velX *= 0.9;
		velY *= 0.9;
		
		if (KeyInput.keys[KeyEvent.VK_W]) velY -= walkSpeed;
		if (KeyInput.keys[KeyEvent.VK_S]) velY += walkSpeed;
		if (KeyInput.keys[KeyEvent.VK_A]) velX -= walkSpeed;
		if (KeyInput.keys[KeyEvent.VK_D]) velX += walkSpeed;
		
		x += velX;
		y += velY;
		
		int v = 16; //Math.max((int) velX, (int) velY);
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			
			//Exceptions
			if (temp.id == ObjectId.Block){
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
			} else if (temp.id == ObjectId.Hole) {
				if (getBounds().intersects(temp.getBounds())) {
					Game.levelUp();
				}
			}
		}
		
		Game.camToX = (int) x - Game.width / 2 + (int)width / 2;
		Game.camToY = (int)y - Game.height / 2 + (int)height / 2;
		xl = (int) x;
		yl = (int) y;
		
		if (KeyInput.mouseButtons[MouseEvent.BUTTON1]) {
			handler.object.add(new Bullet(x+28,y+28,3,100));
			health -= 0.05;
		}
		if (KeyInput.mouseButtons[MouseEvent.BUTTON3]) {
			handler.object.add(new Bullet(x+28,y+28,10,10));
			health -= 0.05;
		}
	}

	
	public void render() {
		//Draw.rectangle((int)x, (int)y, (int) width, (int) height, 0xffffff);
		Vector2D v = new Vector2D(new FloatPoint(x + 32,y + 32), new FloatPoint(KeyInput.camMouse.x, KeyInput.camMouse.y));
		Side s = v.getSide();
		if (s == Side.right)
			Draw.texture((int) x, (int) y, (int) width,  (int) height, Game.playerSprite, 0, 0, false);
		if (s == Side.left)
			Draw.textureFlip((int) x, (int) y, (int) width,  (int) height, Game.playerSprite, 0, 0, false);
		if (s == Side.top)
			Draw.texture((int) x, (int) y, (int) width,  (int) height, Game.playerSprite, 0, 1, false);
		if (s == Side.bottom)
			Draw.texture((int) x, (int) y, (int) width,  (int) height, Game.playerSprite, 0, 2, false);
	}
}
