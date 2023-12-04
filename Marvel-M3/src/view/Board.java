package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Color;

import javax.swing.*;

import engine.*;
import model.world.*;

public class Board extends JPanel{

	private Cell[][] map;
	private static Game g;
	
	public Board(Game g)  {

		map = new Cell[5][5];
		
		this.g = g;
				
		this.setLayout(new GridLayout(5,5));
		g.placeChampions();
		
		for(int i = 0; i < g.getBoard().length; i++){
			for(int j = 0; j<g.getBoard()[i].length; j++){
				Cell c = new Cell();
				if(g.getBoard()[i][j] instanceof Damageable){
					Damageable d = (Damageable)g.getBoard()[i][j];
					c = new Cell();
					c.addChampion(d);
				}
				
				
				map[i][j] = c;
				
				this.add(map[i][j]); // adds cell to the world panel 		
				c.addActionListener(c);
			}
		}
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);
		
	}
	
	public Cell[][] getMap() {
		return map;
	}

	public static Game getG() {
		return g;
	}

	
	//TESTING
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1600,900);
		
		Game g = new Game(new Player("Zeina"), new Player("Rowaida"));
		try {
			g.loadChampions("assets/Champions.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.getFirstPlayer().getTeam().add(g.getAvailableChampions().get(0));
		g.getFirstPlayer().getTeam().add(g.getAvailableChampions().get(1));
		g.getFirstPlayer().getTeam().add(g.getAvailableChampions().get(2));
		
		g.getSecondPlayer().getTeam().add(g.getAvailableChampions().get(3));
		g.getSecondPlayer().getTeam().add(g.getAvailableChampions().get(4));
		g.getSecondPlayer().getTeam().add(g.getAvailableChampions().get(5));
		
		f.add(new Board(g)); 
		
		f.setVisible(true);
	}
	
	
	public void updateBoard(){
		
		for(int i = 0; i < g.getBoard().length; i++){
			for(int j = 0; j<g.getBoard()[i].length; j++){
				//Cell c = new Cell();
				if(g.getBoard()[i][j] instanceof Damageable){
					Damageable d2 = (Damageable)g.getBoard()[i][j];
					map[i][j].addChampion(d2);
				}
				else if(g.getBoard()[i][j] == null){
					map[i][j].clear();
				}
			}
		}
	}

}
