package com.mts.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mts.entities.Enemy;
import com.mts.entities.Entity;
import com.mts.main.Game;

public class World {
	
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 32;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			int[] pixels = new int[WIDTH * HEIGHT];
			tiles = new Tile[WIDTH * HEIGHT];
			map.getRGB(0, 0, WIDTH, HEIGHT, pixels, 0, WIDTH);
			for(int x = 0; x < WIDTH; x++) {
				for(int y = 0; y < HEIGHT; y++) {
					int currentPixel = pixels[x + (y * WIDTH)];
					tiles[x + (y * WIDTH)] = new FloorTile(x*32, y*32, Tile.TILE_FLOOR);
					if(currentPixel == 0xFF1E4900) {
						//Floor
						tiles[x + (y * WIDTH)] = new FloorTile(x*32, y*32, Tile.TILE_FLOOR);
					}else if(currentPixel == 0xFF000000) {
						//Wall
						tiles[x + (y * WIDTH)] = new WallTile(x*32, y*32, Tile.TILE_WALL);
					}else if(currentPixel == 0xFF0026FF) {
						//Player
						Game.player.setX(x*32);
						Game.player.setY(y*32);
					}else if(currentPixel == 0xFFFF0000){
						//Enemy
						//Game.entities.add(new Enemy(x*32, y*32, 18, 22, Entity.GOBLIN_EN));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xNext, int yNext) {

		int x1 = xNext / TILE_SIZE;
		int y1 = yNext / TILE_SIZE;
		
		int x2 = (xNext + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;
		
		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext + TILE_SIZE - 1) / TILE_SIZE;
		
		int x4 = (xNext + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (yNext + TILE_SIZE - 1) / TILE_SIZE;
		
		return !(tiles[x1 + (y1*World.WIDTH)] instanceof WallTile || 
				tiles[x2 + (y2*World.WIDTH)] instanceof WallTile ||
				tiles[x3 + (y3*World.WIDTH)] instanceof WallTile ||
				tiles[x4 + (y4*World.WIDTH)] instanceof WallTile);
	}
	
	public void render(Graphics g) {
		int xStart = Camera.x / 32 ;
		int yStart = Camera.y / 32;
		int xFinal = xStart + (Game.WIDTH - (Game.WIDTH / 4));
		int yFinal = yStart + (Game.HEIGHT - (Game.HEIGHT / 4));
		
		for(int x = xStart; x <= xFinal; x++) {
			for(int y = yStart; y <= yFinal; y++) {
				if(x < 0 || y < 0 || x >= WIDTH || y >= HEIGHT) {
					continue;
				}
				Tile tile = tiles[x + (y * WIDTH)];
				tile.render(g);
			}
		}
	}
}
