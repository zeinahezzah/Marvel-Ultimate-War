package model.effects;

import model.world.Champion;

public class Silence extends Effect {

	public Silence( int duration) {
		super("Silence", duration, EffectType.DEBUFF);
		
	}

	//NB: if applied -> champion can't cast ability -> throw AbilityUseException in all castAbility methods
	
	@Override
	public void apply(Champion c) {
		c.setCurrentActionPoints(c.getCurrentActionPoints()+2);		//increase currentActionPoints by 2
		c.setMaxActionPointsPerTurn(c.getMaxActionPointsPerTurn()+2);	//increase maxActionPoints by 2
		
	}

	@Override
	public void remove(Champion c) {
		c.setCurrentActionPoints(c.getCurrentActionPoints()-2);	//Decrease current action points by 2 (back to normal)
		c.setMaxActionPointsPerTurn(c.getMaxActionPointsPerTurn()-2);	//Decrease max action points by 2 (back to normal)
		
	}

}
