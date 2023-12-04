package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.abilities.Ability;

public class AbilityDetails {
	
	public AbilityDetails(Ability a){
		
		final JFrame f = new JFrame();
		f.setSize(500,400);
		f.setLayout(new BorderLayout());
		
		f.getContentPane().setBackground(Color.LIGHT_GRAY);
		((JPanel)f.getContentPane()).setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

		JPanel p = new JPanel();
		p.setBackground(MainView.getC());
		p.setLayout(new GridLayout(2,0));
		p.setPreferredSize(new Dimension(300,90));
		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
		JLabel name = new JLabel(a.getName());
		name.setFont(new Font("Arial", Font.BOLD, 30));
		name.setBackground(MainView.getC());
		name.setForeground(Color.white);
		name.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel type = new JLabel(a.getType());
		type.setFont(new Font("Arial", Font.BOLD, 25));
		type.setForeground(Color.white);
		type.setHorizontalAlignment(JLabel.CENTER);
		
		p.add(name);
		p.add(type);				
		
		JTextArea attributes = new JTextArea(a.toString());
		attributes.setFont(new Font("Arial", Font.PLAIN, 25));
		attributes.setBorder(BorderFactory.createEmptyBorder(15, 5, 5, 5));
		attributes.setBackground(Color.lightGray);
		attributes.setEditable(false);
		
		JPanel okBtn = new JPanel();
		okBtn.setBackground(Color.LIGHT_GRAY);
		okBtn.setLayout(new GridBagLayout());
		okBtn.setPreferredSize(new Dimension(20,80));
		
		JButton ok = new JButton("BACK");
		ok.setSize(100,80);
		ok.setFocusable(false);
		ok.setHorizontalAlignment(JButton.CENTER);
		ok.setFont(new Font("Verdana", Font.BOLD, 20));
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				f.dispose();
			}
			
		});
		
		okBtn.add(ok);
		
		f.add(p, BorderLayout.NORTH);
		f.setTitle(a.getName());
		f.add(attributes);
		f.add(okBtn, BorderLayout.SOUTH);
		f.setLocationRelativeTo(null);
		f.setUndecorated(true);
		f.setShape(new RoundRectangle2D.Double(0, 0, f.getWidth(), f.getHeight(), 20, 20));
		f.setVisible(true);
	}

}
