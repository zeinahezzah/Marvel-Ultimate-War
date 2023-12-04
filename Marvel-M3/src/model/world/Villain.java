package model.world;

import java.util.ArrayList;

import engine.Game;
import model.effects.Effect;
import model.effects.EffectType;
import model.effects.Embrace;



public class Villain extends Champion {

	public Villain(String name, int maxHP, int maxMana, int actions, int speed, int attackRange, int attackDamage) {
		super(name, maxHP, maxMana, actions, speed, attackRange, attackDamage);

	}

	@Override
	public void useLeaderAbility(ArrayList<Champion> targets) {	
		//assuming target is the enemy teams
		for(int i =0; i<targets.size(); i++){
			Champion c = targets.get(i);
			//if(c.getCurrentHP() < 0.3*c.getMaxHP())
				c.setCondition(Condition.KNOCKEDOUT);
				c.setCurrentHP(0);
		} 
	}
	
//	public String toString(){
//		return "Villain \n" + super.toString();
//	}

	@Override
	public String getType() {
		return "Villain";
	}

	
}
