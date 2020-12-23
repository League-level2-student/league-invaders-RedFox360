package leagueInvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ObjectManager {
	Rocketship rocketShip;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	ObjectManager(Rocketship r){
		rocketShip = r;
	}
	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH),0,50,50));
	}
	public void update() {
		for (Iterator<Alien> iterator = aliens.iterator(); iterator.hasNext();) {
			Alien alien = iterator.next();
			alien.update();
			if (alien.y > LeagueInvaders.HEIGHT) {
				alien.isActive = false;
			}
		}
		
	}
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		rocketShip.draw(g);
		for (Iterator<Alien> iterator = aliens.iterator(); iterator.hasNext();) {
			Alien alien = iterator.next();
			alien.draw(g);;
		}
	}
	public void purgeObjects() {
		for (int i = aliens.size() - 1; i >= 0; i--) {
			Alien alien = aliens.get(i);
			if(!alien.isActive) {
				aliens.remove(i);
			}
		}
	}
}
