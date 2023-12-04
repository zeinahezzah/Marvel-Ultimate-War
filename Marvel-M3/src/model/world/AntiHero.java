package model.world;

import java.util.ArrayList;



import model.abilities.*;
import model.effects.*;

public class AntiHero extends Champion {

	public AntiHero(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}

	@Override
	public void useLeaderAbility(ArrayList<Champion> targets) {
		
		for(int i = 0; i<targets.size(); i++){
			Stun s = new Stun(2);
			s.apply(targets.get(i));
			targets.get(i).getAppliedEffects().add(s);
		}
	}
//	
//	public String toString(){
//		return "AntiHero \n" + super.toString();
//	}

	@Override
	public String getType() {
		return "AntiHero";
	}
}
