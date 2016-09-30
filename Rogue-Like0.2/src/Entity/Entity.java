package Entity;

import Grid.*;
import Items.*;

public abstract class Entity {
	
	public Inventory Inv = new Inventory();
	public double ATK;
	public double DEF;
	public double WS;
	public double As;
	public double HP;
	public int Level;
	public Location Location;
	public Map Map;
	public String ID;
	
	public Entity(int pLevel,Location pLocation,Map pMap){
		ID = "Entity#"+(EntityManager.Entitys.size()+1);
		EntityManager.Entitys.add(this);
		Level = pLevel;
		Location = pLocation;
		HP = (int)((double)((10/Math.expm1(0.0175))*(double)(Math.expm1(0.0175*Level)))+Level-1);
		ATK = ((double)5*((double)(Math.expm1(((double)(Math.log(298/5)/255))*Level))))+Level;
		DEF = ((double)5*((double)(Math.expm1(((double)(Math.log(298/5)/255))*Level))))+Level;
		Map=pMap;
	}
	
	/*
	 * @param Direction in Degree (Up = 0, Rigth = 90,Left = -90,Down = 180) 
	 */	
	public boolean walk(int Direction){
		Location nLocation = new Location(Location); 
		if(Direction == 0)nLocation.setSquareY(nLocation.getSquareY()+1);
		else if(Direction == 90)nLocation.setSquareX(nLocation.getSquareX()+1);
		else if(Direction == 180)nLocation.setSquareY(nLocation.getSquareX()-1);
		else if(Direction == -90)nLocation.setSquareX(nLocation.getSquareX()-1);
		else return false;
		if(Map.canWalk(nLocation)){
			Location = nLocation;
			return true;
		}
		return false;
	}
	
	
}
