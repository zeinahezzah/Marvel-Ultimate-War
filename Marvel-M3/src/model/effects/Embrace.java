package model.effects;

import model.world.Champion;



public class Embrace extends Effect {
	

	public Embrace(int duration) {
		super("Embrace", duration, EffectType.BUFF);
	}

	@Override
	public void apply(Champion c) {
		c.setCurrentHP((int) (c.getCurrentHP()+(c.getMaxHP()*0.2)));	//permanently add 20% of maxHP to currentHP
	
		c.setMana((int) (c.getMana()*1.2));	//permanently increase mana by 20%
		
		c.setSpeed((int)(c.getSpeed()*1.2));	//increase speed by 20%
		
		c.setAttackDamage((int)(c.getAttackDamage()*1.2));	//increase attack damage by 20%
	}

	@Override
	public void remove(Champion c) {
		
		//DONT undo HP and Mana increase since they are permanent!!
		
		c.setSpeed((int)(c.getSpeed()/1.2));	//decrease speed by 20% (back to original)
		c.setAttackDamage((int)(c.getAttackDamage()/1.2));	//decrease attack damage by 20% (back to original)
	}

}
