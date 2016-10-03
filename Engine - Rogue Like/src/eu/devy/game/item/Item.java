package eu.devy.game.item;

public class Item 
{
	private Type type;
	
	private int atk;
	private int def;
	private float atk_spd;
	private float wlk_spd;
	
	public Item(Type type, int atk, int def, float atk_spd, float wlk_spd)
	{
		this.type = type;
		this.atk = atk;
		this.def = def;
		this.atk_spd = atk_spd;
		this.wlk_spd = wlk_spd;
	}
	
	public Type getType()
	{
		return type;
	}
	
	public int getAtk()
	{
		return atk;
	}
	
	public void setAtk(int atk)
	{
		this.atk = atk;
	}
	
	public int getDef()
	{
		return def;
	}
	
	public void setDef(int def)
	{
		this.def = def;
	}
	
	public float getAttackSpeed()
	{
		return atk_spd;
	}
	
	public void setAttackSpeed(float atk_spd)
	{
		this.atk_spd = atk_spd;
	}
	
	public float getWalkSpeed()
	{
		return wlk_spd;
	}
	
	public void setWalkSpeed(float wlk_spd)
	{
		this.wlk_spd = wlk_spd;
	}
}
