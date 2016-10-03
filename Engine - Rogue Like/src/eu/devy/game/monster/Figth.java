package eu.devy.game.monster;

import eu.devy.game.player.Player;

public class Figth {
	
	public static void PlayerAttacks(Entity Attacker, Entity Taker){
		int Damage = (int)((LifeComponent)Attacker.components.get(0)).getAtk();
		if(Attacker instanceof Player)Damage+=((Player)Attacker).getInventory().getItem(0).getAtk();
		int Damagetaken = (int) ((int)Damage-Damage*(((LifeComponent)Taker.components.get(0)).getDef()*(((LifeComponent)Taker.components.get(0)).getDefenceEfficency()/100)));
		((LifeComponent)Taker.components.get(0)).setHealtPoints((((LifeComponent)Taker.components.get(0)).getHealtPoints()-Damagetaken));		
	}

}
