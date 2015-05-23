package com.DwarfPlanet.TheTower.Objects;

import java.awt.Rectangle;
import java.util.LinkedList;

import com.DwarfPlanet.TheTower.Draw;
import com.DwarfPlanet.TheTower.framework.GameObject;
import com.DwarfPlanet.TheTower.framework.ObjectId;

public class Block extends GameObject{

	public Block(float x, float y) {
		super(x, y,64f,64f, ObjectId.Block);
		width = 64;
		height = 64;
	}

	
	public void tick(LinkedList<GameObject> object) {
		
		
	}


	public void render() {
		Draw.rectangle((int)x, (int)y,(int) width,(int) height, 0xff0000);
		
	}


	public Rectangle getBounds() {
		return null;
	}

}
