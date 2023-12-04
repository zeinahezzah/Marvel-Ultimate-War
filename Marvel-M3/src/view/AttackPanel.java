package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import control.GameController;
import engine.Game;
import engine.Player;
import model.world.Direction;

public class AttackPanel extends JPanel implements ActionListener {
	
	private JLabel title;
	private JButton attack;
	private JComboBox options;
	private Direction direction;
	
	
	public AttackPanel(){
		
		this.setLayout(new BorderLayout());
		
		
		//--------------------title------------------
		
		title = new JLabel("Select a direction to attack: ");
		title.setFont(new Font("Arial", Font.BOLD, 25));
		title.setBackground(Color.LIGHT_GRAY);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setPreferredSize(new Dimension(20,50));
		title.setForeground(Color.white);
		
		this.add(title, BorderLayout.NORTH);
		
		
		//------------------direction-----------------
		
		JPanel dir = new JPanel();
		dir.setBackground(new Color(0,0,0,0));
		dir.setLayout(new GridBagLayout());
		
		
		options = new JComboBox(Direction.values());
		options.setEditable(false);
		options.setPreferredSize(new Dimension(300,50));
		options.setFont(new Font("Arial", Font.PLAIN, 20));
		
		options.setOpaque(false);
		
		dir.add(options);
		
		
		
		
		//--------------------attack------------------
		
		JPanel atk = new JPanel();
		atk.setOpaque(false);
		atk.setLayout(new GridBagLayout());
		atk.setPreferredSize(new Dimension(20,80));
		
		
		attack = new JButton();
		attack.setFocusable(false);
				
		attack.setBorderPainted(false);
		attack.setContentAreaFilled(false);
		Image endImg = new ImageIcon("assets/attack.png").getImage();
		attack.setIcon(new ImageIcon(endImg.getScaledInstance(180, 90, Image.SCALE_SMOOTH)));
	
		
		
		attack.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				direction = (Direction)options.getSelectedItem();
				GameController.attack(direction);
			}
			
		});
		
		atk.add(attack);
		
		
		
		this.add(dir);
		this.add(atk, BorderLayout.SOUTH);
		
		this.repaint();
		this.revalidate();
		this.setVisible(true);
		
		
		
	}
	
	
	
	
	public JLabel getTitle() {
		return title;
	}




	public JButton getAttack() {
		return attack;
	}




	public JComboBox getDirection() {
		return options;
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
		
		f.add(new AttackPanel());
		f.setVisible(true);
	}




	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
