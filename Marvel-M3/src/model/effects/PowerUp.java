package model.effects;

import java.util.ArrayList;

import model.abilities.*;
import model.effects.*;
import model.world.*;


public class PowerUp extends Effect {
	

	public PowerUp(int duration) {
		super("PowerUp", duration, EffectType.BUFF);
		
	}

	@Override
	public void apply(Champion c) {		//increase ALL damaging and healing abilities amount by 20%
		ArrayList<Ability> a = c.getAbilities();	//get abilities list
		for(int i = 0; i< a.size(); i++){	//traverse list
			
			Ability x = a.get(i);	//for each ability
		
			if(x instanceof DamagingAbility){	//damaging ability -> damage amount
				DamagingAbility d = (DamagingAbility) x;	//type cast
				d.setDamageAmount((int)(d.getDamageAmount()*1.2));	//increase damage amount by 20%
			}
			
			if(x instanceof HealingAbility){	//healing ability -> heal amount
				HealingAbility h = (HealingAbility) x;		//type cast
				h.setHealAmount((int)(h.getHealAmount()*1.2));		//increase heal amount by 20%
			}
		}
		
	}

	@Override
	public void remove(Champion c) {
		ArrayList<Ability> a = c.getAbilities();	//get abilities list
		for(int i = 0; i< a.size(); i++){	//traverse list
			
			Ability x = a.get(i);	//for each ability
		
			if(x instanceof DamagingAbility){
				DamagingAbility d = (DamagingAbility) x;
				d.setDamageAmount((int)(d.getDamageAmount()/1.2));	//decrease damage amount by 20% (back to normal)
			}
			
			if(x instanceof HealingAbility){
				HealingAbility h = (HealingAbility) x;
				h.setHealAmount((int)(h.getHealAmount()/1.2));		//decrease heal amount by 20% (back to normal)
			}
		}
	}
	
}
