package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

import java.util.ArrayList;

import engine.Game;
import model.abilities.*;
import model.effects.*;
import model.world.*;

public class DamagingAbility extends Ability {
	
	private int damageAmount;
	public DamagingAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area,int required,int damageAmount) {
		super(name, cost, baseCoolDown, castRadius, area,required);
		this.damageAmount=damageAmount;
	}
	public int getDamageAmount() {
		return damageAmount;
	}
	public void setDamageAmount(int damageAmount) {
		this.damageAmount = damageAmount;
	}
	
	
	@Override
	public void execute(ArrayList<Damageable> targets) {
		for(int i = 0; i < targets.size(); i++){
			Damageable d = targets.get(i);
			
			if(d instanceof Champion){
				Champion c = (Champion)d;
			
				if(Game.checkEffect(c,"Shield")){
					for(int j = 0; j < c.getAppliedEffects().size(); j++){		//remove shield effect
						if(c.getAppliedEffects().get(j) instanceof Shield){
							c.getAppliedEffects().get(j).remove(c);	//remove from champion
							c.getAppliedEffects().remove(j);	//remove from list of appliedEffects
							break;
						}
					}
					return;
				}
			}
			d.setCurrentHP(d.getCurrentHP() - damageAmount);
		}
	}
	
	
	public String toString() {
		return //"Damaging Ability \n" +
				super.toString() +
				"Damage Amount: " + damageAmount;
	}
	
	@Override
	public String getType() {
		return "Damaging Ability";
	}

}
