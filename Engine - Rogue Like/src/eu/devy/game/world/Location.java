package eu.devy.game.world;

import eu.devy.game.player.Direction;

public class Location
{
	private int x;
	private int y;
	
	public Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public Location(Location location) {
		this.x=location.getX();
		this.y=location.getY();
	}

	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(Location location)
	{
		this.x = location.getX();
		this.y = location.getY();
	}
	
	public Location getLocation()
	{
		return this;
	}
	
	public boolean isNextTo(Location location) {		
		if(location.getX()-this.getX()!=1&&location.getX()-this.getX()!=-1)return false;
		if(location.getY()-this.getY()!=1&&location.getY()-this.getY()!=-1)return false;
		return true;
	}
	
	public Direction getPrimeDirection(Location Location) {
		int DistanceX = (Location.getX()-this.getX());
		int bDistanceX = DistanceX;
		if(DistanceX<0)bDistanceX*=-1;
		int DistanceY = (Location.getY()-this.getY());
		int bDistanceY = DistanceY;
		if(DistanceY<0)bDistanceY*=-1;
		if(bDistanceX>bDistanceY){
			if(DistanceX>0)return Direction.LEFT;
			return Direction.RIGHT;
		}else{
			if(DistanceY>0)return Direction.UP;
			return Direction.DOWN;
		}
	}
}
