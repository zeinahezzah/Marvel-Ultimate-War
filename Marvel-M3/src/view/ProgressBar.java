package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.border.EtchedBorder;

public class ProgressBar extends JProgressBar {
	
	private int value;
	
	public ProgressBar(){
		this.setValue(0);
		this.setString("Loading... " + value + " %");
		this.setStringPainted(true);
		this.setFont(new Font("Verdana", Font.BOLD, 20));
		this.setForeground(MainView.getC());
		this.setBackground(Color.black);
		this.setPreferredSize(new Dimension(800, 80));
		//this.setBorderPainted(false);
		
		this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.white));
		
	}
	
	
	
	

}
