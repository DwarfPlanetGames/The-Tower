package com.DwarfPlanet.TheTower.Objects;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.DwarfPlanet.TheTower.Draw;
import com.DwarfPlanet.TheTower.framework.BufferedImageLoader;
import com.DwarfPlanet.TheTower.framework.GameObject;
import com.DwarfPlanet.TheTower.framework.ObjectId;

public class Hole extends GameObject {
	
	private static final BufferedImage image = BufferedImageLoader.loadImage("/128SpriteSheet.png");
	
	public Hole(float x, float y) {
		super(x, y, 128f, 128f, ObjectId.Hole);
		width = 128;
		height = 128;
	}

	@Override
	public void tick(LinkedList<GameObject> object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		Draw.texture((int) x, (int) y, (int) width,  (int) height, image, 128-64, 0, false);
	}

}
