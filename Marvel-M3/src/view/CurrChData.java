package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.world.Champion;
import model.world.Direction;
import control.GameController;

public class CurrChData extends JPanel {
	
	private JLabel title;
	private JTextArea area;
	
	
	public CurrChData(Champion c){
		
		this.setLayout(new BorderLayout());
		this.setOpaque(false);		
		
		//--------------------title------------------
		
		title = new JLabel("Current Champion:  " + c.getName() + " (" + c.getType() + ")");
		title.setFont(new Font("Arial", Font.BOLD, 30));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setPreferredSize(new Dimension(20,50));
		title.setForeground(Color.white);
		
		this.add(title, BorderLayout.NORTH);
		
		
		//------------------direction-----------------
		
		JPanel data = new JPanel();
		data.setOpaque(false);
		data.setLayout(new GridBagLayout());
		
		
		
		area = new JTextArea();
		area.setOpaque(false);
		String s = 	"HP: " + c.getCurrentHP() + " \t\t" + 
				"Mana: " + c.getMana() + " \n" +
				"Attack Damage: "  + c.getAttackDamage() + " \t" + 
				"Attack Range: " + c.getAttackRange() + " \n" + 
				"Action Points: " + c.getCurrentActionPoints() + " \t" + 
				"Speed: " + c.getSpeed() + " \n\n" +
				"Applied Effects: ";
		
		for(int i = 0; i<c.getAppliedEffects().size(); i++){
			s += c.getAppliedEffects().get(i).toString() + "\t";
		}
		
		area.setText(s);
		area.setEditable(false);
		area.setFont(new Font("Arial", Font.BOLD, 20));
		area.setBorder(BorderFactory.createEmptyBorder(10, 0,0,0));
		area.setForeground(Color.white);
		area.setPreferredSize(new Dimension(700,150));
	
		

		data.add(area);
		
		JLabel img = new JLabel();
		img.setOpaque(false);
		ImageIcon icon = new ImageIcon(c.getImg().getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH)); 
		img.setIcon(icon);
		img.setHorizontalAlignment(JLabel.CENTER);
		
		img.setPreferredSize(new Dimension(220, 200));
		
		img.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		
		this.add(data);
		this.add(img, BorderLayout.EAST);
		
		this.repaint();
		this.revalidate();
		this.setOpaque(false);
		this.setVisible(true);
		
	}

}
