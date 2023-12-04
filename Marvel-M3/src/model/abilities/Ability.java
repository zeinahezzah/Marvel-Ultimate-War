package model.abilities;
import java.util.ArrayList;

import model.abilities.*;
import model.effects.*;
import model.world.*;

public abstract class Ability {
	private String name;
	private int manaCost;
	private int baseCooldown;
	private int currentCooldown;
	private int castRange;
	private AreaOfEffect castArea;
	private int requiredActionPoints;

	public Ability(String name, int cost, int baseCoolDown, int castRange, AreaOfEffect area, int required) {
		this.name = name;
		this.manaCost = cost;
		this.baseCooldown = baseCoolDown;
		this.currentCooldown = 0;
		this.castRange = castRange;
		this.castArea = area;
		this.requiredActionPoints = required;
	}

	public int getCurrentCooldown() {
		return currentCooldown;
	}

	public void setCurrentCooldown(int currentCoolDown) {
		if (currentCoolDown < 0)
			currentCoolDown = 0;
		else if (currentCoolDown > baseCooldown)
			currentCoolDown = baseCooldown;
		this.currentCooldown = currentCoolDown;
	}

	public String getName() {
		return name;
	}

	public int getManaCost() {
		return manaCost;
	}

	public int getBaseCooldown() {
		return baseCooldown;
	}

	public int getCastRange() {
		return castRange;
	}

	public AreaOfEffect getCastArea() {
		return castArea;
	}

	public int getRequiredActionPoints() {
		return requiredActionPoints;
	}
	
	
	
	public abstract void execute(ArrayList<Damageable> targets) throws Exception;
		
	public abstract String getType();
	
	public String toString(){
		return //name + "\n" + 
				"Mana Cost: " + manaCost + "\n" + 
				"Base CoolDown: " + baseCooldown + "\n" + 
				"Current CoolDown: " + currentCooldown + "\n" + 
				"Area of Effect: " + castArea + "\n" + 
				"Cast Range: " + castRange + "\n" + 
				"Action Points Cost: " + requiredActionPoints + "\n";
	}
}
