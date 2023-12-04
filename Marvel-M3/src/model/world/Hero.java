package model.world;

import java.util.ArrayList;




import model.abilities.*;
import model.effects.*;
import model.world.*;



public class Hero extends Champion {

	public Hero(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}
@Override
	public void useLeaderAbility(ArrayList<Champion> targets) {
		//assuming targets is the hero's team
	
		for(int i = 0; i < targets.size(); i++){
			ArrayList<Effect> a = targets.get(i).getAppliedEffects();
			
			for(int j = 0; j < a.size(); j++){
				if(a.get(j).getType().equals(EffectType.DEBUFF)){
					a.get(j).remove(targets.get(i));
					a.remove(j--);
				}
			}
			
			Embrace e = new Embrace(2);
			a.add(e);
			e.apply(targets.get(i));
		} 
		
	}


//	public String toString(){
//		return "Hero \n" + super.toString();
//	}
	@Override
	public String getType() {
		return "Hero";
	}
	
	
}
