package leagueInvaders;

import java.awt.Color;
import java.awt.Graphics;

public class Alien extends GameObject {
	int speed;
	Alien(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 1;
	}
	public void update() {
		y+=speed;
	}
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y,  width, height);
	}
	
}
