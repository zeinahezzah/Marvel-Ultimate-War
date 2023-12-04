package model.world;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Cover implements Damageable {
	private int currentHP;
	private int maxHP;

	private Point location;
	
	private ImageIcon img;

	public Cover(int x, int y) {
		this.currentHP = (int)(( Math.random() * 900) + 100);
		maxHP = currentHP;
		
		location = new Point(x, y);
		
		img = new ImageIcon("tree.png");
		Image im = img.getImage();
		img = new ImageIcon(im.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		
	}

	public int getCurrentHP() {
		return this.currentHP;
	}
	
	public int getMaxHP(){
		return maxHP;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setCurrentHP(int newHp) {
		if (newHp < 0) {
			currentHP = 0;
		
		} else
			currentHP = newHp;
	}

	public Point getLocation() {
		return location;
	}

	

	

}
