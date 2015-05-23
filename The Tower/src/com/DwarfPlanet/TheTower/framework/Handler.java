package com.DwarfPlanet.TheTower.framework;


import java.util.LinkedList;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick(){
		for(int i = 0; i < object.size();i++){
			tempObject = object.get(i);
			
			tempObject.tick(object);
		}
	}
	
	public void render(){
		for(int i = 0; i < object.size(); i++){
			tempObject = object.get(i);
			
			tempObject.render();
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	

}
