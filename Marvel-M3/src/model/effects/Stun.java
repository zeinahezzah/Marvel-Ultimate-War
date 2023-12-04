package model.effects;

import model.world.Champion;
import model.world.Condition;

public class Stun extends Effect {

	public Stun(int duration) {
		super("Stun", duration, EffectType.DEBUFF);
	}

	//NB: if applied (condition is INACTIVE) -> target turn is skipped in endTurn() method
	
	
	@Override
	public void apply(Champion c) {
		c.setCondition(Condition.INACTIVE);		//set condition to INACTIVE
	}

	@Override
	public void remove(Champion c) {
		for(int i = 0; i<c.getAppliedEffects().size(); i++){	//check if target has a rooted effect 
			if(c.getAppliedEffects().get(i) instanceof Root){	//if Root effect found in appliedEffects
					c.setCondition(Condition.ROOTED);	//set condition to ROOTED
					return;	//exit method
			}
		}
		c.setCondition(Condition.ACTIVE);	//otherwise set back to active
	}


}
