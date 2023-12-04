package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import control.GameController;
import engine.Game;
import engine.Player;

public class TeamPage extends JPanel {
	private Game g;
	private Color c = new Color(0xB80523);
	private int index;
	private JButton next;
	private JPanel leftM;
	private JPanel rightM;
	private JPanel nextPane;
	private Player p1;
	private Player p2;
	
	private JPanel champs;
	
	public TeamPage(final Game g){
		
		Toolkit t = Toolkit.getDefaultToolkit();
		int x = (int) t.getScreenSize().getWidth();
		int y = (int) t.getScreenSize().getHeight();
		
		this.setLayout(new BorderLayout());
		
		ImagePanel bg = new ImagePanel("names.jpg",x,y);
		bg.setLayout(new BorderLayout());
		
		this.add(bg);
		
		this.g = g;
		p1 = g.getFirstPlayer();
		p2 = g.getSecondPlayer();
		
		
		
		//---------------title-----------------
		
		JLabel title = new JLabel("Choose Your Leaders!");
		title.setForeground(Color.white);
		title.setPreferredSize(new Dimension(20,150));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setOpaque(false);
		title.setFont(new Font("Serif", Font.BOLD, 60));
		
		//-------------------------------------------
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new GridBagLayout());
		
		
		JPanel p2 = new JPanel();
		p2.setOpaque(false);
		p2.setLayout(new BorderLayout());
		panel.add(p2);
		
		//----------------champions----------------------
		
		champs = new JPanel();
		champs.setOpaque(false);
		//champs.setBackground(Color.gray);
		champs.setPreferredSize(new Dimension(800,800));
		champs.setLayout(new GridLayout(3,2, 400, 10));
		//champs.setBorder(BorderFactory.createEmptyBorder(0,0,30,30));
		
		p2.add(champs);
		
		//-------------------------------------------------
		
		//-------------------side margins----------------------
//		
//		leftM = new JPanel();
//		leftM.setPreferredSize(new Dimension(50,20));
//		leftM.setOpaque(false);
//		leftM.setBackground(Color.GRAY);
//		
//		rightM = new JPanel();
//		rightM.setOpaque(false);
//		rightM.setPreferredSize(new Dimension(50,20));
//		rightM.setBackground(Color.GRAY);
//		
//		bg.add(leftM, BorderLayout.WEST);
//		bg.add(rightM, BorderLayout.EAST);
		
		//----------------------------------------------------
		
		
		JPanel names = new JPanel();
		names.setLayout(new GridLayout(0,2, 400, 10));
		names.setOpaque(false);
		names.setPreferredSize(new Dimension(100,100));
		
		//-------------players----------------------------
		
		JLabel player1 = new JLabel(g.getFirstPlayer().getName());
		player1.setForeground(Color.WHITE);
		player1.setHorizontalAlignment(JLabel.CENTER);
		player1.setFont(new Font("Serif", Font.BOLD, 60));
		names.add(player1);
		
		JLabel player2 = new JLabel(g.getSecondPlayer().getName());
		player2.setForeground(Color.WHITE);
		player2.setHorizontalAlignment(JLabel.CENTER);
		player2.setFont(new Font("Serif", Font.BOLD, 60));
		names.add(player2);
		
		p2.add(names, BorderLayout.NORTH);
		//----------------------------------------------
		
		
		final ArrayList<JButton> buttons1 = new ArrayList<JButton>();
		final ArrayList<JButton> buttons2 = new ArrayList<JButton>();
		//-----------------------buttons---------------------------
		
