package model.effects;

import model.abilities.*;
import model.world.*;
import exceptions.*;

public class Root extends Effect {

	public Root( int duration) {
		super("Root", duration, EffectType.DEBUFF);
		
	}
	
	//NB: if applied -> Champion can't move -> throw UnallowedMovementException in move() method

	@Override
	public void apply(Champion c) {
		//NB: inactive has a higher priority than root (check pdf)
		if(!(c.getCondition().equals(Condition.INACTIVE)))	//if inactive -> stays inactive
				c.setCondition(Condition.ROOTED);	//otherwise -> set condition to root
	}

	@Override
	public void remove(Champion c) {
		if(c.getCondition().equals(Condition.ROOTED))	//if champion is rooted -> set back to active
			c.setCondition(Condition.ACTIVE);
				
		for(int i = 0; i<c.getAppliedEffects().size(); i++){		//check applied effects in case of multiple instance of root effect
				if(c.getAppliedEffects().get(i) instanceof Root){	//if the champion still has another root effect
					
					if(!(c.getCondition().equals(Condition.INACTIVE)))	//if champion is not inactive -> set back to root
						c.setCondition(Condition.ROOTED);
			}
		}
	}

}
