package leagueInvaders;


import java.awt.Dimension;

import javax.swing.JFrame;

public class LeagueInvaders {
	JFrame f;
	GamePanel panel;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	public static void main(String[] args) {
		LeagueInvaders l = new LeagueInvaders();
		l.setup();
	}
	LeagueInvaders() {
		f = new JFrame();
		panel = new GamePanel();
	}
	public void setup() {
		f.setVisible(true);
		f.setSize(new Dimension(WIDTH, HEIGHT));
		f.add(panel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.addKeyListener(panel);
	}
} 