		for(int i = 0; i<g.getFirstPlayer().getTeam().size(); i++){
			final JButton b1 = new JButton();	//g.getFirstPlayer().getTeam().get(i).getName()
			buttons1.add(b1);
			b1.setFont(new Font("Serif", Font.PLAIN, 30));
			b1.setPreferredSize(new Dimension(180,180));
			b1.setFocusable(false);
			b1.setContentAreaFilled(false);
			b1.setBorderPainted(false);
			b1.setOpaque(false);
			
			final Image img = g.getFirstPlayer().getTeam().get(i).getImg().getImage();
			Image icon = g.getFirstPlayer().getTeam().get(i).getIcon().getImage();
			
			b1.setIcon(new ImageIcon(icon.getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
			b1.setIconTextGap(20);
			
			index = i;
			b1.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
			
					if(g.getFirstPlayer().getLeader() == null){
						
						int i = buttons1.indexOf(b1);
						
						g.getFirstPlayer().setLeader(g.getFirstPlayer().getTeam().get(i));
						b1.setIcon(new ImageIcon(img.getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
						//b1.setEnabled(false);
					}
					else{
						JOptionPane.showMessageDialog(null, "You've Already Selected Your Leader", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			});
			
			champs.add(b1);
			final JButton b2 = new JButton();	//g.getSecondPlayer().getTeam().get(i).getName()
			buttons2.add(b2);
			b2.setFont(new Font("Serif", Font.PLAIN, 30));
			b2.setPreferredSize(new Dimension(180,180));
			b2.setOpaque(false);
			b2.setFocusable(false);
			b2.setContentAreaFilled(false);
			b2.setBorderPainted(false);


			
			final Image img2 = g.getSecondPlayer().getTeam().get(i).getImg().getImage();
			Image icon2 = g.getSecondPlayer().getTeam().get(i).getIcon().getImage();

			b2.setIcon(new ImageIcon(icon2.getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
			b2.setIconTextGap(20);
			
			b2.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					int i = buttons2.indexOf(b2);
					
					
					if(g.getSecondPlayer().getLeader() == null){
						g.getSecondPlayer().setLeader(g.getSecondPlayer().getTeam().get(i));
						b2.setIcon(new ImageIcon(img2.getScaledInstance(180, 180, Image.SCALE_SMOOTH)));

						//b2.setEnabled(false);
						//System.out.println(p2.getLeader());
					}
					
					else{
						JOptionPane.showMessageDialog(null, "You've Already Selected Your Leader", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			});
			champs.add(b2);
		}
		
	//	g.prepareChampionTurns();
		
		//GameController.fillTurnOrder();
		
		//-------------------------------------------------------
		
		
		//----------------Next----------------------
		
		nextPane = new JPanel();
		nextPane.setPreferredSize(new Dimension(20,100));
		nextPane.setOpaque(false);
		//nextPane.setBackground(Color.gray);
		
		
		next = new JButton();
		next.setFont(new Font("Serif", Font.PLAIN, 30));
		next.setSize(150, 100);
		next.setBorderPainted(false);
		next.setContentAreaFilled(false);
		Image nextImg = new ImageIcon("nextB.png").getImage();
		next.setIcon(new ImageIcon(nextImg.getScaledInstance(180, 125, Image.SCALE_SMOOTH)));
		next.setFocusable(false);
		//next.setBackground(Color.white);
		
		
//		next.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//						
//				
//		}});
		
		
		nextPane.add(next, BorderLayout.CENTER);
		
		bg.add(panel);
		bg.add(title, BorderLayout.NORTH);
		bg.add(nextPane, BorderLayout.SOUTH);
		//bg.add(champs, BorderLayout.CENTER);
		//this.setBackground(c);
		
//		this.revalidate();
//		this.repaint();
		this.setVisible(true);
	}
	
	
	
	
	
	public Color getC() {
		return c;
	}


	public int getIndex() {
		return index;
	}


	public JPanel getLeftM() {
		return leftM;
	}



	public JPanel getRightM() {
		return rightM;
	}





	public JPanel getNextPane() {
		return nextPane;
	}





	public Player getP1() {
		return p1;
	}





	public Player getP2() {
		return p2;
	}





	public JPanel getChamps() {
		return champs;
	}





	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1600,900);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Game g = new Game(new Player("Zeina"), new Player("Rowaida"));
		try {
			g.loadChampions("Champions.csv");
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
		
		f.add(new TeamPage(g));
		f.setVisible(true);
	}



	public Game getG() {
		return g;
	}



	public JButton getNext() {
		return next;
	}

	
	

}
