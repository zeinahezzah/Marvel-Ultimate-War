package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.GameController;

public class LoadingPage {
	
	
	private static int value = 0;
	private static ProgressBar load = new ProgressBar();
	//final static JFrame f = new JFrame();
	
	public LoadingPage(){
		
		JFrame f = new JFrame();
		f.setSize(800,200);
		f.setLayout(new GridBagLayout());
		//f.setBackground(Color.lightGray);
		
		f.getContentPane().setBackground(Color.LIGHT_GRAY);
		((JPanel)f.getContentPane()).setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

//
//		JPanel p = new JPanel();
//		p.setBackground(MainView.getC());
//		p.setLayout(new GridBagLayout());
//		p.setPreferredSize(new Dimension(300,90));
//		p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
////		
//		Toolkit t = Toolkit.getDefaultToolkit();
//		int x = (int) t.getScreenSize().getWidth();
//		int y = (int) t.getScreenSize().getHeight();
//		
//		f.setLayout(new BorderLayout());
//		
//		ImagePanel bg = new ImagePanel("names.jpg",x,y);
//		bg.setLayout(new GridBagLayout());
		
		
		
//		JPanel panel = new JPanel();
//		panel.setOpaque(false);
//		panel.setPreferredSize(new Dimension(2000,500));
		
		load = new ProgressBar();
		//panel.add(load);
		
		load.setPreferredSize(new Dimension(750,100));
		
		f.add(load);
		
		f.setTitle("Loading...");
		//f.add(bg);
	
		f.setLocationRelativeTo(null);
		f.setUndecorated(true);
		f.setShape(new RoundRectangle2D.Double(0, 0, f.getWidth(), f.getHeight(), 20, 20));
		f.setVisible(true);
		//f.setVisible(true);
		
		while(value <= 100){
			load.setValue(value);
			load.setString("Loading... " + value + "%");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			value += 1;
			f.repaint();
			f.revalidate();
		}
		f.setVisible(true);
		f.dispose();
		
		//fill();
	}

	
	public static void main(String[] args) {
//		JFrame f = new JFrame();
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setSize(1600,900);
//		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		f.setVisible(true);
//		
//		LoadingPage loading = new LoadingPage();
//		f.add(loading);
//		loading.fill();
//		f.setVisible(true);
		new LoadingPage();
		
	}
	
}
