package eu.devy.game.monster;

import java.awt.Graphics;

public abstract class Component
{
	private String name;
	private Entity instance;
	
	private boolean isActive;
	
	public Component()
	{
		
	}
	
	public Component(String name, Entity instance)
	{
		this.name = name;
		this.instance = instance;
	
		this.isActive = true;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Entity getInstance()
	{
		return instance;
	}
	
	public void setActivity(boolean isActive)
	{
		this.isActive = isActive;
	}
	
	public boolean isActive()
	{
		return isActive;
	}
	
	public abstract void tick();
	public abstract void draw(Graphics graphics);
}