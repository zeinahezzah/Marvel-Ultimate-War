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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import control.GameController;
import model.abilities.Ability;
import model.world.Direction;

public class DirectionFrame extends JFrame {
	
	private JComboBox options;

	public DirectionFrame(final Ability a){
		
		this.setLayout(new BorderLayout());
		
		this.setSize(500,250);
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
		
		
		JLabel select = new JLabel("Select a Direction: ");
		select.setFont(new Font("Arial", Font.BOLD, 20));
		select.setHorizontalAlignment(JLabel.CENTER);
		
		p.add(name);
		p.add(select);
		
		
		JPanel dir = new JPanel();
		dir.setOpaque(false);
		dir.setLayout(new GridBagLayout());
		
		options = new JComboBox(Direction.values());
		options.setEditable(false);
		options.setPreferredSize(new Dimension(300,40));
		options.setFont(new Font("Arial", Font.PLAIN, 20));
		
		options.setOpaque(false);
		
		dir.add(options);
		
		JPanel okBtn = new JPanel();
		okBtn.setOpaque(false);
		okBtn.setLayout(new GridBagLayout());
		okBtn.setPreferredSize(new Dimension(20,80));
		
		JButton ok = new JButton("Cast Ability");
		ok.setSize(100,80);
		ok.setFocusable(false);
		ok.setHorizontalAlignment(JButton.CENTER);
		ok.setBackground(Color.WHITE);
		ok.setFont(new Font("Verdana", Font.BOLD, 20));
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Direction direction = (Direction) options.getSelectedItem();
				GameController.castAbilityDir(a, direction);
			}
			
		});
		
		okBtn.add(ok);
		
		this.add(p, BorderLayout.NORTH);
		this.add(dir);
		this.setTitle(a.getName());
		this.add(okBtn, BorderLayout.SOUTH);
		this.setLocationRelativeTo(null);
		
	
		this.setUndecorated(true);
		this.setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 10, 10));
		this.setVisible(true);
	
		this.repaint();
		this.revalidate();
	}
	
	public static void main(String[] args) {
	
	}
}
