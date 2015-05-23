package com.DwarfPlanet.TheTower.Objects;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.DwarfPlanet.TheTower.Draw;
import com.DwarfPlanet.TheTower.framework.BufferedImageLoader;
import com.DwarfPlanet.TheTower.framework.GameObject;
import com.DwarfPlanet.TheTower.framework.ObjectId;

public class Block extends GameObject{
	
	private static final BufferedImage image = BufferedImageLoader.loadImage("/128SpriteSheet.png");
	
	public Block(float x, float y) {
		super(x, y,128f,128f, ObjectId.Block);
		width = 128;
		height = 128;
	}

	
	public void tick(LinkedList<GameObject> object) {
		
		
	}


	public void render() {
		//Draw.rectangle((int)x, (int)y,(int) width,(int) height, 0xff0000);
		Draw.texture((int) x, (int) y, (int) width,  (int) height, image, 0, 0, false);
	}

}
