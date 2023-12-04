package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import control.GameController;
import engine.*;

public class WinPage extends JPanel{

	public WinPage(Player p) {
		
		Toolkit t = Toolkit.getDefaultToolkit();
		int x = (int) t.getScreenSize().getWidth();
		int y = (int) t.getScreenSize().getHeight();
		
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(0x090e22));
		
		ImagePanel bg = new ImagePanel("gameover2.jpg", x-250, y-250);
		bg.setLayout(new BorderLayout());
		bg.setPreferredSize(new Dimension(x-250, y-250));
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,0));
		panel.setPreferredSize(new Dimension(250,230));
		panel.setOpaque(false);
		
		
		JLabel wins = new JLabel(p.getName().toUpperCase() + " WINS!");
		wins.setFont(new Font("Verdana", Font.BOLD, 80));
		wins.setForeground(Color.white);
		wins.setHorizontalAlignment(JLabel.CENTER);
		wins.setVerticalAlignment(JLabel.CENTER);
		
		panel.add(wins);
		
		
		JPanel exit = new JPanel();
		exit.setLayout(new GridBagLayout());
		exit.setOpaque(false);
		
		JButton exitBtn = new JButton("EXIT");
		exitBtn.setFont(new Font("Verdana", Font.BOLD, 30));
		exitBtn.setFocusable(false);
		exitBtn.setPreferredSize(new Dimension(200,50));
		exitBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameController.exit();
				
			}
			
		});
		
		exit.add(exitBtn);
		
		panel.add(exit);
		
//		if(g.checkGameOver() == g.getFirstPlayer()){
//			wins.setText(p.getName);
//		}
//		
		//JPanel wins = new JPanel();
		
		
		bg.add(panel, BorderLayout.SOUTH);
		
		this.add(bg);
		
		this.setVisible(true);
	
		
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
		f.add(new WinPage(g.getSecondPlayer()));
		f.setVisible(true);
		//JOptionPane.showMessageDialog(null, getFirstPlayer().getName() + ", Select your champions", "Player 1", JOptionPane.INFORMATION_MESSAGE);
	}
	
	
	
	
}
