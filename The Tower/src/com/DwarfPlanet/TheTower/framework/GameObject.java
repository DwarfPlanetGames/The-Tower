package com.DwarfPlanet.TheTower.framework;


import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	public float x;
	public float y;
	public float velX = 0, velY = 0;
	public float width, height;
	public ObjectId id;
	
	public GameObject(float x, float y,float width,float height, ObjectId id ){
		this.x = x;
		this.y = y;
		this.id = id;
		this.width = width;
		this.height = height;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render();
	
	public float getX(){
		return x;
	}
	public void setX(float x){
		this.x = x;
	}
	public float getY(){
		return y;
	}
	public void setY(float y){
		this.y = y;
	}
	public float getVelX(){
		return velX;
	}
	public void setVelX(float velX){
		this.velX = velX;
	}
	public float getVelY(){
		return velY;
	}
	public void setVelY(float velY){
		this.velY = velY;
	}
	public ObjectId getId(){
		return id;
	}
	public Rectangle getBounds() {
		Rectangle r = new Rectangle();
		r.x=(int)x;r.y=(int)y;
		r.width=(int)width;r.height=(int)height;
		return r;
	}
	public Rectangle getBounds(Side s, int pinch) {
		int v = pinch;
		if (s == Side.top) {
			return new Rectangle((int) x + v, (int) y, (int) width - 2*v, (int) height / 2);
		}
		if (s == Side.bottom) {
			return new Rectangle((int) x + v, (int) y + (int) width / 2, (int) width - 2*v, (int) height / 2);
		}
		if (s == Side.left) {
			return new Rectangle((int) x, (int) y + v, (int) v, (int) height - 2*v);
		}
		if (s == Side.right) {
			return new Rectangle((int) x + (int) width - v, (int) y + v, (int) v, (int) height - 2*v);
		}
		throw new NullPointerException("Couldn't generate one of the four rectangles: top, bottom, left, right.");
	}

}
