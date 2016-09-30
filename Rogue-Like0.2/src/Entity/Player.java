package Entity;

import Grid.Location;
import Grid.Map;

public class Player extends Entity{
	
	String Name;
	
	public Player(String pName,int pLevel,Location pLocation,Map pMap){
		super(pLevel,pLocation,pMap); 
		Name = pName;
		EntityManager.Player.add(this);
		ID = "Player#"+EntityManager.Player.size();
	}

}
