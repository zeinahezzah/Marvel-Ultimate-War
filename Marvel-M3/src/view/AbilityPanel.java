package view;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.*;

import control.GameController;
import engine.Game;
import engine.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.abilities.Ability;
import model.abilities.AreaOfEffect;

public class AbilityPanel extends JPanel {
	
	private JLabel name;
	private JPanel buttons;
	private JButton details;
	private JButton use;
	

	public AbilityPanel(final Ability a, int i) {
		
		
		this.setBackground(new Color(0,0,0,0));
	
		
		//----------------------name--------------------
		
		name = new JLabel(a.getName());
		name.setFont(new Font("Copperplate", Font.BOLD, 17));
		
		ImageIcon img = null;
		
		switch(i){
			case 0: img = new ImageIcon("assets/ability1.jpg"); break;
			case 1: img = new ImageIcon("assets/ability2.jpg"); break;
			case 2: img = new ImageIcon("assets/ability3.jpg"); break;
		}
		
		Image image = img.getImage();
		img = new ImageIcon(image.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		
		name.setIcon(img);
		name.setIconTextGap(20);
		name.setForeground(Color.white);
		
		
		//----------------buttons-----------------
		
	//	
		
		details = new JButton("View Details");
		details.setFont(new Font("Arial", Font.BOLD, 13));
		details.setForeground(Color.black);
		details.setPreferredSize(new Dimension(115,25));
		details.setFocusable(false);
		details.setBackground(Color.WHITE);
		details.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AbilityDetails(a);
				
			}
			
		});
		
		
		use = new JButton("Use Ability");
		use.setFont(new Font("Arial", Font.BOLD, 13));
		use.setPreferredSize(new Dimension(115,25));
		use.setBackground(Color.white);
		use.setFocusable(false);
		
		use.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(a.getCastArea().equals(AreaOfEffect.DIRECTIONAL))
					new DirectionFrame(a);
				
				else if (a.getCastArea().equals(AreaOfEffect.SINGLETARGET))
					new STFrame(a);
				
				else{
					GameController.castAbility(a);
				}
			}
			
		});
		
		this.add(name);
		this.add(details);
		this.add(use);
		
		this.repaint();
		this.revalidate();
		this.setVisible(true);
		
		
		
		
	}

	public AbilityPanel(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public AbilityPanel(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public AbilityPanel(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400,300);
		
		Game g = new Game(new Player("Rowaida"), new Player("Zeina"));
		try {
			g.loadAbilities("Abilities.csv");
			g.loadChampions("Champions.csv");
			
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
		
		f.add(new AbilityPanel(g2.getCurrentChampion().getAbilities().get(0), 2));
		f.setVisible(true);
	}

}
