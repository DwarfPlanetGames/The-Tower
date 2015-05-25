package com.DwarfPlanet.TheTower.Objects;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import com.DwarfPlanet.TheTower.Draw;
import com.DwarfPlanet.TheTower.Game;
import com.DwarfPlanet.TheTower.framework.GameObject;
import com.DwarfPlanet.TheTower.framework.Handler;
import com.DwarfPlanet.TheTower.framework.KeyInput;
import com.DwarfPlanet.TheTower.framework.ObjectId;
import com.DwarfPlanet.TheTower.framework.Side;

public class Player extends GameObject{
	
	private Handler handler;
	public static int walkSpeed = 1;
	public static final int walkSpeedO = 1;

	public Player(float x, float y,Handler handler) {
		super(x, y, 64, 64, ObjectId.Player);
		this.handler = handler;
	}

	
	public void tick(LinkedList<GameObject> object) {
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
		
		if (KeyInput.mouseButtons[MouseEvent.BUTTON1]) {
			handler.object.add(new Bullet(x+28,y+28));
		}
	}

	
	public void render() {
		//Draw.rectangle((int)x, (int)y, (int) width, (int) height, 0xffffff);
		Draw.texture((int) x, (int) y, (int) width,  (int) height, Game.playerSprite, 0, 0, false);
	}
}
