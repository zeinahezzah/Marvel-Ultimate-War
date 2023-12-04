package model.effects;

import java.util.ArrayList;

import model.abilities.*;
import model.effects.*;
import model.world.*;


public class Disarm extends Effect {
	

	public Disarm( int duration) {
		super("Disarm", duration, EffectType.DEBUFF);
		
	}

	//NB: if applied -> can't do normal attacks -> throw ChampionDisarmedException in attack() method
	
	@Override
	public void apply(Champion c) {
		Ability a = new DamagingAbility("Punch", 0, 1, 1, AreaOfEffect.SINGLETARGET, 1, 50);	//create new punch ability with given parameters
		c.getAbilities().add(a);	//add it to champion's abilities
	}

	@Override
	public void remove(Champion c) {
		ArrayList<Ability> a = c.getAbilities();	
		for(int i = 0; i< a.size(); i++){		//traverse champion's abilities list
			if(a.get(i).getName().equals("Punch")){		//remove the "punch" ability that was added
				a.remove(i);	
				break;	//in case of multiple instances of Disarm applied
			}
		}
	}
}
