package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import model.*;
import model.abilities.Ability;
import model.world.Champion;
import engine.*;

public class ChampionsPage extends JPanel implements ActionListener{
	
	private JPanel champion;
	private JPanel chData;
	private JPanel buttonPane;
	private JPanel characters;
	private JPanel titlePane;
	private JButton addToTeam;
	private JButton next;
	private JPanel abilities; 
	private static Champion displayedChamp;
	
	
	private JTextArea attributes;
	private int index;
	private static int pIndex;
	
	//private Player current;
	
	private JLabel image = new JLabel();
	private JLabel name = new JLabel();
	
	private static Game g;
	private static ArrayList<JButton> buttons;
	private static ArrayList<Champion> champs;
	//private int counter = 0;

	public ChampionsPage(final Game g){
		
		Toolkit t = Toolkit.getDefaultToolkit();
		int x = (int) t.getScreenSize().getWidth();
		int y = (int) t.getScreenSize().getHeight();
		
		this.g = g;
		
	this.setLayout(new BorderLayout());
	ImagePanel bg = new ImagePanel("names.jpg",x,y);
	bg.setLayout(new BorderLayout());
	this.add(bg);
	
	index = 0;
	
	champion = new JPanel();
	characters = new JPanel();
	titlePane = new JPanel();
	abilities = new JPanel();
	
	champion.setPreferredSize(new Dimension(500, 100));
	titlePane.setPreferredSize(new Dimension(100, 100));
	
	champion.setLayout(new BorderLayout());
	characters.setLayout(new GridLayout(3,5,20,20));
	
	champion.setOpaque(false);
	abilities.setOpaque(false);
	titlePane.setOpaque(false);
	characters.setOpaque(false);
	
	//champion.setBackground(Color.gray);
	//titlePane.setBackground(new Color(0xB80523));
	
	bg.add(champion, BorderLayout.EAST);
	bg.add(titlePane, BorderLayout.NORTH);
	bg.add(characters, BorderLayout.CENTER);
	
	this.setVisible(true);
	
	addToTeam = new JButton();
	addToTeam.setFocusable(false);
	
	//------------title----------------
	
	JLabel chooseC = new JLabel("Choose Your Champions!");
	chooseC.setFont(new Font("Serif", Font.BOLD, 50));
	chooseC.setForeground(Color.white);
	next = new JButton();
	next.setFocusable(false);
	
	
	
	titlePane.add(chooseC);
	
	//------------------title--------------
	
	//----------------characters------------
	champs = g.getAvailableChampions();
	buttons = new ArrayList<JButton>();
	
	for(int i = 0; i < champs.size(); i++){
		final JButton b = new JButton();	//champs.get(i).getName()
		//final ImageButton b = new ImageButton(champs.get(i).getIcon().getImage(), champs.get(i).getImg().getImage());
		b.setFont(new Font("Serif", Font.BOLD, 20));
		b.setToolTipText(champs.get(i).toString());
		b.setBorderPainted(false);
		
		Image img2 = champs.get(i).getImg().getImage();
		Image icon2 = champs.get(i).getIcon().getImage();
		
		final ImageIcon img = new ImageIcon(img2.getScaledInstance(280, 290, Image.SCALE_SMOOTH));
		final ImageIcon icon = new ImageIcon(icon2.getScaledInstance(280, 290, Image.SCALE_SMOOTH));
		
		b.setOpaque(false);
		b.setBackground(new Color(0,0,0,0));
		b.setIcon(icon);
		b.setRolloverIcon(img);
		b.setPressedIcon(img);
//		
//		b.setIcon(img);
//		b.setRolloverIcon(icon);
//		b.setPressedIcon(icon);
//		
		//b.setDisabledIcon(icon);
		//b.setHorizontalTextPosition(JButton.CENTER);
		//b.setVerticalTextPosition(JButton.BOTTOM);
		b.setFocusable(false);
		buttons.add(b);
		characters.add(b);
		
		pIndex = 0;
		
		//System.out.println(characters.getSize());
		//b.setToolTipText("<html><font face=\"sansserif\" color=\"green\">first line<br>second line</font></html>");
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(pIndex == 0){
					pIndex = buttons.indexOf(b);
				}
				
				else{
					JButton b2 = buttons.get(pIndex);
					
					Image pImg = champs.get(pIndex).getIcon().getImage();
					ImageIcon pIcon = new ImageIcon(pImg.getScaledInstance(280, 290, Image.SCALE_SMOOTH));

				
					if(addToTeam.isEnabled()){
						b2.setIcon(pIcon);
					}
					
				}
				
				index = buttons.indexOf(b);
				b.setIcon(img);
				
				Champion c = champs.get(index);
				
				name.setHorizontalAlignment(JLabel.CENTER);
				name.setPreferredSize(new Dimension(30,80));
				name.setText(c.getName() + " (" + c.getType() + ")");
				name.setFont(new Font("Verdana", Font.BOLD, 30));
				//name.setBackground(new Color(0x73bbf2));
				name.setForeground(Color.white);
				//name.setOpaque(true);
				champion.add(name, BorderLayout.NORTH);
				
				

				image.setPreferredSize(new Dimension(400,400));
				image.setHorizontalAlignment(JLabel.CENTER);
				image.setIcon(c.getImg());
				image.setBackground(new Color(0,0,0,0));
				image.setBorder(new EmptyBorder(20, 10, 10, 10));
				image.setOpaque(false);
				
				chData.add(image, BorderLayout.NORTH);
				
				
				JPanel data = new JPanel();
				data.setOpaque(false);
				data.setLayout(new BorderLayout());
				
				
				attributes.setText(c.toString());
				attributes.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

				data.add(attributes);
				
				abilities.removeAll();
				abilities.setOpaque(false);
				abilities.setPreferredSize(new Dimension(200,30));
				abilities.setLayout(new BoxLayout(abilities, BoxLayout.Y_AXIS));
				
				JLabel ab = new JLabel("Abilities: ");
				ab.setFont(new Font("Serif", Font.BOLD, 30));
				ab.setForeground(Color.white);
				ab.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				abilities.add(ab);
				abilities.add(Box.createVerticalStrut(20));
				
				for(int i = 0; i <c.getAbilities().size(); i++){
					
					final Ability a = c.getAbilities().get(i);
					
					JButton b = new JButton(a.getName());
					b.setPreferredSize(new Dimension(100,50));
					b.setFont(new Font("Serif", Font.BOLD, 20));
					b.setBackground(MainView.getC());
					b.setForeground(Color.white);
					b.setFocusable(false);
					
					b.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent arg0) {
							new AbilityDetails(a);
						}
						
					});
					
