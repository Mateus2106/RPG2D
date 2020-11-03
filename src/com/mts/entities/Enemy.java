package com.mts.entities;

import java.awt.image.BufferedImage;

import com.mts.main.Game;
import com.mts.world.World;

public class Enemy extends Entity{
	
	private double speed = 1;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void tick() {
		if((int)x < Game.player.getX() && World.isFree((int)(x + speed), (int)y)) {
			x += speed;
		}else if((int)x > Game.player.getX() && World.isFree((int)(x - speed), (int)y)) {
			x -= speed;
		}else if((int)y < Game.player.getY() && World.isFree((int)x, (int)(y + speed))) {
			y += speed;
		}else if((int)y > Game.player.getY() && World.isFree((int)x, (int)(y - speed))) {
			y -= speed;
		}
	}
}
