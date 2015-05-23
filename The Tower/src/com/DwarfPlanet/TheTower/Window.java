package com.DwarfPlanet.TheTower;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	public static int scale = 2;
	
	public Window(int w, int h, String title,Game game){
		
		game.setPreferredSize(new Dimension(w,h));
		game.setMaximumSize(new Dimension(w,h));
		game.setMinimumSize(new Dimension(w,h));
		
		JFrame frame = new JFrame(title);
		Game.width = w/ scale;
		Game.height = h /scale;
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		
		game.start();
		
	}
	
}
