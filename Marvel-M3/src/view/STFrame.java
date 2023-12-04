package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.abilities.Ability;
import model.world.Direction;
import control.GameController;

public class STFrame extends JFrame {
	
	private static int x;
	private static int y;
	private static JTextField xBox;
	private static JTextField yBox;

	
	public STFrame(final Ability a){
		
		this.setLayout(new BorderLayout());
		
		this.setSize(500,300);
		//this.setBackground(Color.gray);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		((JPanel)getContentPane()).setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.black));

//
		JPanel p = new JPanel();
		p.setOpaque(false);
		//p.setBackground(MainView.getC());
		p.setLayout(new GridLayout(2,0));
		p.setPreferredSize(new Dimension(500,80));
		
		
		JLabel name = new JLabel(a.getName());
		name.setFont(new Font("Arial", Font.BOLD, 30));
		name.setOpaque(false);
		//name.setForeground(Color.white);
		name.setHorizontalAlignment(JLabel.CENTER);
		
		
		JLabel select = new JLabel("Enter the coordinates of the desired target: ");
		select.setFont(new Font("Arial", Font.BOLD, 20));
		//select.setForeground(Color.white);
		//select.setOpaque(true);
		select.setHorizontalAlignment(JLabel.CENTER);
		
		p.add(name);
		p.add(select);
		
		
		JPanel entries = new JPanel();
		entries.setOpaque(false);
		//dir.setBackground(new Color(0,0,0,0));
		entries.setLayout(new GridBagLayout());
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new FlowLayout());
		//dir.setBackground(new Color(0,0,0,0));
		//panel.setLayout(new GridBagLayout());
		
		xBox = new JTextField("x");
		yBox = new JTextField("y");
		
		
		xBox.setPreferredSize(new Dimension(100,30));
		yBox.setPreferredSize(new Dimension(100,30));
		
		xBox.setFont(new Font("Arial", Font.PLAIN, 20));
		yBox.setFont(new Font("Arial", Font.PLAIN, 20));
		
		panel.add(xBox);
		panel.add(yBox);
		
		entries.add(panel);
	
		//p.add(name);
		
		JPanel okBtn = new JPanel();
		okBtn.setOpaque(false);
		//okBtn.setBackground(Color.LIGHT_GRAY);
		okBtn.setLayout(new GridBagLayout());
		okBtn.setPreferredSize(new Dimension(20,80));
		
		JButton ok = new JButton("Cast Ability");
		ok.setSize(100,80);
		ok.setHorizontalAlignment(JButton.CENTER);
		ok.setBackground(Color.WHITE);
		ok.setFocusable(false);
		ok.setFont(new Font("Verdana", Font.BOLD, 20));
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				x = Integer.parseInt(xBox.getText());
				y = Integer.parseInt(yBox.getText());
				
				GameController.castAbilityST(a, x, y);
			}
			
		});
		
		okBtn.add(ok);
		
		this.add(p, BorderLayout.NORTH);
		this.add(entries);
		//f.add(type);
		this.setTitle(a.getName());
		this.add(okBtn, BorderLayout.SOUTH);
		this.setLocationRelativeTo(null);
		
	
		this.setUndecorated(true);
		this.setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
		this.setVisible(true);
	
		this.repaint();
		this.revalidate();
	}
}
