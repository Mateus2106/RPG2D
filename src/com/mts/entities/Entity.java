package com.mts.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mts.main.Game;
import com.mts.world.Camera;

public class Entity {
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	private BufferedImage sprite;
	public static BufferedImage GOBLIN_EN = Game.spritesheet.getSprite(103, 6, 18, 22);
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
}
