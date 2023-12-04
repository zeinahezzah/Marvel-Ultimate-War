package view;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import engine.Game;
import engine.Player;
import model.world.Champion;

public class ChampAbilities extends JPanel {

	private JLabel title;
	private JPanel abilities;
	
	private JPanel ability1;
	private JPanel ability2;
	private JPanel ability3;
	
	private static Champion c;

	public ChampAbilities(Champion c){
		
		this.setLayout(new BorderLayout());
		
		this.setBackground(new Color(0,0,0,0));
		this.setPreferredSize(new Dimension(500,200));
		
		
		
		//-----------------------title----------------------
		
		title = new JLabel("Champion Abilities: ");
		title.setFont(new Font("Arial", Font.BOLD, 25));
		title.setPreferredSize(new Dimension(20,30));
		title.setBackground(new Color(0,0,0,0));
		title.setForeground(Color.white);
		title.setOpaque(true);
		title.setHorizontalAlignment(JLabel.CENTER);
		
		
		this.add(title, BorderLayout.NORTH);
		
		//----------------------------------------------------
		
		
		
		
		
		//--------------------abilities----------------------
		
		
		abilities = new JPanel();
		abilities.setBackground(new Color(0,0,0,0));
		abilities.setLayout(new GridLayout(3,0,0,0));
		abilities.setPreferredSize(new Dimension(300,190));
		
		for(int i = 0; i<c.getAbilities().size(); i++){
			
			abilities.add(new AbilityPanel(c.getAbilities().get(i),i));
			
		}
		
		
		this.add(abilities);
		
		
		
		this.repaint();
		this.revalidate();
		this.setVisible(true);
		
	}

	public ChampAbilities(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ChampAbilities(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public ChampAbilities(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500,500);
		
		Game g = new Game(new Player("Rowaida"), new Player("Zeina"));
		try {
			g.loadAbilities("assets/Abilities.csv");
			g.loadChampions("assets/Champions.csv");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Player p1 = new Player("s");
		Player p2 = new Player("x");
		
		p1.getTeam().add(g.getAvailableChampions().get(0));
		p1.getTeam().add(g.getAvailableChampions().get(1));
		p1.getTeam().add(g.getAvailableChampions().get(2));
		
		p1.setLeader(p1.getTeam().get(0));
		
		p2.getTeam().add(g.getAvailableChampions().get(3));
		p2.getTeam().add(g.getAvailableChampions().get(4));
		p2.getTeam().add(g.getAvailableChampions().get(5));
		
		p2.setLeader(p2.getTeam().get(0));
		
		
		
		Game g2 = new Game(p1, p2);
		Board b = new Board(g2);
		
		f.add(new ChampAbilities(g2.getCurrentChampion()));
		f.setVisible(true);
	}
	
	
	public static void update(){
		
	}

}
