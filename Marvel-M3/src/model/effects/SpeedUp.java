package model.effects;

import model.world.Champion;

public class SpeedUp extends Effect{

	public SpeedUp(int duration) {
		super("SpeedUp",duration,EffectType.BUFF);
	}

	@Override
	public void apply(Champion c) {
		c.setSpeed((int)(c.getSpeed()*1.15));		//increase speed by 15%
		c.setCurrentActionPoints(c.getCurrentActionPoints()+1);		//increase currentActionPoints by 1
		c.setMaxActionPointsPerTurn(c.getMaxActionPointsPerTurn()+1);	//increase maxActionPoints by 1
	}

	@Override
	public void remove(Champion c) {
		c.setSpeed((int)(c.getSpeed()/1.15));	//decrease speed by 15% (back to normal)
		c.setCurrentActionPoints(c.getCurrentActionPoints()-1);	//Decrease current action points by 1 (back to normal)
		c.setMaxActionPointsPerTurn(c.getMaxActionPointsPerTurn()-1);	//Decrease max action points by 1 (back to normal)	
	}

}
