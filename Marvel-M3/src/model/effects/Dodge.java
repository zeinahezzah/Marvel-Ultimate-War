package model.effects;

import model.world.Champion;

public class Dodge extends Effect {

	public Dodge(int duration) {
		super("Dodge", duration, EffectType.BUFF);
		
	}

	
	//NB: if applied -> champion has a 50% chance of dodging normal attacks ->
			//generate a random number in attack() method to determine if champion dodges or not
	
	@Override
	public void apply(Champion c) {
		c.setSpeed((int)(c.getSpeed()*1.05));		//increase speed by 5%
		
	}

	@Override
	public void remove(Champion c) {
		c.setSpeed((int)(c.getSpeed()/1.05));		//decrease speed by 5% (back to normal)
	}

}
