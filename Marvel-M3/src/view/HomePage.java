package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class HomePage extends JPanel implements ActionListener{
	
	private JLabel bg;
	private JButton start;
	private JLabel logo;
	private JLayeredPane lpanel;
	
	public HomePage(){
		Toolkit t = Toolkit.getDefaultToolkit();
		int x = (int) t.getScreenSize().getWidth();
		int y = (int) t.getScreenSize().getHeight();
		this.setLayout(new BorderLayout());
		
		this.setSize(x,y);
		this.setBackground(new Color(0xB80523));
		
		ImagePanel img = new ImagePanel("assets/MARVEL.png", x, y);
		img.setLayout(new BorderLayout());
		this.add(img);
		
		
		JPanel sideP = new JPanel();
		sideP.setOpaque(false);
		sideP.setPreferredSize(new Dimension(580,100));
		sideP.setLayout(new BorderLayout());
		
		JPanel startPane = new JPanel();
		startPane.setOpaque(false);
		startPane.setPreferredSize(new Dimension(40,300));
		startPane.setLayout(new GridBagLayout());
				
		start = new JButton();
		start.setPreferredSize(new Dimension(400, 200));
		Image startImage = new ImageIcon("assets/start.png").getImage();
		start.setIcon(new ImageIcon(startImage.getScaledInstance(217, 120, Image.SCALE_SMOOTH)));
		start.setBorderPainted(false);
		start.setContentAreaFilled(false);
		start.setFocusable(false);
		startPane.add(start);
		
		sideP.add(startPane, BorderLayout.SOUTH);
		img.add(sideP, BorderLayout.WEST);

		this.repaint();
		this.revalidate();
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		JFrame frame = new MainView();
	}

	
	public void paintComponent(Graphics g){
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == start){
	        
	       
			
			start.setVisible(false);
			this.setVisible(false);
			
			JPanel page2 = new EnterNames();
			page2.setVisible(true);
			this.add(page2);
			
			
		}
		
	}

	public JLabel getBg() {
		return bg;
	}

	public void setBg(JLabel bg) {
		this.bg = bg;
	}

	public JButton getStart() {
		return start;
	}

	public void setStart(JButton start) {
		this.start = start;
	}

	public JLabel getLogo() {
		return logo;
	}

	public void setLogo(JLabel logo) {
		this.logo = logo;
	}
	
}
