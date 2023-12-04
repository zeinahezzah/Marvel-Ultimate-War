package control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.abilities.Ability;
import model.abilities.AreaOfEffect;
import model.world.*;
import engine.*;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.GameActionException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
import view.Cell;
import view.ChampAbilities;
import view.ChampionsPage;
import view.CurrChData;
import view.EnterNames;
import view.GamePage;
import view.LoadingPage;
import view.MainView;
import view.HomePage;
import view.TeamPage;
import view.WinPage;

public class GameController {
	
	private static Game game;
	private static MainView view;
	private static Player p1;
	private static Player p2;
	private static Clip next;
	

	public GameController() {
		view = new MainView();
	}
	
	public static void enterNames(){
		
		String soundName = "assets/click.wav";    
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			next = AudioSystem.getClip();
			next.open(audioInputStream);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		next.start();
		
		view.getHome().setVisible(false);
		EnterNames names = new EnterNames();
		names.getNext().addActionListener(view);
		names.getFirstPlayer().addActionListener(view);
		names.getSecondPlayer().addActionListener(view);
		
		view.setNames(names);
		view.getNames().setVisible(true);
		view.add(names, BorderLayout.CENTER);
	}
	
	
	public static void getNames(){
		
		String soundName = "assets/click.wav";    
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			next = AudioSystem.getClip();
			next.open(audioInputStream);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		next.start();
		p1 = new Player(view.getNames().getFirstPlayer().getText());
		p2 = new Player(view.getNames().getSecondPlayer().getText());
		
		game = new Game(p1,p2);
		try {
			game.loadAbilities("assets/Abilities.csv");
			game.loadChampions("assets/Champions.csv");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		view.getNames().setVisible(false);
		ChampionsPage champ = new ChampionsPage(game);
		champ.getNext().addActionListener(view);
		view.setChamps(champ);
		view.getChamps().setVisible(true);
		
		view.add(champ, BorderLayout.CENTER);
		JOptionPane.showMessageDialog(null, p1.getName() + ", Select your champions", "Player 1", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	public static void selectChamps(){
		
		String soundName = "assets/click.wav";    
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
			next = AudioSystem.getClip();
			next.open(audioInputStream);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		next.start();

		game = view.getChamps().getGame();
		p1 = game.getFirstPlayer();
		p2 = game.getSecondPlayer();
		
		if(p1.getTeam().size() < 3 || p2.getTeam().size() < 3){
			JOptionPane.showMessageDialog(null, "Please Select Remaining Champions", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		
		if(p2.getTeam().size() == 3){
			view.getChamps().setVisible(false);
			TeamPage team = new TeamPage(game);
			team.setVisible(true);
			team.getNext().addActionListener(view);
			view.setTeam(team);
			view.add(team);
		}
	}
	
	public static void startGame(){
		
			
			game = view.getTeam().getG();
			p1 = game.getFirstPlayer();
			p2 = game.getSecondPlayer();
			game.prepareChampionTurns();
			
		
			if(p1.getLeader() == null || p2.getLeader() == null){
				JOptionPane.showMessageDialog(null, "Please Select Your Leaders", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
			else{
				String soundName = "assets/click.wav";    
				AudioInputStream audioInputStream;
				try {
					audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
					Clip nextC = AudioSystem.getClip();
					nextC.open(audioInputStream);
					nextC.start();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			
			view.getTeam().setVisible(false);
			GamePage gamePage = new GamePage(game);
			gamePage.setVisible(true);
			view.setGamePage(gamePage);
			view.add(gamePage);
		
		}

	}
	
	
	public static void move(Direction d){
		try {
			game.move(d);
			view.updateBoard();
			
			view.updateData();
			view.getGamePage().updateCurrData();
			
		} catch (NotEnoughResourcesException e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Not Enough Resources", JOptionPane.ERROR_MESSAGE);
			
		} catch (UnallowedMovementException e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Unallowed Movement", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	public static void attack(Direction d){
		try {
			game.attack(d);
			view.updateBoard();
			view.updateData();
			view.getGamePage().updateCurrData();
			
			
			if(game.checkGameOver() != null){
				view.getGamePage().setVisible(false);
				WinPage winner = new WinPage(game.checkGameOver());
				winner.setVisible(true);
				view.setWinPage(winner);
				view.add(winner);
			}
			
		} catch (NotEnoughResourcesException e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Not Enough Resources", JOptionPane.ERROR_MESSAGE);
		} catch (ChampionDisarmedException e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Unallowed Movement", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void useLeaderAbility(){

			try {
				game.useLeaderAbility();
				view.updateData();
				view.updateBoard();
				view.getGamePage().updateCurrData();
				
				if(game.checkGameOver() != null){
					view.getGamePage().setVisible(false);
					WinPage winner = new WinPage(game.checkGameOver());
					winner.setVisible(true);
					view.setWinPage(winner);
					view.add(winner);
				}
				
			} catch (LeaderNotCurrentException e) {
				String soundName = "assets/error.wav";    
				AudioInputStream audioInputStream;
				try {
					audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
					next = AudioSystem.getClip();
					next.open(audioInputStream);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				next.start();
				JOptionPane.showMessageDialog(null, e.getMessage(), "Not Leader", JOptionPane.ERROR_MESSAGE);

			} catch (LeaderAbilityAlreadyUsedException e) {
				String soundName = "assets/error.wav";    
				AudioInputStream audioInputStream;
				try {
					audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
					next = AudioSystem.getClip();
					next.open(audioInputStream);
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				next.start();
				JOptionPane.showMessageDialog(null, e.getMessage(), "Ability Already Used", JOptionPane.ERROR_MESSAGE);
			}	
	}
	
	
	public static void castAbilityDir(Ability a, Direction d){
		try {
			game.castAbility(a, d);
			view.updateBoard();
			view.updateData();
			view.getGamePage().updateCurrData();
			
			if(game.checkGameOver() != null){
				view.getGamePage().setVisible(false);
				WinPage winner = new WinPage(game.checkGameOver());
				winner.setVisible(true);
				view.setWinPage(winner);
				view.add(winner);
			}
			
		} catch (NotEnoughResourcesException e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Not Enough Resources", JOptionPane.ERROR_MESSAGE);
		} catch (InvalidTargetException e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Target", JOptionPane.ERROR_MESSAGE);
		} catch (AbilityUseException e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Can't Use Ability", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void castAbilityST(Ability a, int x, int y){
		
		try {
			game.castAbility(a, x, y);
			view.updateBoard();
			view.updateData();
			view.getGamePage().updateCurrData();
			
			if(game.checkGameOver() != null){
				view.getGamePage().setVisible(false);
				WinPage winner = new WinPage(game.checkGameOver());
				winner.setVisible(true);
				view.setWinPage(winner);
				view.add(winner);
			}
			
		} catch (NotEnoughResourcesException e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Not Enough Resources", JOptionPane.ERROR_MESSAGE);

		} catch (InvalidTargetException e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Target", JOptionPane.ERROR_MESSAGE);

		} catch (AbilityUseException e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Can't Use Ability", JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void castAbility(Ability a){
		try {
			game.castAbility(a);
			view.updateBoard();
			view.updateData();
			view.getGamePage().updateCurrData();
			
			if(game.checkGameOver() != null){
				view.getGamePage().setVisible(false);
				WinPage winner = new WinPage(game.checkGameOver());
				winner.setVisible(true);
				view.setWinPage(winner);
				view.add(winner);
			}
			
			
		} catch (AbilityUseException e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Can't Use Ability", JOptionPane.ERROR_MESSAGE);

		} catch (NotEnoughResourcesException e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Not Enough Resources", JOptionPane.ERROR_MESSAGE);

		} catch (InvalidTargetException e) {
			String soundName = "assets/error.wav";    
			AudioInputStream audioInputStream;
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
				next = AudioSystem.getClip();
				next.open(audioInputStream);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			next.start();
			JOptionPane.showMessageDialog(null, e.getMessage(), "Invalid Target", JOptionPane.ERROR_MESSAGE);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void endTurn(){
		game.endTurn();
		
		view.getGamePage().setG(game);
		
	
			view.getGamePage().updateCurrData();
			view.getGamePage().updateChAbilities();
			view.getGamePage().updatePQ();
	
		
		view.repaint();
		view.revalidate();
		
	}
	
	public static void exit(){
		view.dispose();
		view.getClip().stop();
	}


	public static Game getGame() {
		return game;
	}


	public static MainView getView() {
		return view;
	}


	public static Player getP1() {
		return p1;
	}


	public static Player getP2() {
		return p2;
	}
	
	
	public static void main(String[] args) {
		new GameController();
	}

	public static void setGame(Game game) {
		GameController.game = game;
	}

	public static void setView(MainView view) {
		GameController.view = view;
	}

	public static void setP1(Player p1) {
		GameController.p1 = p1;
	}

	public static void setP2(Player p2) {
		GameController.p2 = p2;
	}
	
	
	
	
	
	
	
	

}
