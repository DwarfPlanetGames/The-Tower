package com.DwarfPlanet.TheTower.framework;


import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	public float x;
	public float y;
	public float velX = 0, velY = 0;
	public float width, height;
	protected ObjectId id;
	
	public GameObject(float x, float y,float width,float height, ObjectId id ){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render();
	public abstract Rectangle getBounds();
	
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

}