					abilities.add(b);
					abilities.add(Box.createVerticalStrut(20));
					
				}
				
				data.add(abilities, BorderLayout.EAST);
				
				chData.add(data);
				champion.add(chData, BorderLayout.CENTER);
				
				addToTeam.setEnabled(true);
				pIndex = index;
			}
			
		});
	}
	
	//----------characters---------------
	
	
	
	//-------------champion-------------
	
	chData = new JPanel();
	chData.setOpaque(false);
	
	chData.setLayout(new BorderLayout());
	//chData.setBackground(Color.gray);
	
	//-----------buttons--------------
	
	buttonPane = new JPanel();
	//buttonPane.setBackground(Color.gray);
	buttonPane.setOpaque(false);
	buttonPane.setPreferredSize(new Dimension(20,120));
	
	
	addToTeam.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
				
				Image img2 = champs.get(index).getImg().getImage();
				Image icon2 = champs.get(index).getIcon().getImage();
				
				final ImageIcon img = new ImageIcon(img2.getScaledInstance(280, 290, Image.SCALE_SMOOTH));
				final ImageIcon icon = new ImageIcon(icon2.getScaledInstance(280, 290, Image.SCALE_SMOOTH));
				
				String soundName = "next.wav";    
				AudioInputStream audioInputStream;
				try {
					audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
					Clip add = AudioSystem.getClip();
					add.open(audioInputStream);
					add.start();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
			
				
				if(g.getFirstPlayer().getTeam().size() < 3){
					
					g.getFirstPlayer().getTeam().add(champs.get(index));
					buttons.get(index).setIcon(img);
					buttons.get(index).setEnabled(false);
					addToTeam.setEnabled(false);
					//System.out.println(champs.get(index).getName());
					
					if(g.getFirstPlayer().getTeam().size() == 3)
						JOptionPane.showMessageDialog(null, g.getSecondPlayer().getName() + ", Select your champions", "Player 2", JOptionPane.INFORMATION_MESSAGE);
				}
				else if (g.getSecondPlayer().getTeam().size() <3){
					g.getSecondPlayer().getTeam().add(champs.get(index));
					buttons.get(index).setIcon(img);
					buttons.get(index).setEnabled(false);
					addToTeam.setEnabled(false);
					//System.out.println(champs.get(index).getName());
					
				}
				else{
					String soundName2 = "error.wav";    
					AudioInputStream audioInputStream2;
					try {
						audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
						Clip error = AudioSystem.getClip();
						error.open(audioInputStream);
						error.start();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Team Full!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
//				if(g.getSecondPlayer().getTeam().size() == 3){
//					setVisible(false);
//					TeamPage c = new TeamPage(g);
//					c.setVisible(true);
//					add(c);
//				}
			}
	});;
	
	addToTeam.setFont(new Font("Serif", Font.PLAIN, 20));
	addToTeam.setSize(200, 150);
	addToTeam.setBorderPainted(false);
	addToTeam.setContentAreaFilled(false);
	Image addImg = new ImageIcon("add.png").getImage();
	addToTeam.setIcon(new ImageIcon(addImg.getScaledInstance(200, 130, Image.SCALE_SMOOTH)));

	//addToTeam.setBackground(Color.white);
	buttonPane.add(addToTeam);

	
	next.setFont(new Font("Serif", Font.PLAIN, 20));
	next.setSize(150, 100);
	next.setBorderPainted(false);
	next.setContentAreaFilled(false);
	Image nextImg = new ImageIcon("nextB.png").getImage();
	next.setIcon(new ImageIcon(nextImg.getScaledInstance(180, 125, Image.SCALE_SMOOTH)));

	
	//next.setBackground(Color.white);
	buttonPane.add(next);
	
	
	champion.add(buttonPane, BorderLayout.SOUTH);
	//----------------attributes----------
	
	
	
	attributes = new JTextArea();
	attributes.setEditable(false);
	attributes.setOpaque(false);
	attributes.setFont(new Font("Serif", Font.BOLD, 30));
	attributes.setForeground(Color.white);
	//attributes.setBackground(Color.gray);

	
	this.revalidate();
	this.repaint();
	
	
	}
	
	public JPanel getChampion() {
		return champion;
	}

	public JPanel getCharacters() {
		return characters;
	}

	public JPanel getTitlePane() {
		return titlePane;
	}

	public JButton getAddToTeam() {
		return addToTeam;
	}

	public JButton getNext() {
		return next;
	}

	public JTextArea getAttributes() {
		return attributes;
	}

	public int getIndex() {
		return index;
	}

	public JLabel getImage() {
		return image;
	}

	public JLabel getChampName() {
		return name;
	}

	public static ArrayList<JButton> getButtons() {
		return buttons;
	}
	
