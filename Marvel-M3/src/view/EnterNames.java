package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import engine.Game;
import engine.Player;

public class EnterNames extends JPanel{
	
	private JTextField firstPlayer;
	private JTextField secondPlayer;
	
	private JLabel bg;
	private JButton next;
	
	private JPanel names;
	
	private String p1name;
	private String p2name;
		
	
	public EnterNames(){
		Toolkit t = Toolkit.getDefaultToolkit();
		int x = (int) t.getScreenSize().getWidth();
		int y = (int) t.getScreenSize().getHeight();
		
		this.setLayout(new BorderLayout());
		
		//this.setBackground(Color.gray);
		ImagePanel img = new ImagePanel("bg.jpg",x,y);
		img.setLayout(new BorderLayout());
		this.add(img);
		
//		names = new JPanel();
//		names.setLayout(new GridBagLayout());
//		names.setBackground(new Color(0,0,0,0));
//		names.setBounds(0, 0, 200, 600);
//		names.setPreferredSize(new Dimension(50,200));
		
		JLabel enterNames = new JLabel("Enter Your Names Below:");
		enterNames.setFont(new Font("Serif", Font.BOLD, 70));
		//enterNames.setBackground(Color.BLACK);
		//enterNames.setOpaque(true);
		enterNames.setPreferredSize(new Dimension(1000,300));
		enterNames.setHorizontalAlignment(JLabel.CENTER);
		enterNames.setVerticalAlignment(JLabel.BOTTOM);
		enterNames.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		enterNames.setForeground(Color.white);
		
	//	names.add(enterNames);
		
		img.add(enterNames, BorderLayout.NORTH);
		
//		ImageIcon img = new ImageIcon("bg2.jpg");
//		bg = new JLabel();
//		bg.setSize(x,y);
//		bg.setIcon(img);
//		bg.setOpaque(true);
//		bg.setVisible(true);
//		//bg.setBounds(0,0,x,y);
//		bg.setVerticalAlignment(JLabel.CENTER);
//		bg.setHorizontalAlignment(JLabel.CENTER);
//		this.add(bg);
		
		
		JPanel center = new JPanel();
		center.setOpaque(false);
		center.setBackground(new Color(0,0,0,0));
		center.setLayout(new GridBagLayout());
		
		JPanel boxes  = new JPanel();
		boxes.setOpaque(false);
		boxes.setLayout(new GridLayout(2,0,10,10));
		boxes.setPreferredSize(new Dimension(1000, 200));
		boxes.setBackground(new Color(0,0,0,0));
		
		JPanel player1 = new JPanel();
		player1.setOpaque(false);
		player1.setBackground(new Color(0,0,0,0));
		
		JPanel player2 = new JPanel();
		player2.setOpaque(false);
		player2.setBackground(new Color(0,0,0,0));
		
		firstPlayer = new JTextField();
		secondPlayer = new JTextField();
		
		firstPlayer.setPreferredSize(new Dimension(500,40));
		secondPlayer.setPreferredSize(new Dimension(500,40));
		
		//firstPlayer.setBounds(x/2-340, y/2-300, 500, 40);
		//secondPlayer.setBounds(x/2-340, y/2-240, 500, 40);
		
		firstPlayer.setFont(new Font("Arial", Font.PLAIN, 25));
		secondPlayer.setFont(new Font("Arial", Font.PLAIN, 25));
		
		firstPlayer.setMargin(new Insets(0,10,0,0));
		secondPlayer.setMargin(new Insets(0,10,0,0));
		
		firstPlayer.setVisible(true);
		secondPlayer.setVisible(true);
		
		JLabel p1 = new JLabel("Player 1: ");
		//p1.setBounds(x/2-500, y/2-300, 200, 40);
		p1.setFont(new Font("Serif", Font.BOLD, 40));
		p1.setForeground(Color.white);
		p1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		JLabel p2 = new JLabel("Player 2: ");
		//p2.setBounds(x/2-500, y/2-240, 200, 40);
		p2.setFont(new Font("Serif", Font.BOLD, 40));
		p2.setForeground(Color.white);
		p2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		player1.add(p1);
		player1.add(firstPlayer);
		player2.add(p2);
		player2.add(secondPlayer);
		
		boxes.add(player1);
		boxes.add(player2);
		
		center.add(boxes);
		
		img.add(center);
		
		
		JPanel nextPane = new JPanel();
		nextPane.setOpaque(false);
		//nextPane.setBackground(new Color(0,0,0,0));
		nextPane.setPreferredSize(new Dimension(200,300));
		//nextPane.setLayout(new GridBagLayout());
		
		next = new JButton();
	//	next.setBounds(700, y/2-40, 200, 40);
		next.setFocusable(false);
		next.setBorderPainted(false);
		next.setContentAreaFilled(false);
		
		//next.setFont(new Font("Verdana", Font.BOLD, 30));
		next.setVerticalAlignment(JButton.NORTH);
		next.setHorizontalAlignment(JButton.CENTER);
		next.setPreferredSize(new Dimension(350,150));
		//next.setOpaque(false);
		//next.setBackground(new Color(0,0,0,0));
		
		Image nextImg = new ImageIcon("nextB.png").getImage();
		next.setIcon(new ImageIcon(nextImg.getScaledInstance(250, 150, Image.SCALE_SMOOTH)));
	
		
		
		nextPane.add(next);
		
		img.add(nextPane, BorderLayout.SOUTH);
		
		p1name = firstPlayer.getText();
		p2name = secondPlayer.getText();
	
		this.repaint();
		this.revalidate();
		
		this.setVisible(true);
	}
	
	public JTextField getFirstPlayer() {
		return firstPlayer;
	}

	public JTextField getSecondPlayer() {
		return secondPlayer;
	}

	public JLabel getBg() {
		return bg;
		
	}

	public JButton getNext() {
		return next;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setSize(1600,900);
		
//		Game g = new Game(new Player("Zeina"), new Player("Rowaida"));
//		try {
//			g.loadChampions("Champions.csv");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		f.add(new EnterNames());
		f.setVisible(true);
	}
	

}
