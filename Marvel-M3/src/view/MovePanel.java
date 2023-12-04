package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;

import control.GameController;
import model.world.Champion;
import model.world.Direction;
import engine.Game;
import engine.Player;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;

public class MovePanel extends JPanel implements ActionListener{
	
	private JPanel up;
	private JPanel right;
	private JPanel left;
	private JPanel down;

	private JButton upBtn;
	private JButton rightBtn;
	private JButton leftBtn;
	private JButton downBtn;
	
	private final Game g;
	private Board b;
	
	public MovePanel(final Game g, Board b) {
		
		this.b = b;
		this.g = g;
		this.setLayout(new BorderLayout());
		//this.setBackground(new Color(0xA6FAD1));
		this.setBackground(new Color(0,0,0,0));
		
		
		//---------------up-----------------
		up = new JPanel();
		up.setPreferredSize(new Dimension(500,60));
		up.setLayout(new GridBagLayout());
		up.setBackground(new Color(0,0,0,0));
		
		upBtn = new JButton();
		
		ImageIcon imgUp = new ImageIcon("up.png");
		Image imageUp = imgUp.getImage();
		imgUp = new ImageIcon(imageUp.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		
		upBtn.setIcon(imgUp);
		upBtn.setFocusable(false);
		//upBtn.setBackground(new Color(0,0,0,0));
		//upBtn.setBorderPainted(false);
		//upBtn.setContentAreaFilled(false);
		upBtn.setPreferredSize(new Dimension(200,100));
		
//		upBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				try {
//					g.move(Direction.UP);
//				} catch (NotEnoughResourcesException e) {
//					JOptionPane.showMessageDialog(null, "You don't have enough action points to move", "Not Enough Resources", JOptionPane.ERROR_MESSAGE);
//					e.printStackTrace();
//				} catch (UnallowedMovementException e) {
//					JOptionPane.showMessageDialog(null, "You are not in a condition to move", "Unallowed Movement", JOptionPane.ERROR_MESSAGE);
//					e.printStackTrace();
//				}
//				
//				Champion c = g.getCurrentChampion();
//				
//				
//		}
//			
//		});
		
		upBtn.addActionListener(this);
		//upBtn.setBackground(new Color(0,0,0,0));
		
		up.add(upBtn);
		//---------------------------------------
		
		
		
		
		//------------------right---------------
		right = new JPanel();
		right.setPreferredSize(new Dimension(80,200));
		right.setBackground(new Color(0,0,0,0));
		right.setLayout(new GridBagLayout());
		
		rightBtn = new JButton();
		ImageIcon imgR = new ImageIcon("right.png");
		Image imageR = imgR.getImage();
		imgR = new ImageIcon(imageR.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		
		rightBtn.setIcon(imgR);
		rightBtn.setFocusable(false);
		rightBtn.setPreferredSize(new Dimension(100,200));
		rightBtn.addActionListener(this);
		//rightBtn.setBackground(new Color(0,0,0,0));
		
		right.add(rightBtn);
		//-----------------------------------------
		
		
		
		
		//----------------down--------------------
		down = new JPanel();
		down.setPreferredSize(new Dimension(500,60));
		down.setBackground(new Color(0,0,0,0));
		down.setLayout(new GridBagLayout());
		
		downBtn = new JButton();
		
		downBtn = new JButton();
		ImageIcon imgD = new ImageIcon("down.png");
		Image imageD = imgD.getImage();
		imgD = new ImageIcon(imageD.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		
		downBtn.setIcon(imgD);
		downBtn.setFocusable(false);
		downBtn.setPreferredSize(new Dimension(200,100));
		downBtn.addActionListener(this);
		//downBtn.setBackground(new Color(0,0,0,0));
		
		down.add(downBtn);
		//---------------------------------------------
		
		
		
		
		//------------------left---------------
		left = new JPanel();
		left.setPreferredSize(new Dimension(80,200));
		left.setBackground(new Color(0,0,0,0));
		left.setLayout(new GridBagLayout());
		
		leftBtn = new JButton();
		
		leftBtn = new JButton();
		ImageIcon imgL = new ImageIcon("left.png");
		Image imageL = imgL.getImage();
		imgL = new ImageIcon(imageL.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		
		leftBtn.setIcon(imgL);
		leftBtn.setFocusable(false);
		leftBtn.setPreferredSize(new Dimension(100,200));
		//leftBtn.getPreferredSize().setSize(100, 200);
		leftBtn.addActionListener(this);
		//leftBtn.setBackground(new Color(0,0,0,0));
		
		left.add(leftBtn);
		//-----------------------------------------
		
		
		//------------------move-------------------
		JLabel move = new JLabel("Move");
		move.setFont(new Font("Verdana", Font.BOLD, 20));
		move.setForeground(Color.white);
		move.setBackground(new Color(0,0,0,0));
		move.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		this.add(up, BorderLayout.NORTH);
		this.add(right, BorderLayout.EAST);
		this.add(down, BorderLayout.SOUTH);
		this.add(left, BorderLayout.WEST);
		this.add(move);
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);
	}

	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(400,300);
		
		Game g = new Game(new Player("Rowaida"), new Player("Zeina"));
		try {
			g.loadChampions("Champions.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.getFirstPlayer().getTeam().add(g.getAvailableChampions().get(0));
		g.getFirstPlayer().getTeam().add(g.getAvailableChampions().get(1));
		g.getFirstPlayer().getTeam().add(g.getAvailableChampions().get(2));
		
		g.getFirstPlayer().setLeader(g.getFirstPlayer().getTeam().get(0));
		
		g.getSecondPlayer().getTeam().add(g.getAvailableChampions().get(3));
		g.getSecondPlayer().getTeam().add(g.getAvailableChampions().get(4));
		g.getSecondPlayer().getTeam().add(g.getAvailableChampions().get(5));
		
		g.getSecondPlayer().setLeader(g.getSecondPlayer().getTeam().get(0));
		
		Board b = new Board(g);
		
		f.add(new MovePanel(g, b));
		f.setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == upBtn)
			GameController.move(Direction.UP);
		
		if(e.getSource() == downBtn)
			GameController.move(Direction.DOWN);
		
		if(e.getSource() == leftBtn)
			GameController.move(Direction.LEFT);
		
		if(e.getSource() == rightBtn)
			GameController.move(Direction.RIGHT);
		
		
		
	}


	public JButton getUpBtn() {
		return upBtn;
	}


	public JButton getRightBtn() {
		return rightBtn;
	}


	public JButton getLeftBtn() {
		return leftBtn;
	}


	public JButton getDownBtn() {
		return downBtn;
	}


	public Game getG() {
		return g;
	}


	public Board getB() {
		return b;
	}
	
	

}
