package model.world;

import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;






import javax.swing.ImageIcon;

import engine.Game;
import model.abilities.Ability;
import model.effects.Effect;

public abstract class Champion implements Damageable, Comparable{
	private String name;
	private int maxHP;
	private int currentHP;
	private int mana;
	private int maxActionPointsPerTurn;
	private int currentActionPoints;
	private int attackRange;
	private int attackDamage;
	private int speed;
	private ArrayList<Ability> abilities;
	private ArrayList<Effect> appliedEffects;
	private Condition condition;
	private Point location;
	
	private ImageIcon img;
	private ImageIcon icon;
	
	

	public Champion(String name, int maxHP, int mana, int actions, int speed, int attackRange, int attackDamage) {
		this.name = name;
		this.maxHP = maxHP;
		this.mana = mana;
		this.currentHP = this.maxHP;
		this.maxActionPointsPerTurn = actions;
		this.speed = speed;
		this.attackRange = attackRange;
		this.attackDamage = attackDamage;
		this.condition = Condition.ACTIVE;
		this.abilities = new ArrayList<Ability>();
		this.appliedEffects = new ArrayList<Effect>();
		this.currentActionPoints = maxActionPointsPerTurn;
		
		
		switch(name){
		case "Captain America": img = new ImageIcon("ca.png"); 
			icon = new ImageIcon("CA2W.png"); break;
		
		case "Deadpool": img = new ImageIcon("dp.png"); 
			icon = new ImageIcon("DP2W.png"); break;
		
		case "Dr Strange": img = new ImageIcon("ds.png"); 
			icon = new ImageIcon("DS2W.png"); break;
		
		case "Electro": img = new ImageIcon("el.png"); 
			icon = new ImageIcon("EL2.png"); break;
		
		case "Ghost Rider": img = new ImageIcon("gr.png"); 
			icon = new ImageIcon("GS2W.png"); break;
		
		case "Hela": img = new ImageIcon("he.png");
			icon = new ImageIcon("HL2W.png"); break;

		case "Hulk": img = new ImageIcon("hu.png"); 
			icon = new ImageIcon("HU2W.png"); break;

		case "Iceman": img = new ImageIcon("im (1).png");
			icon = new ImageIcon("ICE2W.png"); break;

		case "Ironman": img = new ImageIcon("ir.png");
			icon = new ImageIcon("IR2W.png"); break;

		case "Loki": img = new ImageIcon("lo.png"); 
			icon = new ImageIcon("LK2W.png"); break;

		case "Quicksilver": img = new ImageIcon("qs.png");
			icon = new ImageIcon("QS2W.png"); break;

		case "Spiderman": img = new ImageIcon("sp.png");
			icon = new ImageIcon("SP2W.png"); break;

		case "Thor": img = new ImageIcon("th.png"); 
			icon = new ImageIcon("TR2W.png"); break;

		case "Venom": img = new ImageIcon("ve.png");
			icon = new ImageIcon("VN2W.png"); break;

		case "Yellow Jacket": img = new ImageIcon("yj.png");
			icon = new ImageIcon("YJ2W.png"); break;


	
		default: img = null;
		}
		
		Image image = img.getImage();
		img = new ImageIcon(image.getScaledInstance(400, 400, Image.SCALE_SMOOTH));
		
	}

	public int getMaxHP() {
		return maxHP;
	}

	public String getName() {
		return name;
	}

	public void setCurrentHP(int hp) {

		if (hp < 0) {
			currentHP = 0;
			
		} 
		else if (hp > maxHP)
			currentHP = maxHP;
		else
			currentHP = hp;

	}

	
	public int getCurrentHP() {

		return currentHP;
	}

	public ArrayList<Effect> getAppliedEffects() {
		return appliedEffects;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int currentSpeed) {
		if (currentSpeed < 0)
			this.speed = 0;
		else
			this.speed = currentSpeed;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point currentLocation) {
		this.location = currentLocation;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public int getCurrentActionPoints() {
		return currentActionPoints;
	}

	public void setCurrentActionPoints(int currentActionPoints) {
		if(currentActionPoints>maxActionPointsPerTurn)
			currentActionPoints=maxActionPointsPerTurn;
		else 
			if(currentActionPoints<0)
			currentActionPoints=0;
		this.currentActionPoints = currentActionPoints;
	}

	public int getMaxActionPointsPerTurn() {
		return maxActionPointsPerTurn;
	}

	public void setMaxActionPointsPerTurn(int maxActionPointsPerTurn) {
		this.maxActionPointsPerTurn = maxActionPointsPerTurn;
	}
	
	public ImageIcon getImg(){
		return img;
	}
	
	public ImageIcon getIcon(){
		return icon;
	}
	
	@Override
	public int compareTo(Object o) {
		Champion c = (Champion)o;
		if(speed == c.speed){
			return name.compareTo(c.name);
		}
		return c.speed-speed;
	}
	
	
	public abstract void useLeaderAbility(ArrayList<Champion> targets);
	
	public Boolean isLeader(){
			return Game.getPlayer(this).getLeader() == this;
	}
	
	
	public String toString(){
		String s = 	"HP: " + currentHP + " \n" + 
				"Mana: " + mana + " \n" +
				"Attack Damage: "  + attackDamage + " \n" + 
				"Attack Range: " + attackRange + " \n" + 
				"Action Points: " + currentActionPoints + " \n" + 
				"Speed: " + speed + " \n" ;
				
		return s;
	}
	
	public String getEffects(){
		String s = "Applied Effects: \n";
		for(int i = 0; i<appliedEffects.size(); i++){
			s += appliedEffects.get(i).toString() + "\n";
		}
		return s;
	}
	
	public abstract String getType();

	
}
