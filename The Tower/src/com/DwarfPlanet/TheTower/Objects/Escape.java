package com.DwarfPlanet.TheTower.Objects;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.DwarfPlanet.TheTower.Draw;
import com.DwarfPlanet.TheTower.Game;
import com.DwarfPlanet.TheTower.Window;
import com.DwarfPlanet.TheTower.framework.BufferedImageLoader;
import com.DwarfPlanet.TheTower.framework.GameObject;
import com.DwarfPlanet.TheTower.framework.ObjectId;

public class Escape extends GameObject{
	
	private static final BufferedImage image = BufferedImageLoader.loadImage("/Escape.png");
	
	public Escape(float x, float y) {
		super(x, y,1280 / 2 - 1,720 / 3 - 1, ObjectId.Block);
	}

	
	public void tick(LinkedList<GameObject> object) {
		Game.camToX = (int) x;
		Game.camToY = (int)y;
	}


	public void render() {
		//Draw.rectangle((int)x, (int)y,(int) width,(int) height, 0xff0000);
		Draw.texture((int) x, (int) y, (int) width,  (int) height, image, 0, 0, false);
	}

}
