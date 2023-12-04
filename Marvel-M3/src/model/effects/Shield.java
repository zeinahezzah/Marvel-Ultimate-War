package model.effects;

import model.world.Champion;

public class Shield extends Effect {

	public Shield( int duration) {
		super("Shield", duration, EffectType.BUFF);
		
	}
	
	//NB: if applied -> champion blocks attack or damaging ability -> only used once -> 
			//condition is dealt with in newHP() method (game class) and execute method in DamagingAbility class

	@Override
	public void apply(Champion c) {
		c.setSpeed((int)(c.getSpeed()*1.02));	//increase speed by 2%
		
	}

	@Override
	public void remove(Champion c) {
		c.setSpeed((int)(c.getSpeed()/1.02));	//increase speed by 2% (back to normal)
		
	}

}
