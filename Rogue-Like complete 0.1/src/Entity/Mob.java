package Entity;

import Grid.Location;
import Grid.Map;

public class Mob extends Entity{
	
	Player fokusedPlayer;
	
	public Mob(int pLevel,Location pLocation,Map pMap){
		super(pLevel,pLocation,pMap); 
		fokusPlayer();
	}

	private void fokusPlayer() {
		
	}

}
