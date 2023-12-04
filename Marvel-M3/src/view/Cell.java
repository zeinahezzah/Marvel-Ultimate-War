package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import engine.Game;
import model.world.Champion;
import model.world.Damageable;

public class Cell extends JButton implements ActionListener{
	
	private static Color color = new Color(0xf0f0f0);

	public Cell() {
		super();
		this.setFocusable(false);
		this.setBackground(color);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.addActionListener(this);
	}

	public Cell(Icon arg0) {
		super(arg0);
		this.setFocusable(false);
		
	}

	public Cell(String arg0) {
		super(arg0);
		this.setFocusable(false);
	}

	public Cell(Action arg0) {
		super(arg0);
		this.setFocusable(false);
		
	}

	public Cell(String arg0, Icon arg1) {
		super(arg0, arg1);
		this.setFocusable(false);
		}

	
	public void addChampion(Damageable d){
		
		Game g = Board.getG();
		
		Image image = d.getImg().getImage();
		ImageIcon img = new ImageIcon(image.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
		this.setIcon(img);
		
		if(d instanceof Champion){
			Champion x = (Champion) d;
			if(g.getPlayer(x) == g.getFirstPlayer()){
				this.setBackground(new Color(0xd3c6e7));
			}
			
			if(g.getPlayer(x) == g.getSecondPlayer()){
				this.setBackground(new Color(0xe7c6c6));
			}
		}
		
		else{
			this.setBackground(color);
		}
		
		double hp = d.getCurrentHP();
		
		this.setText((int)((hp/d.getMaxHP())*100) + "%");
		this.setHorizontalTextPosition(JButton.CENTER);
		this.setVerticalTextPosition(JButton.BOTTOM);
		this.setFont(new Font("Arial", Font.BOLD, 20));
	}
	
	
	public void clear(){
	
		setIcon(null);
		setBackground(color);
		setText(null);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
