package com.DwarfPlanet.TheTower.framework;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
	Handler handler;
	public static boolean[] keys = new boolean[68800];
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		keys[e.getKeyCode()] = true;
		if(keys[KeyEvent.VK_ESCAPE])
			System.exit(0);
		
	}
	public void keyReleased(KeyEvent e){
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
