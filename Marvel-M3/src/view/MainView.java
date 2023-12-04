package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import control.GameController;
import engine.Game;
import engine.Player;

public class MainView extends JFrame implements ActionListener{
	
	private static HomePage home;
	private static EnterNames names;
	private static Player p1;
	private static Player p2;
	private static ChampionsPage champs;
	private static TeamPage team;
	private static LoadingPage load;
	private static GamePage gamePage;
	private static WinPage winPage;
	private static GameController control;
	private static Clip clip;

	
	private static Color c = new Color(0xB80523);
	
	public MainView(){
		
	GraphicsEnvironment graphics =  GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice device = graphics.getDefaultScreenDevice();

	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("Marvel: Ultimate-War");
	
	Toolkit t = Toolkit.getDefaultToolkit();
	int x = (int) t.getScreenSize().getWidth();
	int y = (int) t.getScreenSize().getHeight();
	
	this.setSize(x,y);
	
	ImageIcon img = new ImageIcon("assets/logo.png");
	this.setIconImage(img.getImage());
	
	
	home = new HomePage();
	home.getStart().addActionListener(this);
	this.add(home);
	
	
	try{
    	File file = new File("assets/theme.wav");
    	AudioInputStream sound = AudioSystem.getAudioInputStream(file);
    	clip = AudioSystem.getClip();
    	clip.open(sound);
    	clip.loop(Clip.LOOP_CONTINUOUSLY);
    	clip.start();
    	
    } catch(Exception ex){
    	System.out.println("error");
    }
	
	

//	device.setFullScreenWindow(this);
	
	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	
	//this.pack();
	this.revalidate();
	this.repaint();
	this.setVisible(true);
	
	}
	
	
	
	
	public static Clip getClip() {
		return clip;
	}




	public static void updateBoard(){
		gamePage.getBoard().updateBoard();
		
	}
	
	public static void updateData(){
		gamePage.updatePlayerData();
	}
	
	
	public static void main(String[] args) {
		JFrame main = new MainView();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == home.getStart()){
			control.enterNames();
			
		}
		
		else if(e.getSource() == names.getNext()){
			GameController.getNames();
		}
		
		
		else if(e.getSource() == champs.getNext()){
			GameController.selectChamps();

		}
		
		else if(e.getSource() == team.getNext()){
			
			GameController.startGame();
//			LoadingPage load = new LoadingPage();
//			load.fill();
			
		}
		
	}


	public HomePage getHome() {
		return home;
	}
	
	
	

	public ChampionsPage getChamps() {
		return champs;
	}


	public static GameController getControl() {
		return control;
	}


	public static Color getC() {
		return c;
	}


	public EnterNames getNames() {
		return names;
	}


	public Player getP1() {
		return p1;
	}


	public Player getP2() {
		return p2;
	}


	public TeamPage getTeam() {
		return team;
	}


	public GamePage getGamePage() {
		return gamePage;
	}


	public void setHome(HomePage home) {
		this.home = home;
	}


	public void setNames(EnterNames names) {
		this.names = names;
	}


	public void setP1(Player p1) {
		this.p1 = p1;
	}


	public void setP2(Player p2) {
		this.p2 = p2;
	}


	public void setChamps(ChampionsPage champs) {
		this.champs = champs;
	}


	public void setTeam(TeamPage team) {
		this.team = team;
	}


	public static WinPage getWinPage() {
		return winPage;
	}


	public static void setWinPage(WinPage winPage) {
		MainView.winPage = winPage;
	}


	public void setGamePage(GamePage gamePage) {
		this.gamePage = gamePage;
	}


	public static void setC(Color c) {
		MainView.c = c;
	}




	public static LoadingPage getLoad() {
		return load;
	}




	public static void setLoad(LoadingPage load) {
		MainView.load = load;
	}
	
	
	
	
}
