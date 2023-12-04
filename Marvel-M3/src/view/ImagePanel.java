package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	Image image;

	public ImagePanel(String s, int x, int y) {
	
		setLayout(new BorderLayout());
		this.setOpaque(false);
		
		image = Toolkit.getDefaultToolkit().createImage(s);
		image = image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		
		this.revalidate();
		this.repaint();
		this.setVisible(true);
			
	}
		
		
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(image != null)
			g.drawImage(image, 0, 0, this);
	}
	
	

}
