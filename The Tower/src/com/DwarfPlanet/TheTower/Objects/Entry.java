package com.DwarfPlanet.TheTower.Objects;

import java.util.LinkedList;

import com.DwarfPlanet.TheTower.Draw;
import com.DwarfPlanet.TheTower.Game;
import com.DwarfPlanet.TheTower.framework.GameObject;
import com.DwarfPlanet.TheTower.framework.ObjectId;

public class Entry extends GameObject {
	
	
	
	public Entry(float x, float y) {
		super(x, y, 128, 128, ObjectId.Entry);
		width = 128;
		height = 128;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}

	@Override
	public void render() {
		Draw.texture((int) x, (int) y, (int) width,  (int) height, Game.texture, 3, 0, false);
	}

}
