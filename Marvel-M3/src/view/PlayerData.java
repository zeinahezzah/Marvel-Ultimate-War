package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import control.GameController;
import model.world.Champion;
import model.world.Condition;
import engine.Game;
import engine.Player;
import exceptions.*;


public class PlayerData extends JPanel {

	private JLabel name;
	private JPanel champions;
	private JPanel leader;
	private ImagePanel panel;
	private String img;
	
	private JButton abilitybutton;
//	private LeaderAbilityButton abilitybutton2;
	
	private static ArrayList<JTextArea> data = new ArrayList<JTextArea>();
	private static ArrayList<Champion> champs = new ArrayList<Champion>();
	private static JTextArea area;
	private static int index = 0;
	private static Game g;
	private static Color color;
	//private static Color c2;
	
	public PlayerData(final Player p, final Game g) {
		
		this.g = g;
		
		this.setPreferredSize(new Dimension(300,1000));
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setOpaque(false);
		
		
		//this.setBackground(new Color(0xf57ef9));
		
//		if(p.equals(g.getFirstPlayer())){
//			color = new Color(0xfdf1be);
//		}
//		else
//			color = new Color(0xfdbebe);
		if(p.equals(g.getFirstPlayer())){
			img = "panelx.png";
		}
		else
			img = "panel2x.png";
		
		color = Color.white;
		
		
		ImagePanel panel = new ImagePanel(img, 480,600);
		//panel.setPreferredSize(new Dimension(500,600));
		panel.setOpaque(false);
		//panel.setBackground(new Color(0xf57ef9));
		panel.setLayout(new BorderLayout());
		
		
		this.add(panel);
		//p2Panel.setLayout(new GridBagLayout());
				
		//----------------name------------------
		
		name = new JLabel(p.getName().toUpperCase());
		name.setFont(new Font("Serif", Font.BOLD, 40));
		name.setHorizontalAlignment(JLabel.CENTER);
		name.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
		//name.setForeground(Color.white);
		name.setOpaque(false);
		
		//--------------------------------------
		
		//-----------------champions----------------
		
		champions = new JPanel();
		champions.setLayout(new GridLayout(2,2, 10,10));
		champions.setOpaque(false);
		champions.setBackground(this.getBackground());
		champions.setBorder(BorderFactory.createEmptyBorder(0, 30, 80, 20));
		champions.add(getChData(p.getLeader()));
		
		
		for(int i = 0; i<p.getTeam().size(); i++){
			Champion c = p.getTeam().get(i);
			
			if(c != p.getLeader()){
				champions.add(getChData(c));
			}
		}
		
		JPanel abilityBtn = new JPanel();
		abilityBtn.setOpaque(false);
		abilityBtn.setBackground(this.getBackground());
		abilityBtn.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		abilitybutton = new JButton();
		abilitybutton.setOpaque(false);
		abilitybutton.setText("Use Leader Ability");
		abilitybutton.setFocusable(false);
		abilitybutton.setFont(new Font("Serif", Font.BOLD, 20));
		abilitybutton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameController.useLeaderAbility();
				if(p == g.getFirstPlayer() && g.isFirstLeaderAbilityUsed())
					abilitybutton.setEnabled(false);
				if(p == g.getSecondPlayer() && g.isSecondLeaderAbilityUsed())
					abilitybutton.setEnabled(false);
			}
			
		});
		abilitybutton.setHorizontalAlignment(JButton.CENTER);
		abilitybutton.setVerticalAlignment(JButton.CENTER);
		
		abilityBtn.add(abilitybutton);
		//abilityBtn.add(abilityBtn, gbc);
		
		champions.add(abilityBtn, gbc);
		
		
		
		
		panel.add(name, BorderLayout.NORTH);
		panel.add(champions, BorderLayout.CENTER);
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);
		
	}

	public PlayerData(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PlayerData(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public PlayerData(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
		
	
	
	public JPanel getChData(Champion c){
		champs.add(c);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);
		panel.setBorder(BorderFactory.createBevelBorder(2));
		//panel.setBackground(color);
		
		JLabel n;
		if(c.isLeader()){
			n = new JLabel(c.getName() + " (" + c.getType() + ")");
			n.setForeground(Color.blue);
		}
		else{
			n = new JLabel(c.getName() + " (" + c.getType() + ")");
			//n.setForeground(Color.WHITE);
		}
		
		//n.setBounds(0,i*250,300,50);
		n.setPreferredSize(new Dimension(300,50));
		n.setOpaque(false);
		n.setBackground(this.getBackground());
		
		n.setFont(new Font("Serif", Font.BOLD, 20));
		
		area = new JTextArea();
		area.setOpaque(false);
		//area.setBackground(color);
		//area.setBounds(0,i*250+50,300,200);
		area.setText(c.toString() +"\n"+ c.getEffects());
		area.setEditable(false);
		area.setFont(new Font("Serif", Font.PLAIN, 20));
		//area.setForeground(Color.white);
		data.add(area);
		
		
		JScrollPane scroll = new JScrollPane(area);
		area.setCaretPosition(0);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		//scroll.setBackground(color);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		//scroll.getVerticalScrollBar().setBackground(Color.BLACK);;
		
		panel.add(n, BorderLayout.NORTH);
		//panel.add(area);
		panel.add(scroll);
		
		return panel;
	}
	
	public static void updateData(){
		
		for(int i = 0; i<champs.size(); i++){
			
			Champion c = champs.get(i);
			
			data.get(i).setText(c.toString() +"\n"+ c.getEffects());
			data.get(i).setCaretPosition(0);
			
			if(c.getCondition() == Condition.KNOCKEDOUT){
				data.get(i).setForeground(Color.gray);
			}
		}
	}

	public JLabel getNames() {
		return name;
	}

	public JPanel getChampions() {
		return champions;
	}

	public JPanel getLeader() {
		return leader;
	}

	public ImagePanel getPanel() {
		return panel;
	}

	public String getImg() {
		return img;
	}

	public JButton getAbilitybutton() {
		return abilitybutton;
	}

	public static ArrayList<JTextArea> getData() {
		return data;
	}

	public static ArrayList<Champion> getChamps() {
		return champs;
	}

	public static JTextArea getArea() {
		return area;
	}

	public static int getIndex() {
		return index;
	}

	public static Game getG() {
		return g;
	}

	public static Color getColor() {
		return color;
	}

	
	
}
