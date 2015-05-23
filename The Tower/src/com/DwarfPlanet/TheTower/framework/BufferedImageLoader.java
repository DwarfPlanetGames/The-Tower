package com.DwarfPlanet.TheTower.framework;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	
	
	public static BufferedImage loadImage(String path){
		BufferedImage image = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
		try{
			image = ImageIO.read(new BufferedImageLoader().getClass().getResourceAsStream(path));
		}catch (IOException e){
			e.printStackTrace();
		}
		return image;
	}
}
