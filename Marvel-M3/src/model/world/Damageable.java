package model.world;

import java.awt.Point;

import javax.swing.ImageIcon;

public interface Damageable {
	
	public Point getLocation();
	
	public int getCurrentHP();
	
	public int getMaxHP();
	
	public void setCurrentHP(int hp);
	
	public ImageIcon getImg();

}
