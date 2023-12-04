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
		case "Captain America": img = new ImageIcon("assets/ca.png"); 
			icon = new ImageIcon("assets/CA2W.png"); break;
		
		case "Deadpool": img = new ImageIcon("assets/dp.png"); 
			icon = new ImageIcon("assets/DP2W.png"); break;
		
		case "Dr Strange": img = new ImageIcon("assets/ds.png"); 
			icon = new ImageIcon("assets/DS2W.png"); break;
		
		case "Electro": img = new ImageIcon("assets/el.png"); 
			icon = new ImageIcon("assets/EL2.png"); break;
		
		case "Ghost Rider": img = new ImageIcon("assets/gr.png"); 
			icon = new ImageIcon("assets/GS2W.png"); break;
		
		case "Hela": img = new ImageIcon("assets/he.png");
			icon = new ImageIcon("assets/HL2W.png"); break;

		case "Hulk": img = new ImageIcon("assets/hu.png"); 
			icon = new ImageIcon("assets/HU2W.png"); break;

		case "Iceman": img = new ImageIcon("assets/im (1).png");
			icon = new ImageIcon("assets/ICE2W.png"); break;

		case "Ironman": img = new ImageIcon("assets/ir.png");
			icon = new ImageIcon("assets/IR2W.png"); break;

		case "Loki": img = new ImageIcon("assets/lo.png"); 
			icon = new ImageIcon("assets/LK2W.png"); break;

		case "Quicksilver": img = new ImageIcon("assets/qs.png");
			icon = new ImageIcon("assets/QS2W.png"); break;

		case "Spiderman": img = new ImageIcon("assets/sp.png");
			icon = new ImageIcon("assets/SP2W.png"); break;

		case "Thor": img = new ImageIcon("assets/th.png"); 
			icon = new ImageIcon("assets/TR2W.png"); break;

		case "Venom": img = new ImageIcon("assets/ve.png");
			icon = new ImageIcon("assets/VN2W.png"); break;

		case "Yellow Jacket": img = new ImageIcon("assets/yj.png");
			icon = new ImageIcon("assets/YJ2W.png"); break;


	
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
