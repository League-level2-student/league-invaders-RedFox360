package leagueInvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

public class ObjectManager implements ActionListener {
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	int score = 0;
	Rocketship rocketShip;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();

	ObjectManager(Rocketship r) {
		rocketShip = r;
		if (needImage) {
		    loadImage ("space.png");
		}
	}

	void addAlien() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}
	void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	public void update() {
		for (Iterator<Alien> iterator = aliens.iterator(); iterator.hasNext();) {
			Alien alien = iterator.next();
			alien.update();
			if (alien.y > LeagueInvaders.HEIGHT) {
				alien.isActive = false;
			}
		}
		for (Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext();) {
			Projectile projectile = iterator.next();
			projectile.update();
			if (projectile.y > LeagueInvaders.HEIGHT) {
				projectile.isActive = false;
			}
		}
		checkCollision();
		purgeObjects();
	}
	int getScore() {
		return this.score;
	}
	public void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		rocketShip.draw(g);
		for (Iterator<Alien> iterator = aliens.iterator(); iterator.hasNext();) {
			Alien alien = iterator.next();
			alien.draw(g);
			
		}
		for (Iterator<Projectile> iterator = projectiles.iterator(); iterator.hasNext();) {
			Projectile projectile = iterator.next();
			projectile.draw(g);
		}
	}

	public void purgeObjects() {
		for (int i = aliens.size() - 1; i >= 0; i--) {
			Alien alien = aliens.get(i);
			if (!alien.isActive) {
				aliens.remove(i);
			}
		}
		for (int i = projectiles.size() - 1; i >= 0; i--) {
			Projectile projectile = projectiles.get(i);
			if (!projectile.isActive) {
				projectiles.remove(i);
			}
		}
	}

	void loadImage(String imageFile) {
		if (needImage) {
			try {
				image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
				gotImage = true;
			} catch (Exception e) {

			}
			needImage = false;
		}
	}
	public void checkCollision() {
		for (Iterator<Alien> iterator = aliens.iterator(); iterator.hasNext();) {
			Alien alien = iterator.next();
			rocketShip.collisionBox.intersects(alien.collisionBox);
			if (rocketShip.collisionBox.intersects(alien.collisionBox)) {
				rocketShip.isActive = false;
				alien.isActive = false;
			}
			for (Iterator<Projectile> jterator = projectiles.iterator(); jterator.hasNext();) {
				Projectile projectile = jterator.next();
				if (projectile.collisionBox.intersects(alien.collisionBox)) {
					projectile.isActive = false;
					alien.isActive = false;
					score += 1;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		addAlien();
	}
}
