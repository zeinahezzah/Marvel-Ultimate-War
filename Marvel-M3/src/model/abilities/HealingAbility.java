package model.abilities;

import java.util.ArrayList;

import exceptions.InvalidTargetException;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;

public  class HealingAbility extends Ability {
	private int healAmount;

	public HealingAbility(String name,int cost, int baseCoolDown, int castRadius, AreaOfEffect area,int required, int healingAmount) {
		super(name,cost, baseCoolDown, castRadius, area,required);
		this.healAmount = healingAmount;
	}

	public int getHealAmount() {
		return healAmount;
	}

	public void setHealAmount(int healAmount) {
		this.healAmount = healAmount;
	}

	@Override
	public void execute(ArrayList<Damageable> targets) throws InvalidTargetException {
		for(int i = 0; i < targets.size(); i++){
			Damageable d = targets.get(i);
			if(d instanceof Cover)	//healing cannot be applied to covers
				throw new InvalidTargetException("Healing Ability cannot be cast on cover.");
			else
				d.setCurrentHP(d.getCurrentHP() + healAmount);
		}
	}

	public String toString() {
		return //"Healing Ability \n" +
				super.toString() +
				"Heal Amount: " + healAmount;
	}

	@Override
	public String getType() {
		return "Healing Ability";
	}

	

}
