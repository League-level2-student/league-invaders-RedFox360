package leagueInvaders;

import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject {
	Rocketship(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 10;
	}
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, 50, 50);
	}
	void right() {
		x+=speed;
	}
	void left() {
		x-=speed;
	}
	void up() {
		y-=speed;
	}
	void down() {
		y+=speed;
	}
	
}
