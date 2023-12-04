package model.effects;

import model.world.Champion;

public class Shock extends Effect {

	public Shock(int duration) {
		super("Shock", duration, EffectType.DEBUFF);
		
	}

	@Override
	public void apply(Champion c) {
		c.setSpeed((int)(c.getSpeed()*0.9));	//Decrease speed by 10%
		c.setAttackDamage((int)(c.getAttackDamage()*0.9)); //Decrease normal attack damage by 10%
		c.setCurrentActionPoints(c.getCurrentActionPoints()-1);	//decrease current action points by 1
		c.setMaxActionPointsPerTurn(c.getMaxActionPointsPerTurn()-1);	//decrease max action points by 1
	}

	@Override
	public void remove(Champion c) {
		c.setSpeed((int)(c.getSpeed()/0.9));	//Increase speed by 10% (back to normal)
		c.setAttackDamage((int)(c.getAttackDamage()/0.9));	//Increase normal attack damage by 10% (back to normal)
		c.setCurrentActionPoints(c.getCurrentActionPoints()+1);	//Increase current action points by 1 (back to normal)
		c.setMaxActionPointsPerTurn(c.getMaxActionPointsPerTurn()+1);	//Increase max action points by 1 (back to normal)
		
	}

}
