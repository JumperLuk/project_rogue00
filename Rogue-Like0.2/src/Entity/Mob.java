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
		int Distance = 0;
		Player fPlayer =null;
		for(Player p: EntityManager.Player){
			int pDistanceX = (p.Location.getSquareX()-this.Location.getSquareX());
			if(pDistanceX<0)pDistanceX*=-1;
			int pDistanceY = (p.Location.getSquareY()-this.Location.getSquareY());
			if(pDistanceY<0)pDistanceY*=-1;
			if(Distance == 0|| Distance > (pDistanceX+pDistanceY)){
				Distance = pDistanceX+pDistanceY;		
				fPlayer = p;
			}
		}
		fokusedPlayer = fPlayer;
	}
	
	public void walk(){
		
	}

}
