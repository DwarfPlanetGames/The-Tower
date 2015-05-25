package com.DwarfPlanet.TheTower;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import com.DwarfPlanet.TheTower.Objects.Player;
import com.DwarfPlanet.TheTower.Objects.Remark;
import com.DwarfPlanet.TheTower.framework.KeyInput;

public class GraphicsProcessing {
	
	public static int[] pixels;
	public static BufferedImage img;
	
	public static void init() {
		int height = Game.height * Window.scale;
		img = new BufferedImage(1, height, BufferedImage.TYPE_INT_ARGB);
		pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
		for (int y = 0; y < height; y++) {
			if (y % 2 == 0) {
				pixels[y] = 0x16000000;
			} else {
				pixels[y] = 0x16ffffff;
			}
		}
	}
	
	public static void preBuffer() {
		if (KeyInput.keys[KeyEvent.VK_E])
			for (int x = -1; x < Game.width / 128 + 2; x++) {
				for (int y = -Game.height / 128 - 1; y < 1; y++) {
					Draw.texture(-Game.camX % 128 + 128 * x, -Game.camY % 128 - 128 * y, 128, 128, Game.texture, 1, 0, true);
				}
			}
	}
	
	public static void postBuffer() {
		Draw.rectangle(Game.camX, Game.camY + 10, (int) Player.health, 10, 0x00ff00);
	}

	public static void postGraphics(Graphics2D g) {
		if (KeyInput.keys[KeyEvent.VK_E]) {
			g.setColor(Color.WHITE);
			g.fillRoundRect(-10, -10, Window.width, 30, 10,10);
			g.setColor(Color.BLACK);
			g.drawRoundRect(-10, -10, Window.width, 30, 10,10);
			g.drawString(Remark.remarks[Game.leveli % Remark.remarks.length], 10, 15);
			g.drawImage(img, 0, 0, Game.width * Window.scale, Game.height * Window.scale, null);
		}
	}
	
}
