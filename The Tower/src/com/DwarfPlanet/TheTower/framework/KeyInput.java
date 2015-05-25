package com.DwarfPlanet.TheTower.framework;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.DwarfPlanet.TheTower.Game;
import com.DwarfPlanet.TheTower.Window;

public class KeyInput implements KeyListener, MouseListener, MouseMotionListener {
	Handler handler;
	public static boolean[] keys = new boolean[68800];
	public static Point mouse;
	public static Point camMouse;
	public static boolean[] mouseButtons = new boolean[5];
	
	public KeyInput(Handler handler){
		this.handler = handler;
		mouse = new Point(0,0);
		camMouse = new Point(0,0);
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
		mouse.x /= Window.scale;
		mouse.y /= Window.scale;
		camMouse.x = mouse.x + Game.camX;
		camMouse.y = mouse.y + Game.camY;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouse.x = e.getX();
		mouse.y = e.getY();
		mouse.x /= Window.scale;
		mouse.y /= Window.scale;
		camMouse.x = mouse.x + Game.camX;
		camMouse.y = mouse.y + Game.camY;		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseButtons[e.getButton()] = true;
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseButtons[e.getButton()] = false;
		
	}
}