//	public static Game getG() {
//		return g;
//	}

	public static ArrayList<Champion> getChamps() {
		return champs;
	}

	
	public ArrayList<Champion> getChampions(){
		return champs;
	}
	
	public Game getGame(){
		return g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int i;
		
//		if (e.getSource() instanceof AddButton){
//			if(g.getFirstPlayer().getTeam().size() <3){
//				g.getFirstPlayer().getTeam().add(champs.get(index));
//				System.out.println(champs.get(index).getName());
//			}
//			else{
//				g.getSecondPlayer().getTeam().add(champs.get(index));
//				System.out.println(champs.get(index).getName());
//			}
//		}	
		
		 if(e.getSource() instanceof JButton){
		
			 JButton btn = (JButton) e.getSource();
				for (i = 0; i < buttons.size(); i++){
					if(buttons.get(i) == btn){
						index = i;
						break;
					}	
				}
			
			
			//displayedChamp = champs.get(index);
			
			name.setHorizontalAlignment(JLabel.CENTER);
			name.setPreferredSize(new Dimension(30,80));
			name.setText(champs.get(i).getName());
			name.setFont(new Font("Serif", Font.BOLD, 30));
			//name.setBackground(new Color(0x73bbf2));
			//name.setForeground(Color.white);
			name.setOpaque(false);
			champion.add(name, BorderLayout.NORTH);
			
			

			image.setPreferredSize(new Dimension(400,400));
			image.setHorizontalAlignment(JLabel.CENTER);
			image.setIcon(champs.get(i).getImg());
			image.setBackground(new Color(0,0,0,0));
			image.setOpaque(false);
			
			chData.add(image, BorderLayout.NORTH);
			
			
			attributes.setText(champs.get(i).toString());
			attributes.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
			
			chData.add(attributes);
			chData.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
//			if(current == g.getFirstPlayer()){
//				g.getFirstPlayer().getTeam().add(champs.get(i));
//				System.out.println(champs.get(index).getName());
//				counter++;
//				if(counter == 3)
//					current = g.getSecondPlayer();
//			}
//			else if (current == g.getSecondPlayer()){
//				g.getSecondPlayer().getTeam().add(champs.get(i));
//				System.out.println(champs.get(index).getName());
//			}
			//JLabel cur = new JLabel(btn.getText());
			//attributes.add(cur , BorderLayout.CENTER);
		}
//		else if (e.getSource() instanceof AddButton){
//				if(g.getFirstPlayer().getTeam().size() <3){
//					g.getFirstPlayer().getTeam().add(champs.get(index));
//					System.out.println(champs.get(index).getName());
//				}
//				else{
//					g.getSecondPlayer().getTeam().add(champs.get(index));
//					System.out.println(champs.get(index).getName());
//				}
//		}
		 
		 champion.add(chData, BorderLayout.CENTER);
	}
	
	
//	public static Game setTeams(){
//		return g;
//	}
	
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1600,900);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		Game g = new Game(new Player("Zeina"), new Player("Rowaida"));
		try {
			g.loadAbilities("Abilities.csv");
			g.loadChampions("Champions.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f.add(new ChampionsPage(g));
		f.setVisible(true);
		JOptionPane.showMessageDialog(null, g.getFirstPlayer().getName() + ", Select your champions", "Player 1", JOptionPane.INFORMATION_MESSAGE);
	}
}






