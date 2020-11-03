package com.mts.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mts.main.Game;
import com.mts.world.Camera;
import com.mts.world.World;

public class Player extends Entity{
	
	public boolean right, left, up, down;
	public double speed = 1.2;
	private int frames = 0, maxFrames = 10, index = 0, maxIndex = 3;
	private boolean moved = false;
	private int down_dir = 0, up_dir = 1, right_dir = 2, left_dir = 3;
	private int dir = down_dir;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		downPlayer = new BufferedImage[4];
		rightPlayer[0] = Game.spritesheet.getSprite(8, 100, 15, 24);
		rightPlayer[1] = Game.spritesheet.getSprite(40, 100, 15, 24);
		rightPlayer[2] = Game.spritesheet.getSprite(8, 100, 15, 24);
		rightPlayer[3] = Game.spritesheet.getSprite(72, 100, 15, 24);
		leftPlayer[0] = Game.spritesheet.getSprite(8, 68, 15, 24);
		leftPlayer[1] = Game.spritesheet.getSprite(40, 68, 15, 24);
		leftPlayer[2] = Game.spritesheet.getSprite(8, 68, 15, 24);
		leftPlayer[3] = Game.spritesheet.getSprite(72, 68, 15, 24);
		upPlayer[0] = Game.spritesheet.getSprite(8, 36, 16, 24);
		upPlayer[1] = Game.spritesheet.getSprite(40, 36, 16, 24);
		upPlayer[2] = Game.spritesheet.getSprite(8, 36, 16, 24);
		upPlayer[3] = Game.spritesheet.getSprite(72, 36, 16, 24);
		downPlayer[0] = Game.spritesheet.getSprite(8, 4, 16, 24);
		downPlayer[1] = Game.spritesheet.getSprite(40, 4, 16, 24);
		downPlayer[2] = Game.spritesheet.getSprite(8, 4, 16, 24);
		downPlayer[3] = Game.spritesheet.getSprite(72, 4, 16, 24);
	}
	
	public void tick() {
		moved = false;
		if(up && World.isFree((int)x, (int)(y - speed))) {
			moved = true;
			dir = up_dir;
			y -= speed;	
		}else if(down && World.isFree((int)x, (int)(y + speed))) {
			moved = true;
			dir = down_dir;
			y += speed;
		}else if(right && World.isFree((int)(x + speed), (int)y)) {
			moved = true;
			dir = right_dir;
			x += speed;	
		}else if(left && World.isFree((int)(x - speed), (int)y)) {
			moved = true;
			dir = left_dir;
			x -= speed;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}else{
			index = 0;
		}
		
		Camera.x = Camera.clamp(this.getX() - ((Game.WIDTH/2) + 16), 0, World.WIDTH*32 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - ((Game.HEIGHT/2) + 16), 0, World.HEIGHT*32 - Game.HEIGHT);
	}
	
	public void render(Graphics g) {
		if(dir == down_dir) {
			g.drawImage(downPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else if(dir == up_dir) {
			g.drawImage(upPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else if(dir == right_dir) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else if(dir == left_dir) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
	}
}
