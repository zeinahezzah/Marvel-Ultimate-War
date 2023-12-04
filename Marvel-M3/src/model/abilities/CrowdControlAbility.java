package model.abilities;

import java.util.ArrayList;

import model.effects.Disarm;
import model.effects.Dodge;
import model.effects.Effect;
import model.effects.Embrace;
import model.effects.PowerUp;
import model.effects.Root;
import model.effects.Shield;
import model.effects.Shock;
import model.effects.Silence;
import model.effects.SpeedUp;
import model.effects.Stun;
import model.world.Champion;
import model.world.Cover;
import model.world.Damageable;

public class CrowdControlAbility extends Ability {
	private Effect effect;

	public CrowdControlAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			Effect effect) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.effect = effect;

	}

	public Effect getEffect() {
		return effect;
	}
	

	@Override
	public void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException {
		for(int i = 0; i < targets.size(); i++){
			Damageable d = targets.get(i);
			Effect e = (Effect) effect.clone();	//create copy of effect
			((Champion)d).getAppliedEffects().add(e);	//add to appliedEffects list
			e.apply((Champion)d);	//apply effect on champion
		}
	}

	
	public String toString() {
		return //"Crowd Control Ability \n" +
				super.toString() +
				"Effect: " + effect;
	}
	
	@Override
	public String getType() {
		return "Crowd Control Ability";
	}
}
