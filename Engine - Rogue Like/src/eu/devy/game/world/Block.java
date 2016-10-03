package eu.devy.game.world;

public class Block 
{
	public static final int DEFAULT_SQR_SIZE = 128;
	
	private Location location;
	private Type type;
	
	public Block(Location location, Type type)
	{
		this.location = location;
		this.type = type;
	}
	
	public Location getLocation()
	{
		return location;
	}
	
	public void setLocation(Location location )
	{
		this.location = location;
	}
	
	public Type getType()
	{
		return type;
	}
}
