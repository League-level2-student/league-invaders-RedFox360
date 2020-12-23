package leagueInvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Timer frameDraw;
	Font titleFont;
	Font normalFont;
	Rocketship rocketShip;
	ObjectManager objectManager;
	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 44);
		normalFont = new Font("Arial", Font.PLAIN, 24);
		frameDraw = new Timer(1000 / 60, this);
		frameDraw.start();
		rocketShip = new Rocketship(250, 700, 50, 50);
		objectManager = new ObjectManager(rocketShip);
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU) {
			drawMenuState(g);
		} else if (currentState == GAME) {
			drawGameState(g);
		} else if (currentState == END) {
			drawEndState(g);
		}
	}

	void updateMenuState() {

	}

	void updateGameState() {
		objectManager.update();
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 40, 50);
		g.setFont(normalFont);
		g.drawString("Press ENTER to start", 130, 300);
		g.drawString("Press SPACE for instructions", 100, 600);
	}

	void drawGameState(Graphics g) {
		objectManager.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setColor(Color.YELLOW);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 100, 50);
		g.setFont(normalFont);
		g.drawString("You killed enemies", 130, 300);
		g.drawString("Press ENTER to restart", 100, 600);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentState == MENU) {
			updateMenuState();
		} else if (currentState == GAME) {
			updateGameState();
		} else if (currentState == END) {
			updateEndState();
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (currentState == END) {
				currentState = MENU;
			} else {
				currentState++;
			}
		}
		if (currentState == GAME) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (rocketShip.y > 0) {
					rocketShip.up();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (rocketShip.y < 800) {
					rocketShip.down();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (rocketShip.x > 10) {
					rocketShip.left();
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (rocketShip.x < 450) {
					rocketShip.right();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
